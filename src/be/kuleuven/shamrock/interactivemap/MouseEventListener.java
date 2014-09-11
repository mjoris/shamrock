package be.kuleuven.shamrock.interactivemap;

import be.kuleuven.shamrock.LatLng;

/**
* Created by Joris on 20/08/2014.
*/
public interface MouseEventListener extends LeafletEventListener {
    public void onMouseEvent(LatLng latLng, LeafletObject object);
}
