package be.kuleuven.shamrock.costmatrix;

import be.kuleuven.shamrock.LatLng;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Joris on 10/09/2014.
 */
public class CostMatrix implements Serializable{

    private ArrayList<LatLng> locations;
    private ArrayList<ArrayList<Double>> matrix;
    private CostFunction costFunction;
    private int initialCapacity = 100;

    public CostMatrix(CostFunction costFunction) {
        this.locations = new ArrayList<>();
        this.matrix = new ArrayList<>();
        this.costFunction = costFunction;
    }

    public CostMatrix(CostFunction costFunction, int initialCapacity) {
        this.locations = new ArrayList<>(initialCapacity);
        this.matrix = new ArrayList<>(initialCapacity);
        this.costFunction = costFunction;
        this.initialCapacity = initialCapacity;
    }

    public static CostMatrix create(ArrayList<LatLng> locations, ArrayList<ArrayList<Double>> matrix, CostFunction costFunction) {
        if ((locations == null) || (matrix == null)) return null;
        int dim = locations.size();
        if (matrix.size() != dim) return null;
        for (ArrayList<Double> row : matrix) {
            if (row.size() != dim) return null;
        }
        CostMatrix result = new CostMatrix(costFunction);
        result.locations = locations;
        result.matrix = matrix;
        return result;
    }

    public ArrayList<LatLng> getLocations() {
        return locations;
    }

    public double getCost(int indexFrom, int indexTo) {
        return matrix.get(indexFrom).get(indexTo);
    }

    public synchronized void addLocation(LatLng location) {
        locations.add(location);
        int oldSize = matrix.size();
        ArrayList<Double> newRow = new ArrayList<>();
        for (int i = 0; i < oldSize; i++) {
            matrix.get(i).add(costFunction.getCost(locations.get(i),location));
            newRow.add(costFunction.getCost(location, locations.get(i)));
        }
        newRow.add(costFunction.getCost(location, location));
        matrix.add(newRow);
    }

    public synchronized void updateLocation(int index, LatLng location) {
        locations.set(index, location);
        ArrayList<Double> originRow = matrix.get(index);
        for (int i = 0; i < matrix.size(); i++) {
            matrix.get(i).set(index, costFunction.getCost(locations.get(i),location));
            if (i != index) {
                originRow.set(i, costFunction.getCost(location, locations.get(i)));
            }
        }
    }

    public synchronized void removeLocation(int index) {
        locations.remove(index);
        matrix.remove(index);
        for (ArrayList<Double> row : matrix) {
            row.remove(index);
        }
    }


    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        int dim = locations.size();
        out.writeInt(dim);
        for (int i = 0; i < dim; i++) {
            out.writeDouble(locations.get(i).getLat());
            out.writeDouble(locations.get(i).getLng());
        }
        for (int i = 0; i < dim; i++) {
            ArrayList<Double> row = matrix.get(i);
            for (int j = 0; j < dim; j++) {
                out.writeDouble(row.get(j));
            }
        }
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException  {
        int dim = in.readInt();
        this.locations = new ArrayList<>(dim);
        this.matrix = new ArrayList<>(dim);
        for (int i = 0; i < dim; i++) {
            double lat = in.readDouble();
            double lng = in.readDouble();
            this.locations.add(new LatLng(lat, lng));
        }
        for (int i = 0; i < dim; i++) {
            ArrayList<Double> row = new ArrayList<>(dim);
            for (int j = 0; j < dim; j++) {
                row.add(in.readDouble());
            }
            matrix.add(row);
        }
    }

    public interface CostFunction {
        // please handle the case origin=destination separately in this function
        // return Double.POSITIVE_INFINITY
        public double getCost(LatLng origin, LatLng destination);
    }

    @Override
    public String toString() {
        String result = "";
        for(ArrayList<Double> row : matrix) {
            for (Double element : row)
              result += (element + " ");
            result += "\n";
        }
        return result;
    }


}
