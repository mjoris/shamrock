package be.kuleuven.shamrock.interactivemap;

import be.kuleuven.shamrock.LatLng;

/**
* Created by Joris on 18/08/2014.
*/
public class Circle extends ILayer {

    public Circle(LeafletMap linkedMap, LatLng latlng, double radiusInM, double smoothFactor, String color, double weight, double opacity, String fillColor, double fillOpacity) {
        super(linkedMap);
        this.create("circle", latlng, radiusInM,
                new LeafletObject.JsOption("smoothFactor", smoothFactor), new LeafletObject.JsOption("color", color),
                new LeafletObject.JsOption("weight", weight), new LeafletObject.JsOption("opacity", opacity),
                new LeafletObject.JsOption("fillColor", fillColor), new LeafletObject.JsOption("fillOpacity", fillOpacity));
    }

    public void setLatLng(LatLng latlng) {
        this.executeMethod("setLatLng", latlng);
        this.executeMethod("redraw");
    }

    public void setRadius(double radiusInM) {
        this.executeMethod("setRadius", radiusInM);
        this.executeMethod("redraw");
    }


}
