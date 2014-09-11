package be.kuleuven.shamrock.interactivemap;

/**
* Created by Joris on 18/08/2014.
*/
public class Popup extends LeafletObject {
    public Popup(LeafletMap linkedMap, String htmlContent) {
        super(linkedMap);
        this.create("popup");
        this.executeMethod("setContent", htmlContent);
    }

    public void setContent(String htmlContent) {
        this.executeMethod("setContent", htmlContent);
    }
}
