package edu.sjsu.cab.object;

import com.google.maps.model.LatLng;

import edu.sjsu.cab.util.MapUtil;

public class MapQuadTree<T> {

    private MapQuadTree<T>[] nodes;
    private T object;
    private LatLng latlng;

    public MapQuadTree(double plat, double plng, T obj) {
        this.latlng.lat = plat;
        this.latlng.lng = plng;
        this.object = obj;
    }

    public MapQuadTree(LatLng latlong, T obj) {
        this.latlng = latlong;
        this.object = obj;
    }
    
    public LatLng getLatlng() {
        return latlng;
    }

    public void setLatlng(LatLng latlng) {
        this.latlng = latlng;
    }

    public Object getObject() {
        return this.object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    // =====functions=====

    public T getClosestObject(LatLng latlng) {
        int index = -1;

        switch (this.getDirection(latlng)) {
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
            return nodes[index].getClosestObject(latlng);
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

    public void insert(LatLng latlong, T obj) {// (double plat, double plng, T obj) {
        int index = -1;

        switch (this.getDirection(latlong)) {
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
            this.nodes[index] = new MapQuadTree<T>(latlong, obj);
        } else {
            this.nodes[index].insert(latlong, obj);
        }
    }

    public void replaceObject(T obj) {
        this.object = obj;
    }

    private String getDirection(LatLng latlng) {
        String NS = (this.latlng.lat > latlng.lat) ? "S" : "N";
        String EW = (this.latlng.lng > latlng.lng) ? "W" : "E";
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
        s = inc + this.latlng.lng + "/" + this.latlng.lat;

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
