package Services.Restaurants;

import Orders.Order;
import Products.Comparator.ProductComparatorAscByPrice;
import Products.Product;
import Restaurants.DeliveryAgent;
import Restaurants.Restaurant;
import Services.Restaurants.Interfaces.RestaurantServiceInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class RestaurantService implements RestaurantServiceInterface {

    private List<Restaurant> restaurantsList = new ArrayList();
    private static RestaurantService instance;

    private RestaurantService() { }

    public static RestaurantService getInstance(){
        if(instance == null){
            instance = new RestaurantService();
        }
        return instance;
    }

    @Override
    public Restaurant GetRestaurantById(int id) {
        if(id < 0 || id >= GetLength())
            return null;

        return restaurantsList.get(id);
    }

    @Override
    public Product GetProductById(int id, int idProduct){
        Restaurant restaurant = GetRestaurantById(id);

        return restaurant.GetProductList().get(idProduct);
    }

    @Override
    public void ShowProducts(int id) {
        Restaurant restaurant = GetRestaurantById(id);

        // System.out.println("Products in restaurant " + restaurant.GetId() + " are:");
        int currentIndex = 1;
        for(Product product : restaurant.GetProductList()) {
            System.out.println(currentIndex + ". " + product);
            currentIndex++;
        }
        System.out.println();
    }

    @Override
    public void ShowProductsOrderedAscByPrice(int id) {
        Restaurant restaurant = GetRestaurantById(id);

        List <Product> sortedProducts = new ArrayList<>(restaurant.GetProductList());
        Collections.sort(sortedProducts, new ProductComparatorAscByPrice());
        System.out.println("Products in restaurant " + restaurant.GetId() + " are: ");
        for(Product product : sortedProducts)
            System.out.println(product);
        System.out.println();
    }

    @Override
    public void ShowProductsOrderedDescByPrice(int id) {
        Restaurant restaurant = GetRestaurantById(id);

        List<Product> sortedProducts = new ArrayList<>(restaurant.GetProductList());
        Collections.sort(sortedProducts, new ProductComparatorAscByPrice());
        Collections.reverse(sortedProducts);
        System.out.println("Products in restaurant " + restaurant.GetId() + " are: ");
        for (Product product : sortedProducts)
            System.out.println(product);
        System.out.println();
    }
    @Override
    public void AddProduct(int id, Product product) {
        Restaurant restaurant = GetRestaurantById(id);
        restaurant.GetProductList().add(product);
    }

    @Override
    public void AddDeliveryAgent(int id, DeliveryAgent deliveryAgent) {
        Restaurant restaurant = GetRestaurantById(id);
        restaurant.GetDeliveryAgentList().add(deliveryAgent);
    }

    @Override
    public void AddOrder(int id, Order order) {
        Restaurant restaurant = GetRestaurantById(id);
        restaurant.GetOrderHistoryList().add(order);
    }

    @Override
    public void AddRestaurant(Restaurant restaurant) {
        restaurantsList.add(restaurant);
    }

    @Override
    public int ReadNewRestaurant() {
        Scanner myInput = new Scanner( System.in );

        String address;

        System.out.print("Enter the address of the restaurant: ");
        address = myInput.nextLine();

        Restaurant restaurant = new Restaurant(address);

        restaurantsList.add(restaurant);

        return restaurantsList.size() - 1;
    }

    @Override
    public Restaurant GetNewRestaurant() {
        Restaurant restaurant = GetRestaurantById(ReadNewRestaurant());

        return restaurant;
    }

    @Override
    public void ShowAllDeliveryAgents(int id) {
        Restaurant restaurant = GetRestaurantById(id);

        int currentIndex = 1;
        for(DeliveryAgent deliveryAgent : restaurant.GetDeliveryAgentList()) {
            System.out.println(currentIndex + ". " + deliveryAgent);
            currentIndex += 1;
        }
    }

    public void ShowAllRestaurants(){
        int currentIndex = 1;
        for(Restaurant restaurant : restaurantsList) {
            System.out.println(currentIndex + ". " + restaurant);
            currentIndex += 1;
        }
    }

    public int GetLength(){
        return restaurantsList.size();
    }

}
