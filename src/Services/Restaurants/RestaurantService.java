package Services.Restaurants;

import Orders.Order;
import Products.Comparator.ProductComparatorAscByPrice;
import Products.Product;
import Repositories.Orders.DeliveryAgentsRepository;
import Repositories.Products.ProductRepository;
import Repositories.Restaurants.RestaurantRepository;
import Restaurants.DeliveryAgent;
import Restaurants.Restaurant;
import Services.Restaurants.Interfaces.RestaurantServiceInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class RestaurantService implements RestaurantServiceInterface {

    //private List<Restaurant> restaurantsList = new ArrayList();
    private RestaurantRepository restaurantRepository = new RestaurantRepository();
    private ProductRepository productRepository = new ProductRepository();
    private DeliveryAgentsRepository deliveryAgentsRepository = new DeliveryAgentsRepository();
    private static RestaurantService instance;

    private final List<String> productNames = new ArrayList<String>() {{
        add("Hamburger");
        add("Shaorma");
        add("Pizza");
        add("Juice");
        add("Water");
    }};

    private RestaurantService() { }

    public static RestaurantService getInstance(){
        if(instance == null){
            instance = new RestaurantService();
        }
        return instance;
    }

    @Override
    public Restaurant GetRestaurantById(int id) {
        if(!restaurantRepository.CheckIfRestaurantExist(id))
            return null;

        Restaurant restaurant = restaurantRepository.Select(id);
        List <Product> products = new ArrayList<>();
        List <DeliveryAgent> deliveryAgents = deliveryAgentsRepository.SelectAll(id);

        products.add(productRepository.Select(restaurant.GetIdHamburger()));
        products.add(productRepository.Select(restaurant.GetIdShaorma()));
        products.add(productRepository.Select(restaurant.GetIdPizza()));
        products.add(productRepository.Select(restaurant.GetIdJuice()));
        products.add(productRepository.Select(restaurant.GetIdWater()));

        restaurant.SetProductList(products);

        restaurant.SetDeliveryAgentList(deliveryAgents);

        return restaurant;
    }

    @Override
    public Product GetProductById(int id, int type){
        String foodName = productNames.get(type);

        return productRepository.SelectByFoodName(id, foodName);
    }

    @Override
    public void ShowProducts(int id) {
        Restaurant restaurant = GetRestaurantById(id);

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
        restaurantRepository.Insert(restaurant);
    }

    @Override
    public int ReadNewRestaurant() {
        Scanner myInput = new Scanner( System.in );

        String address;

        System.out.print("Enter the address of the restaurant: ");
        address = myInput.nextLine();

        int idRestaurant = restaurantRepository.GetIdCounter();
        Restaurant restaurant = new Restaurant(idRestaurant, address);

        // System.out.println("id restaurant: " + idRestaurant);
        restaurantRepository.Insert(restaurant);

        return idRestaurant;
    }

    public void Update(Restaurant restaurant){
        restaurantRepository.Update(restaurant, restaurant.GetId());
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
        List <Integer> restaurantsList = restaurantRepository.SelectAll();

        for(Integer ids : restaurantsList) {
            System.out.println(currentIndex + ". " + GetRestaurantById(ids));
            currentIndex += 1;
        }
    }

    public Boolean CheckIfRestaurantExist(int id){
        return restaurantRepository.CheckIfRestaurantExist(id);
    }

}
