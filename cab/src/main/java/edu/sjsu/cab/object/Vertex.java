package edu.sjsu.cab.object;

import java.util.HashMap;
import java.util.Set;

public class Vertex {
	private String id;
	HashMap<String, Integer> neighbors;
	final static int neighborsNumber=2;

	public Vertex(String id) {
		this.id = id;
		this.neighbors = new HashMap<String, Integer>();
	}

	public Vertex() {
		this("empty");
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean addNeighbor(String nbr, Integer weight) {
		if(neighbors.size()<neighborsNumber){
			this.neighbors.put(nbr, weight);
			return true;
		}else {
			System.out.print("This point cannot be connected");
			return false;
		}
	}

	public Set<String> getNeighbors() {
		return this.neighbors.keySet();
	}

	public String getId() {
		return this.id;
	}

	public Integer getWeightByNeighbor(String nbr) {
		return this.neighbors.get(nbr);
	}
	
	public boolean isConnectable() {
		if(neighbors.size()<neighborsNumber){
			return true;
		}else{
			return false;
		}
	}
}
