package edu.sjsu.cab.object;

import java.util.HashSet;
import java.util.Set;

public class Vertex {
    private String id;
    HashSet<Vertex> neighbors;

    public Vertex(String id) {
        this.id = id;
        this.neighbors = new HashSet<Vertex>();
    }

    public Vertex() {
        this("empty");
        this.neighbors = new HashSet<Vertex>();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void addNeighbor(Vertex nbr) {
        this.neighbors.add(nbr);
        // if(neighbors.size()<neighborsNumber){
        // this.neighbors.put(nbr, weight);
        // return true;
        // }else {
        // System.out.print("This point cannot be connected");
        // return false;
        // }
    }

    public Set<Vertex> getNeighbors() {
        return this.neighbors;
    }

}
