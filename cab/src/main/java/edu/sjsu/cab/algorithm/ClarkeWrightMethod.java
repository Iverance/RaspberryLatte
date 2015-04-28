package edu.sjsu.cab.algorithm;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Stack;

import edu.sjsu.cab.object.Edge;
import edu.sjsu.cab.object.Vertex;
import edu.sjsu.cab.util.MapUtil;

public class ClarkeWrightMethod {

    private int vertexNumber;
    private double parallelTotalSavingValue, sequentialTotalSavingValue;
    HashMap<Edge, Double> sortedSavingEdges;
    Vertex[] vertice;
    /*- for prim
     * distance: distance from each point to current MST 
     * visit: visit record for each point
     * parent: the record of point's parent
     */
    boolean[] visit;
    int[] distance, parent;
    double[][] savingMatrix;

    /*- for kruskal
     * forest: the initial state of the graph, each point is considered as a MST
     * edgeCount: the edge number of each point
     */
    int[] forest, edgeCount;

    public ClarkeWrightMethod(double[][] costMatrix) {
        MapUtil.printMatrix(costMatrix);
        savingMatrix = generateSavingMatrix(costMatrix);
        System.out.println("\n");
        MapUtil.printMatrix(savingMatrix);
        sortedSavingEdges = (HashMap<Edge, Double>) MapUtil.sortByValue(generateSavingMap(costMatrix), true);
        System.out.println("savings: " + sortedSavingEdges);
        vertexNumber = costMatrix.length;
        setParallelTotalSavingValue((double) 0);
        init();
    }

    public double[][] generateSavingMatrix(double[][] costMatrix) {
        double[][] savingMatrix = new double[costMatrix.length][costMatrix.length];
        for (int i = 1; i < costMatrix.length; i++) {
            for (int j = i + 1; j < costMatrix.length; j++) {
                savingMatrix[i][j] = costMatrix[0][i] + costMatrix[0][j] - costMatrix[i][j];
                savingMatrix[j][i] = savingMatrix[i][j];
            }
        }
        return savingMatrix;
    }

    public HashMap<Edge, Double> generateSavingMap(double[][] costMatrix) {
        HashMap<Edge, Double> savingMap = new HashMap<Edge, Double>();
        for (int i = 1; i < costMatrix.length; i++) {
            for (int j = i + 1; j < costMatrix.length; j++) {
                double saving = costMatrix[0][i] + costMatrix[0][j] - costMatrix[i][j];
                savingMap.put(new Edge(i, j, saving), saving);
            }
        }
        return savingMap;
    }

    public void init() {
        // prim
        visit = new boolean[vertexNumber];
        distance = new int[vertexNumber];
        parent = new int[vertexNumber];
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

    public Stack<Vertex> getRouteParallelly() {

        /*-
         * Kruskal Algorithm
         * 
         * Rule:
         * 1. Cannot connect two points which are in same minimum spinning tree
         * 2. traversal all saving edges by sorting saving value with descending order
         * 3. cannot over capacity (FIXME: add constraints)
         * 4. each vertex are only allowed to have two edges, but start & end point has only one.
         */

        Double totalSavingValue = (double) 0;
        // start from one of the points that have biggest saving value
        int start = sortedSavingEdges.entrySet().iterator().next().getKey().getPointA();

        for (Entry<Edge, Double> savingSet : sortedSavingEdges.entrySet()) {
            Edge edge = savingSet.getKey();
            if (!(find(edge.getPointA()) == find(edge.getPointB()))) {
                if (edgeCount[edge.getPointA()] < 2 && edgeCount[edge.getPointB()] < 2) {
                    union(edge.getPointA(), edge.getPointB());
                    totalSavingValue += edge.getWeight();
                }
            }
        }
        this.setParallelTotalSavingValue(totalSavingValue);

        // Generate the path
        Stack<Vertex> path = new Stack<Vertex>();
        int startPoint = 0;
        for (int i = 0; i < edgeCount.length; i++) {
            if (edgeCount[i] == 1)
                startPoint = i;
        }
        boolean findNextVertex = true;
        while (findNextVertex) {
            path.push(vertice[startPoint]);
            findNextVertex = false;
            // find the next vertex from neighbor
            for (Vertex neighbor : vertice[startPoint].getNeighbors()) {
                if (!path.contains(neighbor)) {
                    startPoint = Integer.valueOf(neighbor.getId());
                    findNextVertex = true;
                }
            }
        }

        reset();
        printRoute(path);

        return path;

    }

    public Stack<Vertex> getRouteSequentially() {

        /*-
         * Prim Algorithm
         */
        Double totalSavingValue = (double) 0;

        // start from one of the points that have biggest saving value
        int start = sortedSavingEdges.entrySet().iterator().next().getKey().getPointA();
        visit[start] = true;
        int currentPoint = start;
        int nextPoint = 0;

        for (int a = 1; a < vertexNumber - 1; a++) {
            Double dist = (double) -99999;
            for (int b = 1; b < vertexNumber; b++) {
                if (!visit[b] && savingMatrix[currentPoint][b] > dist) {
                    nextPoint = b;
                    dist = savingMatrix[currentPoint][b];
                }
            }
            totalSavingValue = totalSavingValue + dist;
            visit[nextPoint] = true;
            connect(vertice[nextPoint], vertice[currentPoint]);
            currentPoint = nextPoint;
        }

        // Generate the path
        Stack<Vertex> path = new Stack<Vertex>();

        boolean findNextVertex = true;
        while (findNextVertex) {
            path.push(vertice[start]);
            findNextVertex = false;
            // find the next vertex from neighbor
            for (Vertex neighbor : vertice[start].getNeighbors()) {
                if (!path.contains(neighbor)) {
                    start = Integer.valueOf(neighbor.getId());
                    findNextVertex = true;
                }
            }
        }

        reset();
        setSequentialTotalSavingValue(totalSavingValue);
        printRoute(path);
        
        
        return path;
    }

    private int find(int i) {
        int point = Integer.valueOf(i);
        return point == forest[point] ? point : find(forest[point]);
    }

    private void union(Integer i, Integer j) {
        // System.out.print("union edge " + i + " - " + j);
        // compare the each MMS's size, joint the small tree to the bigger tree.
        if (edgeCount[i] >= edgeCount[j]) {
            forest[find(j)] = find(i);
        } else {
            forest[find(i)] = find(j);
        }

        connect(vertice[i], vertice[j]);
        edgeCount[i]++;
        edgeCount[j]++;
        // System.out.print("\n");
    }

    private void connect(Vertex vertex, Vertex vertex2) {
        vertex.addNeighbor(vertex2);
        vertex2.addNeighbor(vertex);
    }

    public String printRoute(Stack<Vertex> route) {
        String path = "";
        while (!route.empty()) {
            path = route.pop().getId() + ","+path;
        }
        System.out.print(path);
        return path;
    }

    /**
     * @return the totalSavingValue
     */
    public double getParallelTotalSavingValue() {
        return parallelTotalSavingValue;
    }

    public void setParallelTotalSavingValue(Double value) {
        this.parallelTotalSavingValue = value;
    }

    /**
     * @return the sequentialTotalSavingValue
     */
    public double getSequentialTotalSavingValue() {
        return this.sequentialTotalSavingValue;
    }

    /**
     * @param sequentialTotalSavingValue
     *            the sequentialTotalSavingValue to set
     */
    public void setSequentialTotalSavingValue(Double sequentialTotalSavingValue) {
        this.sequentialTotalSavingValue = sequentialTotalSavingValue;
    }

}
