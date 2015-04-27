package edu.sjsu.cab.object;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Freeway {

    private ArrayList<String> exit; // store exist #s
    private HashMap<String, String> exitDescription;// store description w/ exit number
    private HashMap<String, ArrayList<Double>> exitLatLong;
    private HashMap<String, ArrayList<String>> exitCity = new HashMap<String, ArrayList<String>>();

    // ------------- CONSTRUCTORS ------------------
    public Freeway() {
        this.exit = new ArrayList<String>();// store exist #s
        this.exitDescription = new HashMap<String, String>();// store description w/ exit number
        this.exitLatLong = new HashMap<String, ArrayList<Double>>();
        this.exitCity = new HashMap<String, ArrayList<String>>();
    }

    // --------- GETTER SETTERS --------------------
    public void setExit(String e) {
        exit.add(e);
    }

    public void setArrayOfExits(String[] e) {
        for (int i = 0; i < e.length; i++) {
            this.exit.add(e[i]);
        }
    }

    public void setExitDescription(String e, String desc) {
        this.exitDescription.put(e, desc);
    }

    public void setExitDescriptionArray(String[] e, String[] desc) {

        if (e.length != desc.length) {
            System.err.println("ERROR: Exit length (" + e.length + ") and Exit Description length (" + desc.length + ") " + "are mismatched.");
        } else {
            for (int i = 0; i < desc.length; i++) {
                this.exitDescription.put(e[i], desc[i]);
            }
        }
    }

    public void setExitLatLong(String e, double lat, double lon) {
        this.exitLatLong.put(e, new ArrayList<Double>(Arrays.asList(lat, lon)));
    }

    public void setExitLatLongArray(String[] e, ArrayList<List<Double>> latlong) {

        ArrayList<Double> temp = new ArrayList<Double>();

        for (int i = 0; i < e.length; i++) {
            temp.addAll(latlong.get(i * 2));
            temp.addAll(latlong.get(i * 2 + 1));
            this.exitLatLong.put(e[i], temp);

        }
    }

    // public void setExitCity(int e, String city){} //<-- Needed?
    public ArrayList<String> getExitList() {
        return this.exit;
    }

    public String getExitDescription(int e) {
        return this.exitDescription.get(e);
    }

    public ArrayList<String> getExitDescriptionList() {
        return (ArrayList<String>) this.exitDescription.values();
    }

    public ArrayList<Double> getLatLong(int e) {
        return this.exitLatLong.get(e);
    }

    public Collection<ArrayList<Double>> getLatLongList() {
        return this.exitLatLong.values();
    }

    public HashMap<Double, Double> getExitCity(int e) {
        return null;
    }

    public HashMap<Double, Double> getCityList(int e) {
        return null;
    }

    // -----------PUBLIC METHODS -------------------
    // public sortFreewayByExit(int ascDesc){} //<-- Needed?
    // public sortFreewayByExitDesc(int ascDesc) //<-- Needed?
    public void printExitList() {
        System.out.println(this.exit.toString());
    }

    public void printDescriptionList() {
        System.out.println(this.exitDescription.values().toString());
    }

    public void printExitAndDescriptionList() {
        System.out.println(this.exitDescription.toString());
    }

    public ArrayList<List<Double>> latLongLoader() {// may

        ArrayList<List<Double>> latlong = new ArrayList<List<Double>>();
        int isFinished = 0, i = 0;
        double lat, lon;
        Scanner in = new Scanner(System.in);

        System.out.println("\nThis method retrieves lat and longs. Enter -1 to stop and any number to continue: ");
        isFinished = in.nextInt();

        while (isFinished != -1) {

            System.out.println("\nEnter latitude #" + i + ":");
            lat = in.nextDouble();
            System.out.println("\nEnter longtitude #" + i + ":");
            lon = in.nextDouble();
            latlong.add(Arrays.asList(lat, lon));
            System.out.println("Added set " + i + ":" + latlong.subList(i, i + 1) + ")");
            System.out.println("\nThis method retrieves lat and longs. Enter -1 to stop and any number to continue: ");
            isFinished = in.nextInt();
            i++;
        }

        System.out.println(latlong.toString());
        in.close();
        return latlong;

    }

    public void printLatLongList() {
        System.out.println(this.exitLatLong.values().toString());
    }
    // public void printExitDescriptionLatLong()

}
