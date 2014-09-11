package be.kuleuven.shamrock.interactivemap;

/**
* Created by Joris on 18/08/2014.
*/
public class TileLayer extends ILayer {


    public static final String MAPBOX_URL_TEMPLATE = "https://{s}.tiles.mapbox.com/v3/{id}/{z}/{x}/{y}.png"; //°,*
    public static final String MAPQUEST_URL_TEMPLATE = "http://otile{s}.mqcdn.com/tiles/1.0.0/map/{z}/{x}/{y}.png"; //°,**
    public static final String MAPQUEST_SAT_URL_TEMPLATE = "http://otile{s}.mqcdn.com/tiles/1.0.0/sat/{z}/{x}/{y}.png"; //°°,**
    public static final String OSM_URL_TEMPLATE = "http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"; //*
    public static final int DEFAULT_MAX_ZOOM = 18;

    //° requires
    public static final String OSM_ATTRIBUTION = "Map data &copy; <a href=\"http://openstreetmap.org\">" +
            "OpenStreetMap</a> contributors, <a href=\"http://creativecommons.org/licenses/by-sa/2.0/\">CC-BY-SA</a>, ";
    //°° requires
    public static final String MQAERIAL_ATTRIBUTION = "Portions Courtesy NASA/JPL-Caltech and U.S. " +
            "Depart. of Agriculture, Farm Service Agency, ";
    //* requires
    public static final String MAPBOX_ATTRIBUTION = "Imagery &copy; <a href=\"http://mapbox.com\">Mapbox</a>";
    //** requires
    public static final String MAPQUEST_ATTRIBUTION = "Tiles Courtesy of <a href=\"http://www.mapquest.com/\" " +
            "target=\"_blank\">MapQuest</a> <img src=\"http://developer.mapquest.com/content/osm/mq_logo.png\">";


    public static final String MAPQUEST_SUBDOMAINS = "1234";
    public static final String DEFAULT_SUBDOMAINS = "abc";

    public TileLayer(LeafletMap linkedMap, String urlTemplate, int maxZoom, int maxNativeZoom, String attribution, String idkey, String subdomains) {
        super(linkedMap);
        this.create("tileLayer", urlTemplate,
                new LeafletObject.JsOption("maxZoom", maxZoom), new LeafletObject.JsOption("maxNativeZoom", maxNativeZoom),
                new LeafletObject.JsOption("attribution", attribution), new LeafletObject.JsOption("id", idkey),
                new LeafletObject.JsOption("subdomains", subdomains));
    }

    public static TileLayer getMapBoxInstance(LeafletMap linkedMap, String idkey) {
        TileLayer tl= new TileLayer(linkedMap, MAPBOX_URL_TEMPLATE, DEFAULT_MAX_ZOOM, DEFAULT_MAX_ZOOM,
                OSM_ATTRIBUTION + MAPBOX_ATTRIBUTION, idkey, DEFAULT_SUBDOMAINS);
        return tl;
    }

    public static TileLayer getOSMInstance(LeafletMap linkedMap) {
        TileLayer tl= new TileLayer(linkedMap, OSM_URL_TEMPLATE, DEFAULT_MAX_ZOOM, DEFAULT_MAX_ZOOM,
                OSM_ATTRIBUTION, "", DEFAULT_SUBDOMAINS);
        return tl;
    }

    public static TileLayer getMapQuestAerialInstance(LeafletMap linkedMap) {
        TileLayer tl= new TileLayer(linkedMap, MAPQUEST_SAT_URL_TEMPLATE, DEFAULT_MAX_ZOOM, DEFAULT_MAX_ZOOM,
                MQAERIAL_ATTRIBUTION + MAPQUEST_ATTRIBUTION, "", MAPQUEST_SUBDOMAINS);
        return tl;
    }

    public static TileLayer getMapQuestOsmInstance(LeafletMap linkedMap) {
        TileLayer tl= new TileLayer(linkedMap, MAPQUEST_URL_TEMPLATE, DEFAULT_MAX_ZOOM, DEFAULT_MAX_ZOOM,
                OSM_ATTRIBUTION + MAPQUEST_ATTRIBUTION, "", MAPQUEST_SUBDOMAINS);
        return tl;
    }


    public void setUrl(String template) {
        this.executeMethod("setUrl", template);
    }

}
