package edu.sjsu.cab.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.*;

public class Distance {

	//creates an mxn matrix filled with random numbers and -1
	//filling the diagonals
	private int m;
	private List<int[][]> vhpMatrix;

	public Distance(){
		this.m = 0; //mxm matrix
		this.vhpMatrix = new ArrayList<int[][]>();
	}

	//getter and setter methods
	public int getM(){return m;}
	public List<int[][]> getMatrix(){return vhpMatrix;}
	public void setM(int x){this.m = x;}
	public void setMatrix(List<int[][]> matrix){this.vhpMatrix = matrix;}	


	public int[][] matrixGenerator(int m){

		//declare variables
		Distance dim = new Distance();
		Random rand = new Random();
		int[][] tmpRow = new int [m][m];

		//fill matrix with arbitrary distances
		for (int i = 0; i < m; i++){
			for (int j = 0; j < m; j++){
				if(i==j)
					tmpRow[i][j] = -1;
				else if (i > j) {
					tmpRow[i][j] =  rand.nextInt(50)+3;
				} else {
					tmpRow[i][j] = -1;
				}
			}	
		}

		//tmpRow = clarkeAndWright(tmpRow, m);
		//add randomly generated rows to multidim list
		//dim.vhpMatrix.add(tmpRow);
		//return dim.vhpMatrix;

		return tmpRow;
	}

	public static void clarkeAndWright(int[][] tmpRow, int m){

		//initialize variables
		ArrayList<Integer> path = new ArrayList<Integer>(); //stores the path
		ArrayList<Integer> savingsValues = new ArrayList<Integer>();			//stores the values of the hashmap which will be sorted by a max heap
		HashMap<int[], Integer> savingsMap = new HashMap<int[], Integer>();	//relate coordinates to savings
		int [] tmpArray = new int [2];				//tmp store coordinates
		int min = tmpRow[1][0];
		int tmp;

		//calculate savings
		for (int i = 1; i < m ; i++){
			for (int j = 0; j < m; j++){
				if(i < j && i != j) {//savings calculation
					tmpRow[i][j] = tmpRow[i][0] + tmpRow[j][0] - tmpRow[j][i];
					//System.out.println( i + "," + j + ": "+ tmpRow[i][0] + " + " + tmpRow[j][0] +" - " +tmpRow[j][i]+" = "+ tmpRow[i][j]);
					tmpArray[0] = i; tmpArray[1] = j;
					//tmp = Arrays.deepToString(tmpArray);
					savingsValues.add(tmpRow[i][j]);
					System.out.println(savingsValues.toString());
					savingsMap.put(new int[] {tmpArray[0], tmpArray[1]}, (tmpRow[i][j]));
				}
			}
		}

		System.out.println("Distance and Savings Matrix:\n"+Arrays.deepToString(tmpRow));

		Map<int[], Integer> sortedSavingsMap = sortByValues(savingsMap); 

		//find first node (smallest dist) before factoring in savings calcs
		for (int i = 1; i < m; i++){ if(tmpRow[i][0] < min) min = i;}

		//initialize path
		path.add(1); path.add(min);
		System.out.println("Starting Path: "+path.toString());


		//iterate through list to find path
		while(path.size() < m-1){	
			for(Map.Entry<int [], Integer> entry : savingsMap.entrySet()){

				tmp = getNextPath(min, entry.getKey());
				System.out.println("Tmp: "+tmp);
				if(tmp != -1 && path.contains(tmp)==false){// && alreadyVisited(path, tmp) == false){ 
					path.add(tmp); 
					tmpArray = entry.getKey(); 
					min = tmp;
					System.out.println("Adding Path: " + Arrays.toString(tmpArray) + ", Min: " + min);
					System.out.println("New Path: " + path.toString() + ", Min: " + min);
					break;
				}

			}

			//check to see if finished determining path
			if (path.size() < m-1)
				min = path.get(path.size()-1);
			/*cannot find this method so I comment this*/
			//else
				//path.add(getFinalNode(path));
			
			System.out.println("Last index in path --> "+min);
			System.out.println("Last updated path --> "+path.toString());
			savingsMap.remove(tmpArray);
		}

		/*create max heap --> for parallel search method
			Heap sortedSavings = new Heap(savingsValues);
			System.out.println("Max in Heap: "+sortedSavings..getMax());*/

		//display path
		System.out.println("Path: "+path.toString());

		//return tmpRow;

	}

	public void printMatrix (int[][] matrix){ 
		//System.out.println("MxN: " + dim.m + "x" + dim.n);
		System.out.println(Arrays.deepToString(matrix));
	}		

	public static int getNextPath(int min, int[] keyArray){

		if(keyArray[0]==min)
			return keyArray[1];
		else if(keyArray[1]==min)
			return keyArray[0];
		else
			return -1;
	}

	private static HashMap sortByValues(HashMap map) { 
		List list = new LinkedList(map.entrySet());
		// Defined Custom Comparator here
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
			}
		});

		// Copying sorted list in HashMap an using LinkedHashMap to preserve order
		HashMap sortedHashMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedHashMap.put(entry.getKey(), entry.getValue());
		} 
		return sortedHashMap;
	}

	
}