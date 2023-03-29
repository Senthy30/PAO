package Services.Orders;

import Orders.Order;
import Products.Comparator.ProductComparatorAscByPrice;
import Products.Product;
import Services.Orders.Interfaces.OrderServiceInterface;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderService implements OrderServiceInterface {

    private List<Order> ordersList = new ArrayList();
    private static OrderService instance;

    private OrderService() { }

    public static OrderService getInstance(){
        if(instance == null){
            instance = new OrderService();
        }
        return instance;
    }

    @Override
    public Order GetOrderById(int id) {
        return ordersList.get(id);
    }

    @Override
    public void ShowProducts(int id) {
        Order order = GetOrderById(id);

        System.out.println("Products in order " + order.GetId() + " are:");
        for(Product product : order.GetProducts())
            System.out.println(product.GetId() + ". " + product.GetFoodName());
        System.out.println();
    }

    @Override
    public void ShowProductsOrderedAscByPrice(int id) {
        Order order = GetOrderById(id);

        List <Product> sortedProducts = new ArrayList<>(order.GetProducts());
        Collections.sort(sortedProducts, new ProductComparatorAscByPrice());
        System.out.println("Products in order " + order.GetId() + " are: ");
        for(Product product : sortedProducts)
            System.out.println(product);
        System.out.println();
    }

    @Override
    public void ShowProductsOrderedDescByPrice(int id) {
        Order order = GetOrderById(id);

        List <Product> sortedProducts = new ArrayList<>(order.GetProducts());
        Collections.sort(sortedProducts, new ProductComparatorAscByPrice());
        Collections.reverse(sortedProducts);
        System.out.println("Products in order " + order.GetId() + " are: ");
        for(Product product : sortedProducts)
            System.out.println(product);
        System.out.println();
    }

    @Override
    public void CalculateTotalPrice(int id) {
        Order order = GetOrderById(id);

        double totalPrice = 0;
        for(Product product : order.GetProducts())
            totalPrice += product.GetPrice();
        order.SetTotalPrice(totalPrice);
    }

    @Override
    public void AddProduct(int id, Product product) {
        Order order = GetOrderById(id);

        order.GetProducts().add(product);
        CalculateTotalPrice(id);
    }

    @Override
    public void RemoveProductById(int id, int idRemove) {
        Order order = GetOrderById(id);

        for(Product product : order.GetProducts()){
            if(product.GetId() == idRemove){
                order.GetProducts().remove(product);
                CalculateTotalPrice(id);
                return;
            }
        }
        System.out.println("Product with id " + idRemove + " not found!");
    }

    @Override
    public void ShowDescriptionOfProductById(int id, int idRemove) {
        Order order = GetOrderById(id);

        for(Product product : order.GetProducts()){
            if(product.GetId() == idRemove){
                System.out.println(product);
                return;
            }
        }
        System.out.println("Product with id " + idRemove + " not found!");
    }

    @Override
    public int ReadNewOrder(int idRestaurant){
        Scanner myInput = new Scanner( System.in );

        boolean isDelivered = false;
        int idDeliveryAgent = -1;

        System.out.println("Do you want to deliver the order at home? (1/0)");
        if(myInput.nextInt() == 1)
            isDelivered = true;

        Order order = new Order(idRestaurant, isDelivered);
        ordersList.add(order);

        return ordersList.size() - 1;
    }

    @Override
    public Order GetNewOrder(int idRestaurant){
        Order order = GetOrderById(ReadNewOrder(idRestaurant));

        return order;
    }

    public int GetLength(){
        return ordersList.size();
    }

}
