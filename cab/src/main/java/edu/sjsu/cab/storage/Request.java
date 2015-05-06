package edu.sjsu.cab.storage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "request")
public class Request {
    @Id
    @Column(name = "requestId", unique = true, nullable = false, length = 50)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String requestId;

    @Column(name = "transactionId", unique = true, nullable = true, length = 100)
    private String transactionId;

    @Column(name = "email", unique = true, nullable = true, length = 100)
    private String email;

    @Column(name = "firstName", unique = true, nullable = true, length = 100)
    private String firstName;

    @Column(name = "lastName", unique = true, nullable = true, length = 100)
    private String lastName;

    @Column(name = "isDynamicRoute", unique = true, nullable = true, length = 100)
    private char isDynamicRoute;

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
    private char isPicked;
    
    @Column(name = "userName", unique = true, nullable = false, length = 100)
    private String userName;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getCabTransactionId() {
        return transactionId;
    }

    public void setCabTransactionId(String cabTransactionId) {
        this.transactionId = cabTransactionId;
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

    public char getIsPicked() {
        return isPicked;
    }

    public void setIsPicked(char isPicked) {
        this.isPicked = isPicked;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public char getIsDynamicRoute() {
        return isDynamicRoute;
    }

    public void setIsDynamicRoute(char isDynamicRoute) {
        this.isDynamicRoute = isDynamicRoute;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    
}
