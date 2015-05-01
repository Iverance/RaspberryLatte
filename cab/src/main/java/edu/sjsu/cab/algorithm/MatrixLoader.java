package edu.sjsu.cab.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.maps.model.LatLng;

import edu.sjsu.cab.storage.Request;
import edu.sjsu.cab.util.MapUtil;

public class MatrixLoader {

    public static double[][] getMatrixByUsers(List<Request> users) {
        double[][] matrix = new double[users.size()][users.size()];
        for (int i = 0; i < users.size(); i++) {
            for (int j = 0; j < users.size(); j++) {
                LatLng passenger1 = new LatLng(users.get(i).getPickupLocationLat(),users.get(i).getPickupLocationLong());
                LatLng passenger2 = new LatLng(users.get(j).getPickupLocationLat(),users.get(j).getPickupLocationLong());

                if (i == j) {
                    matrix[i][j] = -1;
                } else {
                    matrix[i][j] = MapUtil.getDistance(passenger1.lat, passenger1.lng, passenger2.lat, passenger2.lng);
                }
            }
        }
        return matrix;
    }

    public static double[][] randomMatrix(int n) {
        double[][] randomMatrix = new double[n][n];

        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (i == j) {
                    randomMatrix[i][j] = -1;
                } else {
                    Integer r = rand.nextInt() % 100;
                    randomMatrix[i][j] = Math.abs(r);
                    randomMatrix[j][i] = randomMatrix[i][j];
                }
            }
        }
        return randomMatrix;
    }
}
