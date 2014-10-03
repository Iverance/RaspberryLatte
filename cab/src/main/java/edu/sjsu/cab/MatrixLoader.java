package edu.sjsu.cab;

import java.util.Arrays;
import java.util.Random;

public class MatrixLoader {

	private static int[][] RandomMatrix(int n) {
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
	
	public static void printMatrix(int[][] m){
	    try{
	        int rows = m.length;
	        int columns = m[0].length;
	        String str = "|\t";

	        for(int i=0;i<rows;i++){
	            for(int j=0;j<columns;j++){
	                str += m[i][j] + "\t";
	            }

	            System.out.println(str + "|");
	            str = "|\t";
	        }

	    }catch(Exception e){System.out.println("Matrix is empty!!");}
	}

	public static void main(String[] args) {

		printMatrix(RandomMatrix(4));
	}
}
