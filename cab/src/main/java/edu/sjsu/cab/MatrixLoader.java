package edu.sjsu.cab;

import java.util.Random;

import edu.sjsu.cab.util.MapUtil;

public class MatrixLoader {

	public static int[][] RandomMatrix(int n) {
		int[][] randomMatrix = new int[n][n];

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
	


	public static void main(String[] args) {

		MapUtil.printMatrix(RandomMatrix(4));
	}
}
