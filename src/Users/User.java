package Users;

import Orders.Order;
import Products.Product;

import java.util.HashMap;
import java.util.List;
import java.time.LocalDate;
import java.util.Map;
import java.util.ArrayList;

public class User extends Person {

    private static int idCounter = 0;

    private int id;
    private String address;

    private List<Order> orderHistory;
    private List<Product> mostExpensiveProducts;
    private Map<LocalDate, Boolean> orderDates;

    public User(String firstName, String lastName, String phoneNumber, String email, String address){
        this.id = idCounter++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;

        orderHistory = new ArrayList();
        mostExpensiveProducts = new ArrayList();
        orderDates = new HashMap();
    }

    public User(User user){
        this.id = user.id;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.phoneNumber = user.phoneNumber;
        this.email = user.email;
        this.address = user.address;
        this.orderHistory = user.orderHistory;
        this.mostExpensiveProducts = user.mostExpensiveProducts;
        this.orderDates = user.orderDates;
    }

    @Override
    public String toString() {
        return "User with id " + this.id + " and name " + this.firstName + " " + this.lastName + " has the following order history:\n" + this.orderHistory.toString();
    }

    public static int GetIdCounter() {
        return idCounter;
    }

    public int GetId() {
        return id;
    }

    public void ShowAddress() {
        System.out.println("Address: " + this.address);
    }

    public String GetAddress() {
        return address;
    }

    public void SetAddress(String address) {
        this.address = address;
    }

    public List<Order> GetOrderHistory() {
        return orderHistory;
    }

    public void SetOrderHistory(List<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }

    public List<Product> GetMostExpensiveProducts() {
        return mostExpensiveProducts;
    }

    public void SetMostExpensiveProducts(List<Product> mostExpensiveProducts) {
        this.mostExpensiveProducts = mostExpensiveProducts;
    }

    public Map<LocalDate, Boolean> GetOrderDates() {
        return orderDates;
    }

    public void SetOrderDates(Map<LocalDate, Boolean> orderDates) {
        this.orderDates = orderDates;
    }
}
