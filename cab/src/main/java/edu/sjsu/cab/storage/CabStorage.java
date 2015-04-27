package edu.sjsu.cab.storage;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="user")
@NamedQueries({
	@NamedQuery(name="CabStorage.findByUserUuid", query="SELECT b FROM user b where b.userId = :userId"),
})

public class CabStorage {
	@Id
    @Column(name = "userId", unique = true, nullable = false, length = 50)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;
 	
 	 @Column(name = "cabTransactionId", unique = true, nullable = true, length = 100)
     private String cabTransactionId;
 	 
 	 @Column(name = "email", unique = true, nullable = true, length = 100)
     private String email;
 	 
 	 @Column(name = "firstName", unique = true, nullable = true, length = 100)
     private String firstName;
 	 
 	 @Column(name = "lastName", unique = true, nullable = true, length = 100)
     private String lastName;
 	 
 	 @Column(name = "isDyamicRoute", unique = true, nullable = true, length = 100)
     private String isDyamicRoute;
 	 
 	 @Column(name = "password", unique = true, nullable = true, length = 100)
     private String password;
 	 
 	 @Column(name = "pickupLocationLat", unique = true, nullable = true, length = 100)
     private String pickupLocationLat;
 	 
 	@Column(name = "pickupLocationLong", unique = true, nullable = true, length = 100)
    private String pickupLocationLong;
 	
 	@Column(name = "destLocationLat", unique = true, nullable = true, length = 100)
    private String destLocationLat;
 	
 	@Column(name = "destLocationLong", unique = true, nullable = true, length = 100)
    private String destLocationLong;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCabTransactionId() {
		return cabTransactionId;
	}

	public void setCabTransactionId(String cabTransactionId) {
		this.cabTransactionId = cabTransactionId;
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

	public String getIsDyamicRoute() {
		return isDyamicRoute;
	}

	public void setIsDyamicRoute(String isDyamicRoute) {
		this.isDyamicRoute = isDyamicRoute;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPickupLocationLat() {
		return pickupLocationLat;
	}

	public void setPickupLocationLat(String pickupLocationLat) {
		this.pickupLocationLat = pickupLocationLat;
	}

	public String getPickupLocationLong() {
		return pickupLocationLong;
	}

	public void setPickupLocationLong(String pickupLocationLong) {
		this.pickupLocationLong = pickupLocationLong;
	}

	public String getDestLocationLat() {
		return destLocationLat;
	}

	public void setDestLocationLat(String destLocationLat) {
		this.destLocationLat = destLocationLat;
	}

	public String getDestLocationLong() {
		return destLocationLong;
	}

	public void setDestLocationLong(String destLocationLong) {
		this.destLocationLong = destLocationLong;
	}
}
