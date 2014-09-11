package be.kuleuven.shamrock.interactivemap;

import be.kuleuven.shamrock.LatLng;
import javafx.concurrent.Worker.State;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

import java.util.*;


public class LeafletMap extends Map {
    private WebView browser = new WebView();
    private WebEngine webEngine = browser.getEngine();

    EventDispatcher eventDispatcher = new EventDispatcher(this);
    private boolean isLoaded = false;
    private ArrayList<String> scriptQueue = new ArrayList<String>();

    public enum LeafletVersion {
        V0_6_4("0.6.4"), V0_7_3("0.7.3");
        private final String text;
        private LeafletVersion(final String text) { this.text = text;}
        @Override
        public String toString() {return text;}
    }

    public LeafletMap(LeafletVersion version, double viewLat, double viewLon, int viewZoom) {
        super();
        webEngine.getLoadWorker().stateProperty().addListener(
                (ov, oldState, newState) -> {
                        if (newState == State.SUCCEEDED) {
                            onLoadSuccess();
                        }});
        // load starts in new thread
        webEngine.load(LeafletMap.class.getResource("map-" + version + ".html").toExternalForm());
        this.init();
        this.setView(viewLat, viewLon, viewZoom);

    }

    public WebView getWebView() {
        return this.browser;
    }

    private synchronized void onLoadSuccess() {
        this.isLoaded = true;
        // register eventDispatcher
        JSObject win = (JSObject) webEngine.executeScript("window");
        win.setMember("app", eventDispatcher);
        // ------------------------
        for (String script : this.scriptQueue) {
            webEngine.executeScript(script);
        }
        this.scriptQueue.clear();

    }

    synchronized void doScript(String script) {
        System.out.println(script);
        if (this.isLoaded) {
            webEngine.executeScript(script);
        } else {
            this.scriptQueue.add(script);
        }
    }

    synchronized Object tryScript(String script) {
        System.out.println(script);
        if (this.isLoaded) {
            return webEngine.executeScript(script);
        } else {
            return null;
        }
    }



    // MUST be public since it is called from the JS environment
    public static class EventDispatcher {
        private LeafletMap leafletMap;
        private HashMap<String, ArrayList<LeafletEventListener>> listeners = new HashMap<String, ArrayList<LeafletEventListener>>();
        private HashMap<String, LeafletObject> varNameToObject = new HashMap<String, LeafletObject>();
        public EventDispatcher (LeafletMap leafletMap) {
            this.leafletMap = leafletMap;
        }

        public void registerEventListener(LeafletObject leafletObject, String eventName, LeafletEventListener eventListener) {
            String varName = leafletObject.jsVariableName;
            this.varNameToObject.put(varName, leafletObject);
            if (! this.listeners.containsKey(varName + eventName)) {
                this.listeners.put(varName + eventName, new ArrayList<LeafletEventListener>());
            }
            if (this.listeners.get(varName + eventName).size() == 0) {
                if (eventListener instanceof MouseEventListener)
                    leafletMap.doScript(varName + ".on('" + eventName +
                            "', function(e){app.onClick(e.latlng.lat, e.latlng.lng, '" +
                            varName + "', '" + eventName +"');});\n");
                if ((eventListener instanceof DragEndListener) && (leafletObject instanceof Marker))
                    leafletMap.doScript(varName + ".on('" + eventName +
                            "', function(e){app.onDragEnd(" + varName + ".getLatLng().lat, " +
                            varName + ".getLatLng().lng, '"+ varName + "', '" + eventName +"');});\n");
                /*
                if (eventListener instanceof PopupEventListener)
                    leafletMap.doScript(varName + ".on('" + eventName +
                            "', function(e){app.onPopupEvent('" + varName + "', '" + eventName +"');});\n");
                            */
            }
            this.listeners.get(varName + eventName).add(eventListener);
        }

        public void onClick(double lat, double lng, String varName, String eventName) {
            LeafletObject object = this.varNameToObject.get(varName);
            for (LeafletEventListener listener : this.listeners.get(varName + eventName)) {
                ((MouseEventListener)(listener)).onMouseEvent(new LatLng(lat, lng), object);
            }
        }

        public void onDragEnd(double lat, double lng, String varName, String eventName) {
            LeafletObject object = this.varNameToObject.get(varName);
            for (LeafletEventListener listener : this.listeners.get(varName + eventName)) {
                ((DragEndListener)(listener)).onDragEnd(new LatLng(lat, lng), object);
            }
        }
/*
        public void onPopupEvent(String varName, String eventName) {
            LeafletObject object = this.varNameToObject.get(varName);
            for (LeafletEventListener listener : this.listeners.get(varName + eventName)) {
                ((PopupEventListener)(listener)).onPopupEvent(object);
            }
        }
        */

    }

    /*
    public static String implode(Object[] os, String glue) {
        return Arrays.asList(os).stream().map(i -> i.toString()).collect(Collectors.joining(glue));
    }

    public static String implode(List<Object> l, String glue) {
        return l.stream().map(i -> i.toString()).collect(Collectors.joining(glue));
    }
    */


}