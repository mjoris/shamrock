package be.kuleuven.shamrock.interactivemap;

import be.kuleuven.shamrock.LatLng;
import netscape.javascript.JSObject;

import java.util.Arrays;

/**
* Created by Joris on 18/08/2014.
*/
class Map extends LeafletObject {

    Map() {
        super(null);
        this.linkedMap = (LeafletMap)(this);
        this.jsVariableName = "map";
    }

    protected void init() {
        this.create("map", "map", new JsOption("scrollWheelZoom", "center"), new JsOption("doubleClickZoom", false));
    }

    public void setView(double viewLat, double viewLon, int viewZoom) {
        this.executeMethod("setView", new LatLng(viewLat, viewLon), viewZoom);
    }

    public void fitBounds(double lat1, double lon1, double lat2, double lon2) {
        this.executeMethod("fitBounds", new Bounds(lat1, lon1, lat2, lon2));
    }

    public void fitBounds(ILayer ... llObjects) {
        FeatureGroup group = new FeatureGroup(this.linkedMap, Arrays.asList(llObjects) );
        this.executeMethod("fitBounds", new BareCode(group + ".getBounds()"));
    }

    public void add(ILayer obj) {
        this.executeMethod("addLayer", obj);
    }

    public void remove(ILayer obj) {
        this.executeMethod("removeLayer", obj);
    }

    // returns null when browser map is still loading (!)
    public LatLng getCenter() {
        Object o = this.tryGetMethod("getCenter");
        return (o == null? null : new LatLng((JSObject)o));
    }

    // returns null when browser map is still loading (!)
    public Integer getZoom() {
        Object o = this.tryGetMethod("getZoom");
        return (o == null? null :  ((Integer)o));
    }

    public void addClickListener(MouseEventListener clickListener) {
        this.linkedMap.eventDispatcher.registerEventListener(this, "click", clickListener);
    }

    public void addDoubleClickListener(MouseEventListener clickListener) {
        this.linkedMap.eventDispatcher.registerEventListener(this, "dblclick", clickListener);
    }

    public void addRightClickListener(MouseEventListener clickListener) {
        this.linkedMap.eventDispatcher.registerEventListener(this, "contextmenu", clickListener);
    }
}
