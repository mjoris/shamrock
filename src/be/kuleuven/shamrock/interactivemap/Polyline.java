package be.kuleuven.shamrock.interactivemap;

/**
* Created by Joris on 18/08/2014.
*/
public class Polyline extends ILayer {

    public Polyline(LeafletMap linkedMap, String latlngs, double smoothFactor, String color, double weight, double opacity) {
        super(linkedMap);
        this.create("polyline", new LeafletObject.BareCode(latlngs),
                new LeafletObject.JsOption("smoothFactor", smoothFactor), new LeafletObject.JsOption("color", color),
                new LeafletObject.JsOption("weight", weight), new LeafletObject.JsOption("opacity", opacity));
    }

    public void setLatLngs(String latlngs) {
        this.executeMethod("setLatLngs", new LeafletObject.BareCode(latlngs));
        this.executeMethod("redraw");
    }

    // Adapted from http://jeffreysambells.com/2010/05/27/decoding-polylines-from-google-maps-direction-api-with-java
    public static String decodePoly(String encoded) {

        String polyString = "[";
        boolean first = true;
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            polyString += ((first? "" : ",") + "[" + ((double) lat / 1E6) + "," + ((double) lng / 1E6) + "]");
            first = false;
        }
        polyString += "]";
        return polyString;
    }
}
