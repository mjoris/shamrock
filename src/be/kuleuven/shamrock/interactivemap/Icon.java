package be.kuleuven.shamrock.interactivemap;

/**
* Created by Joris on 18/08/2014.
*/
public class Icon extends LeafletObject {

    public Icon(LeafletMap linkedMap, String iconUrl, int anchorX, int anchorY, int popupAnchorX, int popupAnchorY) {
        super(linkedMap);
        this.create("icon", new JsOption("iconUrl", iconUrl), new JsOption("iconAnchor", new Point(anchorX, anchorY)),
                new JsOption("popupAnchor", new Point(popupAnchorX, popupAnchorY)));

    }

    public void setIconUrl(String iconUrl) {
        this.executeMethod("setIconUrl", iconUrl);
    }

}
