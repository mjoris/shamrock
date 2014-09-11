package be.kuleuven.shamrock.interactivemap;

/**
* Created by Joris on 18/08/2014.
*/
public class Rectangle extends ILayer {

    public Rectangle(LeafletMap linkedMap, double lat1, double lon1, double lat2, double lon2, double smoothFactor,
                     String color, double weight, double opacity, String fillColor, double fillOpacity) {
        super(linkedMap);
        this.create("rectangle", new LeafletObject.Bounds(lat1, lon1, lat2, lon2),
                new LeafletObject.JsOption("smoothFactor", smoothFactor), new LeafletObject.JsOption("color", color),
                new LeafletObject.JsOption("weight", weight), new LeafletObject.JsOption("opacity", opacity),
                new LeafletObject.JsOption("fillColor", fillColor), new LeafletObject.JsOption("fillOpacity", fillOpacity));
    }

    public void setBounds(double lat1, double lon1, double lat2, double lon2) {
        this.executeMethod("setBounds", new LeafletObject.Bounds(lat1, lon1, lat2, lon2));
        this.executeMethod("redraw");
    }
}
