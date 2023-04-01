package Services.Orders.Interfaces;

import Orders.Order;
import Products.Product;

import java.io.IOException;

public interface OrderServiceInterface {

    public void ShowProducts(int id);

    public void ShowProductsOrderedAscByPrice(int id);

    public void ShowProductsOrderedDescByPrice(int id);

    public void CalculateTotalPrice(int id);

    public void AddProduct(int id, Product product);

    public void RemoveProductById(int id, int idRemove);

    public void ShowDescriptionOfProductById(int id, int idRemove);

    public Order GetOrderById(int id);

    public int ReadNewOrder(int idRestaurant, int idDeliveryAgent, boolean deliveredHome);

    public Order GetNewOrder(int idRestaurant, int idDeliveryAgent, boolean deliveredHome);

}
