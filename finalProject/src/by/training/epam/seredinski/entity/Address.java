package by.training.epam.seredinski.entity;

import java.io.Serializable;

public class Address implements Serializable {

    private static final long serialVersionUID = 4535859720132168340L;

    private int id;
    private String city;
    private String street;
    private int house;
    private int flat;
    private int userId;

    public Address(String city, String street, int house, int flat, int userId) {
        this.city = city;
        this.street = street;
        this.house = house;
        this.flat = flat;
        this.userId = userId;
    }

    public Address(int id, String city, String street, int house, int flat, int userId) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.house = house;
        this.flat = flat;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public int getFlat() {
        return flat;
    }

    public void setFlat(int flat) {
        this.flat = flat;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
