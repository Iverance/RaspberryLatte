package edu.sjsu.cab;

import java.util.HashMap;

import edu.sjsu.cab.util.MapUtil;

public class ClarkeWrightMethod {

	public ClarkeWrightMethod(int[][] costMatrix) {
		MapUtil.printMatrix(costMatrix);
		HashMap<String,Integer> sortedSavingMap= (HashMap<String, Integer>) MapUtil.sortByValue(generateSavingMap(costMatrix));
		System.out.println("results: "+sortedSavingMap);
	}

	public HashMap<String,Integer> generateSavingMap(int[][] costMatrix) {
		HashMap<String, Integer> savingMap = new HashMap<String, Integer>();
		for (int i = 1; i < costMatrix.length; i++) {
			for (int j = i + 1; j < costMatrix.length; j++) {
				String pathName = String.valueOf(i) + "-" + String.valueOf(j);
				Integer saving = costMatrix[0][i] + costMatrix[0][j] - costMatrix[i][j];
				savingMap.put(pathName, saving);
			}
		}
		return savingMap;
	}
	
	public String getRoute() {
		
		return null;
	}
	
	public static void main(String[] args) {

		ClarkeWrightMethod cwm = new ClarkeWrightMethod(MatrixLoader.RandomMatrix(6));
	}
		
}
