package edu.sjsu.cab.storage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "user")
//@NamedQueries({ @NamedQuery(name = "CabStorage.findByUserUuid", query = "SELECT b FROM user b where b.userId = :userId"), })
public class User {
    @Id
    @Column(name = "userId", unique = true, nullable = false, length = 50)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;

    @Column(name = "transactionId", unique = true, nullable = true, length = 100)
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
    private Float pickupLocationLat;

    @Column(name = "pickupLocationLong", unique = true, nullable = true, length = 100)
    private Float pickupLocationLong;

    @Column(name = "destLocationLat", unique = true, nullable = true, length = 100)
    private Float destLocationLat;

    @Column(name = "destLocationLong", unique = true, nullable = true, length = 100)
    private Float destLocationLong;
    
    @Column(name = "isPicked", unique = true, nullable = true, length = 100)
    private String isPicked;

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

    public Float getPickupLocationLat() {
        return pickupLocationLat;
    }

    public void setPickupLocationLat(Float pickupLocationLat) {
        this.pickupLocationLat = pickupLocationLat;
    }

    public Float getPickupLocationLong() {
        return pickupLocationLong;
    }

    public void setPickupLocationLong(Float pickupLocationLong) {
        this.pickupLocationLong = pickupLocationLong;
    }

    public Float getDestLocationLat() {
        return destLocationLat;
    }

    public void setDestLocationLat(Float destLocationLat) {
        this.destLocationLat = destLocationLat;
    }

    public Float getDestLocationLong() {
        return destLocationLong;
    }

    public void setDestLocationLong(Float destLocationLong) {
        this.destLocationLong = destLocationLong;
    }

    public String getIsPicked() {
        return isPicked;
    }

    public void setIsPicked(String isPicked) {
        this.isPicked = isPicked;
    }
    
    
}
