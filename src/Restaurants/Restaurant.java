package Restaurants;

import Orders.Order;
import Products.Product;

import java.util.List;
import java.util.ArrayList;

public class Restaurant {

    private static int idCounter = 0;

    private int id;
    private String address;

    private List<Product> productList;
    private List<DeliveryAgent> deliveryAgentList;
    private List<Order> orderHistoryList;

    public Restaurant(String address) {
        this.id = idCounter++;
        this.address = address;
        productList = new ArrayList();
        deliveryAgentList = new ArrayList();
        orderHistoryList = new ArrayList();
    }

    public Restaurant(String address, List<Product> productList, List<DeliveryAgent> deliveryAgentList) {
        this.id = idCounter++;
        this.address = address;
        this.productList = productList;
        this.deliveryAgentList = deliveryAgentList;
    }

    // copy constructor

    public Restaurant(Restaurant restaurant) {
        this.id = restaurant.id;
        this.address = restaurant.address;
        this.productList = restaurant.productList;
        this.deliveryAgentList = restaurant.deliveryAgentList;
        this.orderHistoryList = restaurant.orderHistoryList;
    }

    @Override
    public String toString() {
        String returnMessage = "Restaurant with id " + this.id + " and address " + this.address + " has the following products:\n";
        for (Product product : this.productList)
            returnMessage += product.toString() + "\n";
        returnMessage += "and the following delivery agents:\n";
        for (DeliveryAgent deliveryAgent : this.deliveryAgentList)
            returnMessage += deliveryAgent.toString() + "\n";

        return returnMessage;
    }

    public static int GetIdCounter() {
        return idCounter;
    }

    public int GetId() {
        return id;
    }

    public String GetAddress() {
        return address;
    }

    public void SetAddress(String address) {
        this.address = address;
    }

    public List<Product> GetProductList() {
        return productList;
    }

    public void SetProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<DeliveryAgent> GetDeliveryAgentList() {
        return deliveryAgentList;
    }

    public void SetDeliveryAgentList(List<DeliveryAgent> deliveryAgentList) {
        this.deliveryAgentList = deliveryAgentList;
    }

    public List<Order> GetOrderHistoryList() {
        return orderHistoryList;
    }

    public void SetOrderHistoryList(List<Order> orderHistoryList) {
        this.orderHistoryList = orderHistoryList;
    }
}
