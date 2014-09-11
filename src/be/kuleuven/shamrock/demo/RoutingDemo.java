package be.kuleuven.shamrock.demo;

import be.kuleuven.shamrock.LatLng;
import be.kuleuven.shamrock.costmatrix.OSRMWebservice;
import be.kuleuven.shamrock.interactivemap.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;


public class RoutingDemo extends Application {

    private Scene scene;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Routing demo");
        scene = new Scene(new RoutingRegion(), 750, 500, Color.web("#666970"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static class RoutingRegion extends MapRegion {
        ArrayList<Marker> markerArrayList = new ArrayList<>();
        ArrayList<Polyline> polyArrayList = new ArrayList<>();
        OSRMWebservice service = new OSRMWebservice(OSRMWebservice.PROJECT_OSRM_URL);

        public RoutingRegion() {
            super(new LeafletMap(LeafletMap.LeafletVersion.V0_7_3, 51.05, 3.71, 13));
            TileLayer myLayer =  TileLayer.getMapQuestOsmInstance(this.map);
            this.map.add(myLayer);
            this.map.addClickListener(new MouseEventListener() {
                @Override
                public void onMouseEvent(LatLng latLng, LeafletObject obj) {
                    int position = markerArrayList.size();
                    Icon icon = new NumberIcon(map, NumberIcon.Color.BRIGHT_ORANGE, position);
                    Marker m = new Marker(map, latLng, true, icon);
                    markerArrayList.add(m);
                    map.add(m);

                    if (position > 0) {
                        OSRMWebservice.Route route = service.getRoute(markerArrayList.get(position - 1).getLatLng(), latLng);
                        String encodedPolyline = (route.getStatus()==0? route.getEncodedGeometry() : "");
                        System.out.println(route.getTotalDistance());
                        Polyline poly = new Polyline(map, Polyline.decodePoly(encodedPolyline), 1.0, "#00f", 5, 0.5);
                        polyArrayList.add(poly);
                        map.add(poly);
                    }



                    m.addDragEndListener(new DragEndListener(){
                        @Override
                        public void onDragEnd(LatLng latLng, LeafletObject object) {
                            Marker culprit = (Marker)(object);
                            int position = markerArrayList.indexOf(culprit);
                            updateRoute(position);
                            updateRoute(position + 1);
                        }
                    });

                    if (position == 2) {
                        double[][] test = service.getDistanceTable(markerArrayList.get(0).getLatLng(),
                                markerArrayList.get(1).getLatLng(), markerArrayList.get(2).getLatLng());
                        System.out.println("######" + test[0][0] + " " + test[0][1] + " " + test[0][2] + " ");
                    }

                }
            });
        }

        private void updateRoute(int position) {
            if ((position > 0) && (position < this.markerArrayList.size())) {
                OSRMWebservice.Route route = service.getRoute(markerArrayList.get(position - 1).getLatLng(),
                        markerArrayList.get(position).getLatLng());
                String encodedPolyline = (route.getStatus()==0? route.getEncodedGeometry() : "");
                polyArrayList.get(position - 1).setLatLngs(Polyline.decodePoly(encodedPolyline));
            }
        }
    }
}

