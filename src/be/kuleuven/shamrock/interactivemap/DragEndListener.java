package be.kuleuven.shamrock.interactivemap;

import be.kuleuven.shamrock.LatLng;

/**
* Created by Joris on 20/08/2014.
*/
public interface DragEndListener extends LeafletEventListener {
    public void onDragEnd(LatLng newLatLng, LeafletObject object);
}
