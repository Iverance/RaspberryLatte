package edu.sjsu.cab.object;

import edu.sjsu.cab.util.MapUtil;

public class MapQuadTree<T> {

    private MapQuadTree<T>[] nodes;
    private double lat;
    private double lng;
    private T object;

    public MapQuadTree(double plat, double plng, T obj) {
        this.lat = plat;
        this.lng = plng;
        this.object = obj;
    }
    
    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public Object getObject() {
        return this.object;
    }
    
    public void setObject(T object) {
        this.object = object;
    }
    
    //=====functions=====
    
    public T getClosestObject(double plat, double plng) {
        int index = -1;

        switch (this.getDirection(plat, plng)) {
        case "NE":
            index = 0;
            break;
        case "SE":
            index = 1;
            break;
        case "SW":
            index = 2;
            break;
        case "NW":
            index = 3;
            break;
        }
        
        if (!MapUtil.isNullOrEmpty(nodes[index])) {
            return nodes[index].getClosestObject(plat, plng);
        } else {
            return this.object;
        }
    }

    public void clear() {
        object = null;

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                nodes[i].clear();
                nodes[i] = null;
            }
        }
    }

    public void insert(double plat, double plng, T obj) {
        int index = -1;

        switch (this.getDirection(plat, plng)) {
        case "NE":
            index = 0;
            break;
        case "SE":
            index = 1;
            break;
        case "SW":
            index = 2;
            break;
        case "NW":
            index = 3;
            break;
        }
        if (MapUtil.isNullOrEmpty(nodes[index])) {
            this.nodes[index] = new MapQuadTree<T>(plat, plng, obj);
        } else {
            this.nodes[index].insert(plat, plng, obj);
        }
    }

    public void replaceObject(T obj) {
        this.object = obj;
    }

    private String getDirection(double pLat, double pLng) {
        String NS = (this.lat > pLat) ? "S" : "N";
        String EW = (this.lng > pLng) ? "W" : "E";
        return NS + EW;
    }

    @Override
    public String toString() {
        return printTree(0);
    }

    private String printTree(int increment) {
        String s = "";
        String inc = "";
        for (int i = 0; i < increment; ++i) {
            inc = inc + " ";
        }
        s = inc + this.lng + "/" + this.lat;

        for (int i = 0; i < 4; i++) {
            if (!MapUtil.isNullOrEmpty(this.nodes[i])) {
                switch (i) {
                case 0:
                    s += "NE ";
                    break;
                case 1:
                    s += "SE ";
                    break;
                case 2:
                    s += "SW ";
                    break;
                case 3:
                    s += "NW ";
                    break;
                }
                s += "\n" + this.nodes[i].printTree(increment + 2);
            }
        }
        return s;
    }

}
