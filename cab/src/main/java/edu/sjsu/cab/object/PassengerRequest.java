package edu.sjsu.cab.object;

import com.google.maps.model.LatLng;

public class PassengerRequest {
	
	String originAddress;
	String destinationAddress;
	LatLng originLL;
	LatLng destinationLL;
	int numOfPassengers;
	
	public PassengerRequest(String originAddress, String destinationAddress,
			LatLng originLL, LatLng destinationLL, int numOfPassengers) {
		super();
		this.originAddress = originAddress;
		this.destinationAddress = destinationAddress;
		this.originLL = originLL;
		this.destinationLL = destinationLL;
		this.numOfPassengers = numOfPassengers;
	}
	public String getOriginAddress() {
		return originAddress;
	}
	public void setOriginAddress(String originAddress) {
		this.originAddress = originAddress;
	}
	public String getDestinationAddress() {
		return destinationAddress;
	}
	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}
	public LatLng getOriginLL() {
		return originLL;
	}
	public void setOriginLL(LatLng originLL) {
		this.originLL = originLL;
	}
	public LatLng getDestinationLL() {
		return destinationLL;
	}
	public void setDestinationLL(LatLng destinationLL) {
		this.destinationLL = destinationLL;
	}
	public int getNumOfPassengers() {
		return numOfPassengers;
	}
	public void setNumOfPassengers(int numOfPassengers) {
		this.numOfPassengers = numOfPassengers;
	}
	
	

	
}
