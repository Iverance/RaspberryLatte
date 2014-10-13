package edu.sjsu.cab.algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;

import edu.sjsu.cab.object.Vertex;
import edu.sjsu.cab.util.MapUtil;
import edu.sjsu.cab.util.ValueComparator;

public class ClarkeWrightMethod {

	private int[][] costMatrix;
	HashMap<String, Integer> sortedSavingMap;

	public ClarkeWrightMethod(int[][] costMatrix) {
		this.costMatrix = costMatrix;
		MapUtil.printMatrix(costMatrix);
		this.sortedSavingMap = (HashMap<String, Integer>) MapUtil.sortByValue(
				generateSavingMap(costMatrix), true);
		System.out.println("savings: " + sortedSavingMap);
	}

	public HashMap<String, Integer> generateSavingMap(int[][] costMatrix) {
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

	public List<String> getRouteParallelly(String start, String end) {
		
		// Rule:
		// 1.Cannot connect two points which are in one path
		// 2.Stop if start and end match input
		// 3. cannot over capacity
		// 4. each vertexes are only allowed to connect 2 vertex(two edge).
		 
		List<String> route = new ArrayList<String>();
		HashMap<String,Vertex> tempPath = new HashMap<String,Vertex>();
		Set<Integer> endPoints = new HashSet<Integer>();
		Vertex startPoint = new Vertex(start);
		Vertex endPoint = new Vertex(end);

		//put all vertexes into temp path except start and end. Also calculate the first(closest point) as well
		HashMap<String, Integer> distanceValue = new HashMap<String, Integer>();
		for (int i = 1; i < costMatrix.length; i++) {
			if (!String.valueOf(i).equalsIgnoreCase(end)) {
				distanceValue.put(String.valueOf(i), costMatrix[0][i]);
				tempPath.put(String.valueOf(i), new Vertex(String.valueOf(i)));
			}
		}
		//sort the distance and pick the closest one.
		distanceValue = (HashMap<String, Integer>) MapUtil.sortByValue(distanceValue, false);
		Entry<String, Integer> closestEntry = distanceValue.entrySet().iterator().next();
		
		//initiate the closet Vertex.
		Vertex closestVertex = tempPath.get(distanceValue.entrySet().iterator().next().getKey());
		//connect the known edge: 
		startPoint.addNeighbor(start, -1);
		startPoint.addNeighbor(closestEntry.getKey(),closestEntry.getValue());
		closestVertex.addNeighbor(start, closestEntry.getValue());
		endPoint.addNeighbor(end, -1);
		
		tempPath.put(startPoint.getId(), startPoint);
		tempPath.put(endPoint.getId(), endPoint);
		
		//fill the edge table parallelly with sortedSavingMap
		for (Entry<String, Integer> savingSet : this.sortedSavingMap.entrySet()) {
			String ends[] = savingSet.getKey().split("-");
			if(tempPath.get(ends[0]).isConnectable()&&tempPath.get(ends[1]).isConnectable()){
				int distance = costMatrix[Integer.valueOf(ends[0])][Integer.valueOf(ends[1])];
				tempPath.get(ends[0]).addNeighbor(ends[1], distance);
				tempPath.get(ends[1]).addNeighbor(ends[0], distance);
			}
		}
		for (Entry<String, Vertex> connectedPoints : tempPath.entrySet()) {
			System.out.print(connectedPoints.getKey()+": "+connectedPoints.getValue().getNeighbors()+"\n");
		}
		
		String currentVertex=start;
		String nextVertex=start;
		while(!currentVertex.equals(end)){
			route.add(tempPath.get(currentVertex).getId());
			for(String neighbor:tempPath.get(currentVertex).getNeighbors()){
				if(!route.contains(neighbor)) {
					nextVertex=neighbor;
					System.out.print("nextVertex: "+nextVertex+"\n");
				}
			}
			currentVertex = nextVertex;
		}
		route.add(tempPath.get(end).getId());
		return route;
	}

	public List<Vertex> getRouteSequentially() {
		// TODO: Merge with Ashley's code
		return null;
	}

	public String getClosestVertex(Integer point) {
		Integer shortestDistance;
		Integer closestPoint;
		if (point == 0) {
			shortestDistance = costMatrix[point][1];
			closestPoint = 1;
		} else {
			shortestDistance = costMatrix[point][0];
			closestPoint = 0;
		}
		for (int i = 0; i < costMatrix.length; i++) {
			if (costMatrix[point][i] < shortestDistance && point != i) {
				shortestDistance = costMatrix[point][i];
				closestPoint = i;
			}
		}
		return String.valueOf(closestPoint);
	}

	public static void main(String[] args) {

		ClarkeWrightMethod cwm = new ClarkeWrightMethod(MatrixLoader.RandomMatrix(6));
		for (String temp : cwm.getRouteParallelly("0", "4")) {
			System.out.print(temp + "-");
		}
	}

}
