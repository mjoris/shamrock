package be.kuleuven.shamrock.interactivemap;

import java.util.List;

/**
* Created by Joris on 18/08/2014.
*/
public class FeatureGroup extends LeafletObject {
    public FeatureGroup(LeafletMap linkedMap, List<LeafletObject> objs) {
        super(linkedMap);
        this.create("featureGroup", objs);
    }
}
