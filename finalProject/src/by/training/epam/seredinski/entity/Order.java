package by.training.epam.seredinski.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedHashSet;

public class Order implements Serializable {
    private static final long serialVersionUID = 4905859720132168340L;

    public enum OrderStatus {
        NEW, DONE
    }

    private int id;
    private Calendar dateTime;
    private OrderStatus status = OrderStatus.NEW;
    private int userId;
    private int addressId;
    private LinkedHashSet<Dish> dishes;
    private int orderPrice;

    public Order() {
    }

    public Order(int id, Calendar dateTime, int userId, int addressId) {
        this.id = id;
        this.dateTime = dateTime;
        this.userId = userId;
        this.addressId = addressId;
    }

    public Order(Calendar dateTime, int userId, int addressId, LinkedHashSet<Dish> dishes) {
        this.dateTime = dateTime;
        this.userId = userId;
        this.addressId = addressId;
        this.dishes = dishes;
    }

    public Order(int id, Calendar dateTime, int userId, int addressId, LinkedHashSet<Dish> dishes) {
        this.id = id;
        this.dateTime = dateTime;
        this.userId = userId;
        this.addressId = addressId;
        this.dishes = dishes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getDateTime() {
        return dateTime;
    }

    public void setDateTime(Calendar dateTime) {
        this.dateTime = dateTime;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public LinkedHashSet<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(LinkedHashSet<Dish> dishes) {
        this.dishes = dishes;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }
}
