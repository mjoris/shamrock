package be.kuleuven.shamrock.demo;

import be.kuleuven.shamrock.LatLng;
import be.kuleuven.shamrock.costmatrix.CostMatrix;
import be.kuleuven.shamrock.costmatrix.OSRMWebservice;
import be.kuleuven.shamrock.interactivemap.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashSet;


public class TSPDemo extends Application {

    private Scene scene;

    @Override
    public void start(Stage stage) {
        stage.setTitle("TSP demo");
        scene = new Scene(new RoutingRegion(), 750, 500, Color.web("#666970"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


    private static class RoutingRegion extends MapRegion implements CostMatrix.CostFunction {
        CostMatrix matrix = new CostMatrix(this);
        ArrayList<Polyline> polyArrayList = new ArrayList<>();
        OSRMWebservice service = new OSRMWebservice(OSRMWebservice.PROJECT_OSRM_URL);

        public RoutingRegion() {
            super(new LeafletMap(LeafletMap.LeafletVersion.V0_7_3, 51.05, 3.71, 12));
            TileLayer myLayer =  TileLayer.getOSMInstance(this.map);
            this.map.add(myLayer);
            this.map.addClickListener(new MouseEventListener() {
                @Override
                public void onMouseEvent(LatLng latLng, LeafletObject obj) {
                    matrix.addLocation(latLng);
                    System.out.println(matrix);
                    Icon icon = new TemplaticIcon(map, TemplaticIcon.Type.SHOPPING);
                    Marker m = new Marker(map, latLng, false, icon);
                    map.add(m);

                    if (matrix.getLocations().size() > 2) {
                        for (Polyline poly : polyArrayList) {
                            map.remove(poly);
                        }
                        polyArrayList.clear();

                        ArrayList<LatLng> solution = naiveTSPalgorithm(matrix);
                        solution.add(solution.get(0));
                        LatLng previous = null;
                        for (LatLng location : solution) {
                            if (previous != null) {
                                OSRMWebservice.Route route = service.getRoute(previous, location);
                                String encodedPolyline = (route.getStatus()==0? route.getEncodedGeometry() : "");
                                Polyline poly = new Polyline(map, Polyline.decodePoly(encodedPolyline), 1.0, "#00f", 5, 0.5);
                                polyArrayList.add(poly);
                                map.add(poly);
                            }
                            previous = location;
                        }
                    }
                }
            });
        }

        @Override
        public double getCost(LatLng origin, LatLng destination) {
            if (origin.equals(destination)) {
                return 0;
            } else {
                OSRMWebservice.Route route = service.getRoute(origin, destination);
                if (route.getStatus() != 0) {
                    return Double.POSITIVE_INFINITY;
                } else {
                    return route.getTotalTime();
                }

            }
        }

        public ArrayList<LatLng> naiveTSPalgorithm(CostMatrix matrix){
            ArrayList<LatLng> result = new ArrayList<>();
            HashSet<Integer> toBeVisited = new HashSet<>();
            for (int i = 1; i < matrix.getLocations().size(); i++) {
                toBeVisited.add(i);
            }

            result.add(matrix.getLocations().get(0));
            int lastIndex = 0;
            while (! toBeVisited.isEmpty() ) {
                int bestIndex = -1;
                double lowestCost = Double.POSITIVE_INFINITY;
                for (int i = 0; i < matrix.getLocations().size(); i++ ) {
                    if (toBeVisited.contains(i)) {
                        double cost = matrix.getCost(lastIndex, i);
                        if (cost < lowestCost) {
                            bestIndex = i;
                            lowestCost = cost;
                        }
                    }
                }
                result.add(matrix.getLocations().get(bestIndex));
                toBeVisited.remove(bestIndex);
                lastIndex = bestIndex;
            }

            // NEEM ALTIJD DICHTSTBIJZIJNDE DIE VRIJ IS!!

            return result;
        }
    }
}

