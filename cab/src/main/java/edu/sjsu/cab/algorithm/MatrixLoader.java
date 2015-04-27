package edu.sjsu.cab.algorithm;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import com.google.maps.model.LatLng;

import edu.sjsu.cab.object.PassengerRequest;
import edu.sjsu.cab.util.MapUtil;

public class MatrixLoader {

    public static double[][] getMatrixByPassengers(ArrayList<PassengerRequest> requests) {
        double[][] matrix = new double[requests.size()][requests.size()];
        for (int i = 0; i < requests.size(); i++) {
            for (int j = 0; j < requests.size(); j++) {
                LatLng passenger1 = requests.get(i).getOriginLL();
                LatLng passenger2 = requests.get(j).getOriginLL();

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
