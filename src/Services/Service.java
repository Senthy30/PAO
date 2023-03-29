package Services;

import Orders.Order;
import Products.*;
import Restaurants.DeliveryAgent;
import Restaurants.Restaurant;
import Services.Orders.DeliveryAgentService;
import Services.Orders.OrderService;
import Services.Products.*;
import Services.Restaurants.RestaurantService;
import Services.Users.UserService;
import Users.User;

import java.time.LocalDate;
import java.util.Scanner;

public class Service implements ServiceInterface {

    private static Service instance = null;

    private UserService userService = UserService.getInstance();

    private RestaurantService restaurantService = RestaurantService.getInstance();
    private DeliveryAgentService deliveryAgentService = DeliveryAgentService.getInstance();

    private OrderService orderService = OrderService.getInstance();
    private ProductService productService = ProductService.getInstance();

    private HamburgerService hamburgerService = HamburgerService.getInstance();
    private PizzaService pizzaService = PizzaService.getInstance();
    private ShaormaService shaormaService = ShaormaService.getInstance();

    private WaterService waterService = WaterService.getInstance();
    private JuiceService juiceService = JuiceService.getInstance();


    private Service() { }

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }

    @Override
    public Restaurant GetRestaurantById(int id){
        return restaurantService.GetRestaurantById(id);
    }

    @Override
    public void ShowAllRestaurants(){
        restaurantService.ShowAllRestaurants();
    }

    @Override
    public User GetUserById(int id){
        return userService.GetUserById(id);
    }

    @Override
    public Order GetOrderById(int id){
        return orderService.GetOrderById(id);
    }

    @Override
    public void CreateNewRestaurant(){
        Restaurant restaurant = restaurantService.GetNewRestaurant();
        Product product;

        System.out.println("Enter the information for hambruger: ");
        product = productService.GetNewProduct(restaurant.GetId());
        Hamburger hamburger = new Hamburger(product);
        hamburger.SetFoodName();

        System.out.println("Enter the information for pizza: ");
        product = productService.GetNewProduct(restaurant.GetId());
        Pizza pizza = new Pizza(product);
        pizza.SetFoodName();

        System.out.println("Enter the information for shaorma: ");
        product = productService.GetNewProduct(restaurant.GetId());
        Shaorma shaorma = new Shaorma(product);
        shaorma.SetFoodName();

        System.out.println("Enter the information for juice: ");
        product = productService.GetNewProduct(restaurant.GetId());
        Juice juice = new Juice(product);
        juice.SetFoodName();

        System.out.println("Enter the information for water: ");
        product = productService.GetNewProduct(restaurant.GetId());
        Water water = new Water(product);
        water.SetFoodName();

        restaurantService.AddProduct(restaurant.GetId(), hamburger);
        restaurantService.AddProduct(restaurant.GetId(), pizza);
        restaurantService.AddProduct(restaurant.GetId(), shaorma);
        restaurantService.AddProduct(restaurant.GetId(), juice);
        restaurantService.AddProduct(restaurant.GetId(), water);
    }

    @Override
    public Order CreateNewOrder(int idRestaurant){
        Restaurant restaurant = restaurantService.GetRestaurantById(idRestaurant);
        Order order = orderService.GetNewOrder(idRestaurant);
        Scanner myInput = new Scanner( System.in );

        int type = -1;
        while(type != 0){
            System.out.println("0. Exit");
            restaurantService.ShowProducts(idRestaurant);

            System.out.println("Enter the type of the product: ");
            type = myInput.nextInt();

            if(type < 0 || type > 5){
                System.out.println("Invalid type!");
                continue;
            } else if(type == 0){
                break;
            }

            Product product = restaurantService.GetProductById(idRestaurant, type - 1);
            Product productType;

            if(type == 1){
                productType = hamburgerService.GetNewHamburger(product);
            } else if(type == 2){
                productType = pizzaService.GetNewPizza(product);
            } else if(type == 3){
                productType = shaormaService.GetNewShaorma(product);
            } else if(type == 4){
                productType = juiceService.GetNewJuice(product);
            } else {
                productType = waterService.GetNewWater(product);
            }

            orderService.AddProduct(order.GetId(), productType);
        }

        return orderService.GetOrderById(order.GetId());
    }

    @Override
    public void CreateNewUser(){
        userService.GetNewUser();
    }

    @Override
    public void ShowProductInRestaurant(int idRestaurant){
        restaurantService.ShowProducts(idRestaurant);
    }

    @Override
    public void ShowProductsInRestaurantOrderedAscByPrice(int idRestaurant){
        restaurantService.ShowProductsOrderedAscByPrice(idRestaurant);
    }

    @Override
    public void ShowProductsInRestaurantOrderedDescByPrice(int idRestaurant){
        restaurantService.ShowProductsOrderedDescByPrice(idRestaurant);
    }

    @Override
    public void ShowProductsInOrder(int idOrder){
        orderService.ShowProducts(idOrder);
    }

    @Override
    public void ShowProductsInOrderOrderedAscByPrice(int idOrder){
        orderService.ShowProductsOrderedAscByPrice(idOrder);
    }

    @Override
    public void ShowProductsInOrderOrderedDescByPrice(int idOrder){
        orderService.ShowProductsOrderedDescByPrice(idOrder);
    }

    @Override
    public void ShowHistoryOfOrdersForUser(int idUser){
        userService.ShowHistoryOfOrdersForUser(idUser);
    }

    @Override
    public void ShowProductsFromOrderByIndex(int idUser, int index){
        int idOrder = userService.GetUserById(idUser).GetOrderHistory().get(index).GetId();

        orderService.ShowProducts(idOrder);
    }

    @Override
    public void ShowProductsFromOrdersAscByPrice(int idUser, int index){
        int idOrder = userService.GetUserById(idUser).GetOrderHistory().get(index).GetId();

        orderService.ShowProductsOrderedAscByPrice(idOrder);
    }

    @Override
    public void ShowProductsFromOrdersDescByPrice(int idUser, int index){
        int idOrder = userService.GetUserById(idUser).GetOrderHistory().get(index).GetId();

        orderService.ShowProductsOrderedDescByPrice(idOrder);
    }

    @Override
    public void ShowMostExpensiveProductsFromOrders(int idUser){
        userService.ShowMostExpensiveProductsFromOrders(idUser);
    }

    @Override
    public void CheckIfUserOrderedSomethingOnDate(int idUser, LocalDate date){
        userService.CheckIfUserOrderedSomethingOnDate(idUser, date);
    }

    @Override
    public void ChangeAdress(int idUser, String address){
        userService.GetUserById(idUser).SetAddress(address);
    }

    @Override
    public void ChangePhoneNumber(int idUser, String phoneNumer){
        userService.GetUserById(idUser).SetPhoneNumber(phoneNumer);
    }

    @Override
    public void ChangeEmail(int idUser, String email){
        userService.GetUserById(idUser).SetEmail(email);
    }

    @Override
    public void ShowAccountInformationAboutUser(int idUser){
        userService.ShowAccountInformationAboutUser(idUser);
    }

    @Override
    public void ShowProductsInRestaurantAscByPrice(int idRestaurant){
        restaurantService.ShowProductsOrderedAscByPrice(idRestaurant);
    }

    @Override
    public void ShowProductsInRestaurantDescByPrice(int idRestaurant){
        restaurantService.ShowProductsOrderedDescByPrice(idRestaurant);
    }

    @Override
    public void ShowDeliveryAgentInRestaurantByIndex(int idRestaurant, int index){
        System.out.println(restaurantService.GetRestaurantById(idRestaurant).GetDeliveryAgentList().get(index));
    }

    @Override
    public void ShowAllDeliveryAgentsInRestaurant(int idRestaurant){
        restaurantService.ShowAllDeliveryAgents(idRestaurant);
    }

    @Override
    public void AddNewDeliveryAgentInRestaurant(int idRestaurant){
        DeliveryAgent deliveryAgent = deliveryAgentService.GetNewDeliveryAgent(idRestaurant);
        restaurantService.AddDeliveryAgent(idRestaurant, deliveryAgent);
    }

    @Override
    public void AddNewOrder(int idUser, Order order){
        userService.AddNewOrder(idUser, order);
        userService.MarkThisDateAsOrderDate(idUser, order.GetDate());
    }

    public int GetLengthOfUsers(){
        return userService.GetLength();
    }

    public int GetLengthOfRestaurants(){
        return restaurantService.GetLength();
    }
}
