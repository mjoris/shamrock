package be.kuleuven.shamrock;

import netscape.javascript.JSObject;

/**
* Can be used both as Leaflet and OSRM webservice option/parameter
*/
public class LatLng {
    private double lat;
    private double lng;

    public LatLng(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public LatLng (JSObject jsObject) {
        this.lat = (double)(jsObject.getMember("lat"));
        this.lng = (double)(jsObject.getMember("lng"));
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    @Override
    public String toString() {
        return "[" + this.lat +  ", " + this.lng + "]";
    }

    public boolean equals(LatLng other) {
        return ((this.lat == other.lat) && (this.lng == other.lng));
    }
}
