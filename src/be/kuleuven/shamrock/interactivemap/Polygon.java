package be.kuleuven.shamrock.interactivemap;

/**
* Created by Joris on 18/08/2014.
*/
public class Polygon extends ILayer {

    public Polygon(LeafletMap linkedMap, String latlngs, double smoothFactor, String color, double weight, double opacity, String fillColor, double fillOpacity) {
        super(linkedMap);
        this.create("polygon", new LeafletObject.BareCode(latlngs),
                new LeafletObject.JsOption("smoothFactor", smoothFactor), new LeafletObject.JsOption("color", color),
                new LeafletObject.JsOption("weight", weight), new LeafletObject.JsOption("opacity", opacity),
                new LeafletObject.JsOption("fillColor", fillColor), new LeafletObject.JsOption("fillOpacity", fillOpacity));
    }

    public void setLatLngs(String latlngs) {

        this.executeMethod("setLatLngs", new LeafletObject.BareCode(latlngs));
        this.executeMethod("redraw");
    }
}
