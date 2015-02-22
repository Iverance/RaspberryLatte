package edu.sjsu.cab.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Stack;

import edu.sjsu.cab.object.Edge;
import edu.sjsu.cab.object.Vertex;
import edu.sjsu.cab.util.MapUtil;

public class ClarkeWrightMethod {

    private int vertexNumber, parallelTotalSavingValue, sequentialTotalSavingValue;
    HashMap<Edge, Integer> sortedSavingEdges;
    Vertex[] vertice;
    /*- for prim
     * distance: distance from each point to current MST 
     * visit: visit record for each point
     */
    boolean[] visit;
    int[] distance;
    int[][] savingMatrix;

    /*- for kruskal
     * forest: the initial state of the graph, each point is considered as a MST
     * edgeCount: the edge number of each point
     */
    int[] forest, edgeCount;

    public ClarkeWrightMethod(int[][] costMatrix) {
        MapUtil.printMatrix(costMatrix);
        savingMatrix = generateSavingMatrix(costMatrix);
        MapUtil.printMatrix(savingMatrix);
        sortedSavingEdges = (HashMap<Edge, Integer>) MapUtil.sortByValue(generateSavingMap(costMatrix), true);
        System.out.println("savings: " + sortedSavingEdges);
        vertexNumber = costMatrix.length;
        setParallelTotalSavingValue(0);
        init();
    }

    public int[][] generateSavingMatrix(int[][] costMatrix) {
        int[][] savingMatrix = new int[costMatrix.length][costMatrix.length];
        ;
        for (int i = 1; i < costMatrix.length; i++) {
            for (int j = i + 1; j < costMatrix.length; j++) {
                savingMatrix[i][j] = costMatrix[0][i] + costMatrix[0][j] - costMatrix[i][j];
                savingMatrix[j][i] = savingMatrix[i][j];
            }
        }
        return savingMatrix;
    }

    public HashMap<Edge, Integer> generateSavingMap(int[][] costMatrix) {
        HashMap<Edge, Integer> savingMap = new HashMap<Edge, Integer>();
        for (int i = 1; i < costMatrix.length; i++) {
            for (int j = i + 1; j < costMatrix.length; j++) {
                Integer saving = costMatrix[0][i] + costMatrix[0][j] - costMatrix[i][j];
                savingMap.put(new Edge(i, j, saving), saving);
            }
        }
        return savingMap;
    }

    public void init() {
        // prim
        visit = new boolean[vertexNumber];
        distance = new int[vertexNumber];
        // kruskal
        vertice = new Vertex[vertexNumber];
        forest = new int[vertexNumber];
        edgeCount = new int[vertexNumber];

        for (int i = 0; i < vertexNumber; i++) {
            visit[i] = (i == 0) ? true : false;
            distance[i] = (int) -1e9;

            forest[i] = i;
            vertice[i] = new Vertex(String.valueOf(i));
            edgeCount[i] = 0;
        }

    }

    private void reset() {
        edgeCount = null;
        vertice = null;
        forest = null;
        visit = null;
        init();
    }

    public Stack<Vertex> getRouteParallelly(Integer start, Integer end) {

        /*-
         * Kruskal Algorithm
         * 
         * Rule:
         * 1. Cannot connect two points which are in same minimum spinning tree
         * 2. traversal all saving edges by sorting saving value with descending order
         * 3. cannot over capacity (FIXME: add constraints)
         * 4. each vertex are only allowed to have two edges, but start & end point has only one.
         */

        int totalSavingValue = 0;

        for (Entry<Edge, Integer> savingSet : sortedSavingEdges.entrySet()) {
            Edge edge = savingSet.getKey();
            if (!(find(edge.getPointA()) == find(edge.getPointB()))) {
                if (edge.getPointA() == end) {
                    if (edgeCount[edge.getPointA()] < 1 && edgeCount[edge.getPointB()] < 2) {
                        union(edge.getPointA(), edge.getPointB());
                        totalSavingValue += edge.getWeight();
                    }
                } else if (edge.getPointB() == end) {
                    if (edgeCount[edge.getPointA()] < 2 && edgeCount[edge.getPointB()] < 1) {
                        union(edge.getPointA(), edge.getPointB());
                        totalSavingValue += edge.getWeight();
                    }
                } else {
                    if (edgeCount[edge.getPointA()] < 2 && edgeCount[edge.getPointB()] < 2) {
                        union(edge.getPointA(), edge.getPointB());
                        totalSavingValue += edge.getWeight();
                    }
                }
            }
        }
        this.setParallelTotalSavingValue(totalSavingValue);

        // Generate the path
        Stack<Vertex> path = new Stack<Vertex>();
        int index = end;
        boolean findNextVertex = true;
        while (findNextVertex) {
            path.push(vertice[index]);
            findNextVertex = false;
            // find the next vertex from neighbor
            for (Vertex neighbor : vertice[index].getNeighbors()) {
                if (!path.contains(neighbor)) {
                    index = Integer.valueOf(neighbor.getId());
                    findNextVertex = true;
                }
            }
        }

        reset();

        return path;

    }

