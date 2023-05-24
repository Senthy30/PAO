package Services.Orders;

import Orders.Order;
import Products.Comparator.ProductComparatorAscByPrice;
import Products.Product;
import Repositories.Orders.OrdersRepository;
import Repositories.Products.ProductRepository;
import Repositories.Users.UserRepository;
import Services.Orders.Interfaces.OrderServiceInterface;
import Users.User;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderService implements OrderServiceInterface {

    //private List<Order> ordersList = new ArrayList();
    private OrdersRepository ordersRepository = new OrdersRepository();
    private ProductRepository productRepository = new ProductRepository();
    private static OrderService instance;

    private OrderService() { }

    public static OrderService getInstance(){
        if(instance == null){
            instance = new OrderService();
        }
        return instance;
    }

    public void SetProductsForOrder(Order order){
        String productsAsString = order.GetProductsAsString();

        String[] productsIds = productsAsString.split(" ");
        List<Product> products = new ArrayList<>();

        for(String productId : productsIds){
            if(productId.equals(""))
                continue;
            products.add(productRepository.Select(Integer.parseInt(productId)));
        }

        order.SetProducts(products);
    }

    @Override
    public Order GetOrderById(int id) {
        Order order = ordersRepository.Select(id);
        SetProductsForOrder(order);

        return order;
    }

    @Override
    public void ShowProducts(int id) {
        Order order = GetOrderById(id);

        System.out.println("Products in order " + order.GetId() + " are:");
        int currentIndex = 1;
        for(Product product : order.GetProducts()){
            System.out.println(currentIndex + ". " + product.GetFoodName() + "of weight 300g" + " with price " + product.GetPrice() + " RON");
            currentIndex++;
        }
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
    public void CalculateTotalPrice(Order order, int id) {
        double totalPrice = 0;
        for(Product product : order.GetProducts())
            totalPrice += product.GetPrice();
        order.SetTotalPrice(totalPrice);
    }

    @Override
    public void AddProduct(int id, Product product) {
        Order order = GetOrderById(id);

        order.GetProducts().add(product);
        CalculateTotalPrice(order, id);

        String productsAsString = order.GetProductsAsString();
        productsAsString += " " + product.GetId();
        order.SetProductsAsString(productsAsString);

        ordersRepository.Update(order, id);
    }

    @Override
    public void RemoveProductById(int id, int idRemove) {
        Order order = GetOrderById(id);

        for(Product product : order.GetProducts()){
            if(product.GetId() == idRemove){
                order.GetProducts().remove(product);
                CalculateTotalPrice(order, id);
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
    public int ReadNewOrder(int idRestaurant, int idDeliveryAgent, int idUser, boolean deliveredHome) {
        Scanner myInput = new Scanner( System.in );

        boolean isDelivered = false;

        isDelivered = deliveredHome;

        int id = ordersRepository.GetIdCounter();
        Order order = new Order(id, idRestaurant, idUser, isDelivered);

        ordersRepository.Insert(order);

        return id;
    }

    @Override
    public Order GetNewOrder(int idRestaurant, int idDeliveryAgent, int idUser, boolean deliveredHome){
        Order order = GetOrderById(ReadNewOrder(idRestaurant, idDeliveryAgent, idUser, deliveredHome));

        return order;
    }
}
