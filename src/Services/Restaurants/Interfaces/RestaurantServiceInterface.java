package Services.Restaurants.Interfaces;

import Orders.Order;
import Products.Product;
import Restaurants.DeliveryAgent;
import Restaurants.Restaurant;

public interface RestaurantServiceInterface {

    public Restaurant GetRestaurantById(int id);

    public Product GetProductById(int id, int idProduct);

    public void ShowProducts(int id);

    public void ShowProductsOrderedAscByPrice(int id);

    public void ShowProductsOrderedDescByPrice(int id);

    public void AddProduct(int id, Product product);

    public void AddDeliveryAgent(int id, DeliveryAgent deliveryAgent);

    public void AddOrder(int id, Order order);

    public void AddRestaurant(Restaurant restaurant);

    public int ReadNewRestaurant();

    public Restaurant GetNewRestaurant();

    public void ShowAllDeliveryAgents(int id);


}