    public Stack<Vertex> getRouteSequentially() {

        /*-
         * Prim Algorithm
         */
        Stack<Vertex> path = new Stack<Vertex>();
        int totalSavingValue=0;

        // start from one of the points that have biggest saving value
        int start = sortedSavingEdges.entrySet().iterator().next().getKey().getPointA();
        distance[start] = 0;

        for (int i = 0; i < vertexNumber; i++) {
            int a = -1, b = -1, min = (int) -1e9;
            // find out the shortest point from MST
            for (int j = 0; j < vertexNumber; j++) {
                if (!visit[j] && distance[j] > min) {
                    a = j;
                    min = distance[j];
                }
            }
            if (a == -1)
                break;
            visit[a] = true;
            totalSavingValue+=distance[a];
            path.push(vertice[a]);
            for (b = 1; b < vertexNumber; b++) {
                if (!visit[b] && savingMatrix[a][b] > distance[b]) {
                    distance[b] = savingMatrix[a][b];
                }
            }

        }
        reset();
        setSequentialTotalSavingValue(totalSavingValue);
        
        return path;
    }

    private int find(int i) {
        int point = Integer.valueOf(i);
        return point == forest[point] ? point : find(forest[point]);
    }

    private void union(Integer i, Integer j) {
//        System.out.print("union edge " + i + " - " + j);
        // compare the each MMS's size, joint the small tree to the bigger tree.
        if (edgeCount[i] >= edgeCount[j]) {
            forest[find(j)] = find(i);
        } else {
            forest[find(i)] = find(j);
        }

        connect(vertice[i], vertice[j]);
        edgeCount[i]++;
        edgeCount[j]++;
//        System.out.print("\n");
    }

    private void connect(Vertex vertex, Vertex vertex2) {
        vertex.addNeighbor(vertex2);
        vertex2.addNeighbor(vertex);
    }

    public void printRoute(Stack<Vertex> route) {
        while (!route.empty())
            System.out.print(route.pop().getId() + "-");
    }

    /**
     * @return the totalSavingValue
     */
    public int getParallelTotalSavingValue() {
        return parallelTotalSavingValue;
    }

    public void setParallelTotalSavingValue(int value) {
        this.parallelTotalSavingValue = value;
    }
    
    /**
     * @return the sequentialTotalSavingValue
     */
    public int getSequentialTotalSavingValue() {
        return sequentialTotalSavingValue;
    }

    /**
     * @param sequentialTotalSavingValue the sequentialTotalSavingValue to set
     */
    public void setSequentialTotalSavingValue(int sequentialTotalSavingValue) {
        this.sequentialTotalSavingValue = sequentialTotalSavingValue;
    }
    
    public static void main(String[] args) {

        /* -------------- PARALLEL IMPLEMENTATION ------------------- */
        ClarkeWrightMethod cwm = new ClarkeWrightMethod(MatrixLoader.RandomMatrix(5));
        cwm.printRoute(cwm.getRouteParallelly(0, 4));
        System.out.print("\n" + cwm.getParallelTotalSavingValue()+"\n");
        
        /* -------------- SEQUENTIAL IMPLEMENTATION ------------------- */
        cwm.printRoute(cwm.getRouteSequentially());
        System.out.print("\n" + cwm.getSequentialTotalSavingValue());
    }

}
