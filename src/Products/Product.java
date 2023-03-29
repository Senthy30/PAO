package Products;

import java.time.LocalDate;

public class Product {

    protected static int idCounter = 0;

    private int id;
    private int idRestaurant;


    protected int weight;
    protected int calories;
    protected double price;
    protected LocalDate expirationDate;
    protected String foodName;
    protected String description;

    public Product(int idRestaurant){
        this.id = idCounter++;
        this.idRestaurant = idRestaurant;
    }

    public Product(int idRestaurant, int weight, int calories, double price, LocalDate expirationDate, String foodName, String description) {
        this.id = idCounter++;
        this.idRestaurant = idRestaurant;
        this.weight = weight;
        this.calories = calories;
        this.price = price;
        this.expirationDate = expirationDate;
        this.foodName = foodName;
        this.description = description;
    }

    public Product(int idRestaurant, int weight, int calories, double price, LocalDate expirationDate) {
        this.id = idCounter++;
        this.idRestaurant = idRestaurant;
        this.weight = weight;
        this.calories = calories;
        this.price = price;
        this.expirationDate = expirationDate;
    }

    public Product(Product product) {
        this.id = product.id;
        this.idRestaurant = product.idRestaurant;
        this.weight = product.weight;
        this.calories = product.calories;
        this.price = product.price;
        this.expirationDate = product.expirationDate;
        this.foodName = product.foodName;
        this.description = product.description;
    }

    @Override
    public String toString() {
        return foodName + " with id " + id + " of weight " + weight + "g, " + "at a price of " + price + " RON";
    }

    public static int GetIdCounter() {
        return idCounter;
    }

    public int GetId() {
        return id;
    }

    public int GetIdRestaurant() {
        return idRestaurant;
    }

    public int GetWeight() {
        return weight;
    }

    public void SetWeight(int weight) {
        this.weight = weight;
    }

    public int GetCalories() {
        return calories;
    }

    public void SetCalories(int calories) {
        this.calories = calories;
    }

    public double GetPrice() {
        return price;
    }

    public void SetPrice(double price) {
        this.price = price;
    }

    public LocalDate GetExpirationDate() {
        return expirationDate;
    }

    public void SetExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String GetFoodName() {
        return foodName;
    }

    public void SetFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String GetDescription() {
        return description;
    }

    public void SetDescription(String description) {
        this.description = description;
    }
}
