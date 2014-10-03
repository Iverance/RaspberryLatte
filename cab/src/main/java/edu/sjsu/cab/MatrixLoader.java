package edu.sjsu.cab;

import java.util.Arrays;
import java.util.Random;

public class MatrixLoader {

	private static int[][] RandomArray(int n) {
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
				}
			}

		}

		return randomMatrix;
	}

	public static void main(String[] args) {

		System.out.println(Arrays.deepToString(RandomArray(5)));
	}
}
