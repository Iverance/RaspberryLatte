package edu.sjsu.cab.object;

import java.util.ArrayList;
import java.util.HashMap;

public class Vehicle {
	
	private boolean isAvailable;
	private enum carType{HYBRID, SUV, PLUGIN, SPORTS, COUPE, LIMOUSINE, TRUCK};
	private enum driverGender{MALE, FEMALE};
	private enum carColor {BLACK, WHITE, BLUE, GREEN, TAN, SILVER, RED, YELLOW, OTHER};
	private String vehicleId;
	private String licensePlate;
	private int numSeatsAvailable;
	private String make;
	private String model;
	private int year;
	private String specialFeatures;
	private ArrayList<Double> currentLocation;		//lat long coordinates
	private ArrayList<Double> currentDestination;	//lat ling coordinates
	private int capacity;
	
	public Vehicle(){
		this.isAvailable = true;
		this.capacity = 4;
		this.currentDestination = new ArrayList<Double>();
		this.currentLocation = new ArrayList<Double>();
		this.licensePlate = null;
		this.make = null;
		this.model = null;
		this.numSeatsAvailable = 0;
		this.specialFeatures = "";
		this.vehicleId = null;
		this.year = 0;
	}
	
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public boolean getIsAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public int getNumSeatsAvailable() {
		return numSeatsAvailable;
	}
	public void setNumSeatsAvailable(int numSeatsAvailable) {
		this.numSeatsAvailable = numSeatsAvailable;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getSpecialFeatures() {
		return specialFeatures;
	}
	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}
	public ArrayList<Double> getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(ArrayList<Double> currentLocation) {
		this.currentLocation = currentLocation;
	}
	public ArrayList<Double> getCurrentDestination() {
		return currentDestination;
	}
	public void setCurrentDestination(ArrayList<Double> currentDestination) {
		this.currentDestination = currentDestination;
	}
	
	
}
