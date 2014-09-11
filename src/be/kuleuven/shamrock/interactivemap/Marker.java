package be.kuleuven.shamrock.interactivemap;

import be.kuleuven.shamrock.LatLng;
import netscape.javascript.JSObject;

/**
* Created by Joris on 18/08/2014.
*/
public class Marker extends ILayer {

    public Marker(LeafletMap linkedMap, LatLng latLng, boolean draggable) {
        super(linkedMap);
        this.create("marker", latLng, new LeafletObject.JsOption("draggable", draggable));
    }

    public Marker(LeafletMap linkedMap, LatLng latLng, boolean draggable,  Icon icon) {
        super(linkedMap);
        this.create("marker", latLng,
                new LeafletObject.JsOption("draggable", draggable),
                new LeafletObject.JsOption("icon", icon));
    }

    public void setLatLng(LatLng latLng) {
        this.executeMethod("setLatLng", latLng);
        this.executeMethod("update");
    }

    // returns null when browser map is still loading (!)
    public LatLng getLatLng() {
        Object o = this.tryGetMethod("getLatLng");
        return (o == null? null : new LatLng((JSObject)o));
    }

    public void bindPopup(Popup popup) {
        this.executeMethod("bindPopup", popup);
        this.executeMethod("update");
    }

    public void bindPopup(String htmlContent) {
        this.executeMethod("bindPopup", htmlContent);
    }

    public void unbindPopup() {
        this.executeMethod("unbindPopup");
    }

    public void openPopup() {
        this.executeMethod("openPopup");
    }

    public void closePopup() {
        this.executeMethod("closePopup");
    }

    public void togglePopup() {
        this.executeMethod("togglePopup");
    }

    public void setIcon(Icon icon) {
        this.executeMethod("setIcon", icon);
    }

    /**
     * Caution: when a popup is bound to the listener, a 'native openPopup call' is called
     * just BEFORE the onMouseEvent execution, using the popup bound at that time
     */
    public void addClickListener(MouseEventListener clickListener) {
        this.linkedMap.eventDispatcher.registerEventListener(this, "click", clickListener);
    }

    public void addDoubleClickListener(MouseEventListener clickListener) {
        this.linkedMap.eventDispatcher.registerEventListener(this, "dblclick", clickListener);
    }

    public void addRightClickListener(MouseEventListener clickListener) {
        this.linkedMap.eventDispatcher.registerEventListener(this, "contextmenu", clickListener);
    }

    public void addDragEndListener(DragEndListener dragEndListener) {
        this.linkedMap.eventDispatcher.registerEventListener(this, "dragend", dragEndListener);
    }


}
