package be.kuleuven.shamrock.costmatrix;



import be.kuleuven.shamrock.LatLng;

import javax.json.*;

/**
 * Created by Joris on 20/08/2014.
 */
public class OSRMWebservice {

    public static final String PROJECT_OSRM_URL = "https://router.project-osrm.org";
    private String serverUrl;

    public OSRMWebservice(String serverUrl) {
        this.serverUrl = serverUrl;
    }


    public class Route {
        private String encodedGeometry;
        private String statusMessage;
        private int status;
        private String startPointName;
        private String endPointName;
        private double totalTime;
        private double totalDistance;

        public String getEncodedGeometry() {
            return encodedGeometry;
        }

        public String getStatusMessage() {
            return statusMessage;
        }

        public int getStatus() {
            return status;
        }

        public String getStartPointName() {
            return startPointName;
        }

        public String getEndPointName() {
            return endPointName;
        }

        public double getTotalTime() {
            return totalTime;
        }

        public double getTotalDistance() {
            return totalDistance;
        }
    }




    public Route getRoute(LatLng... viaPoints) {
        JSonWebservice.KeyValuePair[] pairs = new JSonWebservice.KeyValuePair[viaPoints.length+3];
        pairs[0] = new JSonWebservice.KeyValuePair("z","18");
        pairs[1] = new JSonWebservice.KeyValuePair("output","json");
        pairs[2] = new JSonWebservice.KeyValuePair("instructions", Boolean.toString(false));
        for (int i=0; i < viaPoints.length; i++) {
            pairs[i+3] = new JSonWebservice.KeyValuePair("loc", viaPoints[i].getLat() + "," + viaPoints[i].getLng());
        }
        JsonObject jsonObj = (JsonObject)(JSonWebservice.callService(false, this.serverUrl, "viaroute", pairs));
        Route route = new Route();

        route.status = jsonObj.getInt("status");
        route.statusMessage = jsonObj.getString("status_message");
        if (route.status == 0) {
            route.encodedGeometry = jsonObj.getString("route_geometry");
            JsonObject summary = jsonObj.getJsonObject("route_summary");
            route.startPointName = summary.getString("start_point");
            route.endPointName = summary.getString("end_point");
            route.totalTime = summary.getJsonNumber("total_time").doubleValue();
            route.totalDistance = summary.getJsonNumber("total_distance").doubleValue();
        }
        return route;
    }

    /** The results are network distance; in a default configuration this is travel-time, in 10th of seconds
     *
    **/
    public double[][] getDistanceTable(LatLng... points) {
        JSonWebservice.KeyValuePair[] pairs = new JSonWebservice.KeyValuePair[points.length];
        for (int i=0; i < points.length; i++) {
            pairs[i] = new JSonWebservice.KeyValuePair("loc", points[i].getLat() + "," + points[i].getLng());
        }
        JsonObject jsonObj = (JsonObject)(JSonWebservice.callService(false, this.serverUrl, "table", pairs));
        double[][] matrix = new double[points.length][points.length];
        JsonArray jArray = jsonObj.getJsonArray("distance_table");
        for (int i=0; i < jArray.size(); i++) {
            JsonArray jArray2 = jArray.getJsonArray(i);
            for (int j=0; j < jArray2.size(); j++) {
                matrix[i][j] = jArray2.getJsonNumber(j).doubleValue();
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        OSRMWebservice service = new OSRMWebservice(OSRMWebservice.PROJECT_OSRM_URL);
        Route r = service.getRoute(new LatLng(50.205033,4.262695), new LatLng(51.179343,5.317383));

        System.out.println(r.encodedGeometry);

    }




}
