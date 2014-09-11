package be.kuleuven.shamrock.interactivemap;

/**
* Created by Joris on 18/08/2014.
*/
public class ILayer extends LeafletObject {
    ILayer(LeafletMap linkedMap) {
        super(linkedMap);
    }

    public void setOpacity(double opacity) {
        this.executeMethod("setOpacity", opacity);
    }
}
