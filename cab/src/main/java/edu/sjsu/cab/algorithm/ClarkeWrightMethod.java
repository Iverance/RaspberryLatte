package edu.sjsu.cab.algorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Stack;

import edu.sjsu.cab.object.Edge;
import edu.sjsu.cab.object.Vertex;
import edu.sjsu.cab.util.MapUtil;

public class ClarkeWrightMethod {

    private int vertexNumber, edgeNumber, totalSavingValue;
    private int[][] costMatrix;
    HashMap<Edge, Integer> sortedSavingEdges;
    int[] forest, edgeCount;
    Vertex[] vertice;
    HashMap<String, Vertex> edgesMap = new HashMap<String, Vertex>();

    public static int getDimensions() {

        Random rand = new Random();
        return (rand.nextInt(2) + 4);

    }

    public ClarkeWrightMethod(int[][] costMatrix) {
        this.costMatrix = costMatrix;
        MapUtil.printMatrix(costMatrix);
        this.sortedSavingEdges = (HashMap<Edge, Integer>) MapUtil.sortByValue(generateSavingMap(costMatrix), true);
        System.out.println("savings: " + sortedSavingEdges);
        vertexNumber = costMatrix.length;
        edgeNumber = vertexNumber - 1;
        setTotalSavingValue(0);
        init();
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
        vertice = new Vertex[vertexNumber];
        forest = new int[vertexNumber];
        edgeCount = new int[vertexNumber];
        for (int i = 0; i < vertexNumber; i++) {
            forest[i] = i;
            vertice[i] = new Vertex(String.valueOf(i));
            edgeCount[i] = 0;
        }

    }

    public Stack<Vertex> getRouteParallelly(Integer start, Integer end) {

        /*-
         * Kruskal Algorithm
         * 
         * Rule:
         * 1. Cannot connect two points which are in same minimum spinning tree
         * 2. traversal all saving edges by sorting saving value with descending order
         * 3. cannot over capacity (FIXME: add constraints)
         * 4. each vertexes are only allowed to have two edges, except end point has only one.
         */

        for (Entry<Edge, Integer> savingSet : this.sortedSavingEdges.entrySet()) {
            Edge edge = savingSet.getKey();
            if (!(find(edge.getPointA()) == find(edge.getPointB()))) {
                if (edge.getPointA() == end) {
                    if (edgeCount[edge.getPointA()] < 1 && edgeCount[edge.getPointB()] < 2) {
                        union(edge.getPointA(), edge.getPointB());
                        setTotalSavingValue(getTotalSavingValue() + edge.getWeight());
                    }
                } else if (edge.getPointB() == end) {
                    if (edgeCount[edge.getPointA()] < 2 && edgeCount[edge.getPointB()] < 1) {
                        union(edge.getPointA(), edge.getPointB());
                        setTotalSavingValue(getTotalSavingValue() + edge.getWeight());
                    }
                } else {
                    if (edgeCount[edge.getPointA()] < 2 && edgeCount[edge.getPointB()] < 2) {
                        union(edge.getPointA(), edge.getPointB());
                        setTotalSavingValue(getTotalSavingValue() + edge.getWeight());
                    }
                }
            }
        }
        return generateRoute(end);
    }

    public List<Vertex> getRouteSequentially() {
        // TODO: Merge with Ashley's code
        return null;
    }

    private int find(int i) {
        int point = Integer.valueOf(i);
        return point == forest[point] ? point : find(forest[point]);
    }

    private void union(Integer i, Integer j) {
        System.out.print("union edge " + i + " - " + j);
        // compare the each MMS's size, joint the small tree to the bigger tree.
        if (edgeCount[i] >= edgeCount[j]) {
            forest[find(j)] = find(i);
        } else {
            forest[find(i)] = find(j);
        }

        connect(vertice[i], vertice[j]);
        edgeCount[i]++;
        edgeCount[j]++;
        System.out.print("\n");
    }

    private void connect(Vertex vertex, Vertex vertex2) {
        vertex.addNeighbor(vertex2);
        vertex2.addNeighbor(vertex);
    }

    private Stack<Vertex> generateRoute(int end) {
        Stack<Vertex> route = new Stack<Vertex>();
        int index = end;
        boolean findNextVertex = true;
        while (findNextVertex) {
            route.push(vertice[index]);
            findNextVertex = false;
            // find the next vertex from neighbor
            for (Vertex neighbor : vertice[index].getNeighbors()) {
                if (!route.contains(neighbor)) {
                    index = Integer.valueOf(neighbor.getId());
                    findNextVertex = true;
                }
            }
        }
        return route;
    }

    public void printRoute(Stack<Vertex> route) {
        while (!route.empty())
            System.out.print(route.pop().getId() + "-");
    }

    /**
     * @return the totalSavingValue
     */
    public int getTotalSavingValue() {
        return totalSavingValue;
    }

    /**
     * @param totalSavingValue
     *            the totalSavingValue to set
     */
    public void setTotalSavingValue(int totalSavingValue) {
        this.totalSavingValue = totalSavingValue;
    }

    public static void main(String[] args) {

        /* -------------- JEREMY'S PARALLEL IMPLEMENTATION ------------------- */
        ClarkeWrightMethod cwm = new ClarkeWrightMethod(MatrixLoader.RandomMatrix(100));
        cwm.printRoute(cwm.getRouteParallelly(0, 4));
        System.out.print("\n" + cwm.getTotalSavingValue());

        // ----------------------- ASHLEY'S SEQUENTIAL IMPLEMENTATION
        // initialize variables
        Distance dist = new Distance();
        int[][] vhpMapping;
        int m;

        // randomly create dimensions
        m = getDimensions(); // <-- Translate into Nodes

        vhpMapping = dist.matrixGenerator(m);
        System.out.println("Dimensions: " + m + "x" + m);
        // dist.printMatrix(vhpMapping);
        dist.clarkeAndWright(vhpMapping, m);
        // vhpMapping = dist.clarkeAndWright(vhpMapping, m);
        // dist.printMatrix(vhpMapping);

    }
    
}
