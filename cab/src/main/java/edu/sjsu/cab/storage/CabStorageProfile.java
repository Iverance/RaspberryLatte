package edu.sjsu.cab.storage;

public class CabStorageProfile {

	private Long userId;
	 private String transactionId;
	 private String email;
	 private String firstName;
	 private String lastName;
	 private char isDynamicRoute;
	 private String password;
	 private double pickupLocationLat;
	 private double pickupLocationLong;
	 private double destLocationLat;
	 private double destLocationLong;
	 private String userName;
	 
	 public CabStorageProfile()
	    {}
	 
	 public CabStorageProfile(Long userId, String transactionId, String email,
			String firstName, String lastName, char isDynamicRoute,
			String password, double pickupLocationLat,
			double pickupLocationLong, double destLocationLat,
			double destLocationLong, String userName) {
		super();
		this.userId = userId;
		this.transactionId = transactionId;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.isDynamicRoute = isDynamicRoute;
		this.password = password;
		this.pickupLocationLat = pickupLocationLat;
		this.pickupLocationLong = pickupLocationLong;
		this.destLocationLat = destLocationLat;
		this.destLocationLong = destLocationLong;
		this.userName = userName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public char getIsDynamicRoute() {
		return isDynamicRoute;
	}

	public void setIsDynamicRoute(char isDynamicRoute) {
		this.isDynamicRoute = isDynamicRoute;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getPickupLocationLat() {
		return pickupLocationLat;
	}

	public void setPickupLocationLat(double pickupLocationLat) {
		this.pickupLocationLat = pickupLocationLat;
	}

	public double getPickupLocationLong() {
		return pickupLocationLong;
	}

	public void setPickupLocationLong(double pickupLocationLong) {
		this.pickupLocationLong = pickupLocationLong;
	}

	public double getDestLocationLat() {
		return destLocationLat;
	}

	public void setDestLocationLat(double destLocationLat) {
		this.destLocationLat = destLocationLat;
	}

	public double getDestLocationLong() {
		return destLocationLong;
	}

	public void setDestLocationLong(double destLocationLong) {
		this.destLocationLong = destLocationLong;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}