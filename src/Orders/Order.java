package Orders;

import Products.Comparator.ProductComparatorAscByPrice;
import Products.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.time.LocalDate;

public class Order {

    private static final int minOrderValue = 100;

    // protected static int idCounter = 0;

    private boolean isDelivered;
    private int id;
    private int idRestaurant;
    private int idDeliveryAgent;
    private int idUser;
    private double totalPrice;
    private LocalDate date;
    private List<Product> products;
    private String productsAsString;

    public Order(){
        //this.id = idCounter++;
        products = new ArrayList();
    }

    public Order(int id, int idRestaurant, int idDeliveryAgent, int idUser, boolean isDelivered, double totalPrice, LocalDate date, String products) {
        this.id = id;
        this.idRestaurant = idRestaurant;
        this.idDeliveryAgent = idDeliveryAgent;
        this.idUser = idUser;
        this.isDelivered = isDelivered;
        this.totalPrice = totalPrice;
        this.date = date;
        this.products = new ArrayList();
        this.productsAsString = products;
        if(this.productsAsString == null)
            this.productsAsString = "";
        //this.products = products;
    }

    public Order(int idRestaurant){
        //this.id = idCounter++;
        this.idRestaurant = idRestaurant;
        products = new ArrayList();

        date = LocalDate.now();
    }

    public Order(int id, int idRestaurant, int idUser, boolean isDelivered){
        this.id = id;
        this.idRestaurant = idRestaurant;
        this.idUser = idUser;
        this.isDelivered = isDelivered;
        this.idDeliveryAgent = -1;
        this.totalPrice = 0;
        products = new ArrayList();

        date = LocalDate.now();
    }

    public Order(int idRestaurant, List<Product> products) {
        //this.id = idCounter++;
        this.idRestaurant = idRestaurant;
        Collections.copy(this.products, products);
        this.isDelivered = false;
        this.idDeliveryAgent = -1;

        date = LocalDate.now();
    }

    public Order(int idRestaurant, List<Product> products, int idDeliveryAgent) {
        //this.id = idCounter++;
        this.idRestaurant = idRestaurant;
        Collections.copy(this.products, products);
        this.isDelivered = true;
        this.totalPrice += this.totalPrice >= minOrderValue ? 0 : 15;
        this.idDeliveryAgent = idDeliveryAgent;

        date = LocalDate.now();
    }

    public Order(Order order){
        this.id = order.id;
        this.idRestaurant = order.idRestaurant;
        Collections.copy(this.products, order.products);
        this.isDelivered = order.isDelivered;
        this.totalPrice = order.totalPrice;
        this.date = order.date;
    }

    @Override
    public String toString() {
        String returnMesage = "Order with id " + this.id + " from restaurant with id " + this.idRestaurant + " with a total price of " + this.totalPrice + " RON ";
        if(isDelivered)
            returnMesage += "delivered home ";

        returnMesage += "and containing the following products:\n";
        for(Product product : this.products)
            returnMesage += product.toString() + "\n";

        return returnMesage;
    }

    public int GetIdUser() {
        return idUser;
    }

    public void SetIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int GetId() {
        return id;
    }

    public int GetIdRestaurant() {
        return idRestaurant;
    }

    public int GetIdDeliveryAgent() {
        return idDeliveryAgent;
    }

    public boolean IsDelivered() {
        return isDelivered;
    }

    public void SetDelivered(boolean delivered) {
        isDelivered = delivered;
    }

    public double GetTotalPrice() {
        return totalPrice;
    }

    public void SetTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate GetDate() {
        return date;
    }

    public void SetDate(LocalDate date) {
        this.date = date;
    }

    public List<Product> GetProducts() {
        return products;
    }

    public void SetProducts(List<Product> products) {
        this.products = products;
    }

    public String GetProductsAsString(){
        return productsAsString;
    }

    public void SetProductsAsString(String productsAsString){
        this.productsAsString = productsAsString;
    }
}
