package edu.sjsu.cab.object;

public class Edge {
    private Integer pointA;
    private Integer pointB;
    private Double weight;
    
    public Edge(Integer i, Integer j, double saving) {
        setPointA(i);
        setPointB(j);
        setWeight(saving);
    }
    
    /**
     * @return the pointA
     */
    public Integer getPointA() {
        return pointA;
    }
    /**
     * @param pointA the pointA to set
     */
    public void setPointA(Integer pointA) {
        this.pointA = pointA;
    }
    /**
     * @return the pointB
     */
    public Integer getPointB() {
        return pointB;
    }
    /**
     * @param pointB the pointB to set
     */
    public void setPointB(Integer pointB) {
        this.pointB = pointB;
    }
    /**
     * @return the weight
     */
    public Double getWeight() {
        return weight;
    }
    /**
     * @param weight the weight to set
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }
    
    @Override
    public String toString() {
        return this.getPointA()+"-"+this.getPointB();/*+":"+this.getWeight();*/
    }
}