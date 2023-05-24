package Services;

import Orders.Order;
import Products.*;
import Repositories.Restaurants.RestaurantRepository;
import Restaurants.DeliveryAgent;
import Restaurants.Restaurant;
import Services.Audit.CSVService;
import Services.Orders.DeliveryAgentService;
import Services.Orders.OrderService;
import Services.Products.*;
import Services.Restaurants.RestaurantService;
import Services.Users.UserService;
import Users.User;

import java.io.IOException;
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

    private CSVService csvService = CSVService.getInstance();


    private Service() { }

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }

    @Override
    public Restaurant GetRestaurantById(int id){
        csvService.addLine("Select restaurant");
        return restaurantService.GetRestaurantById(id);
    }

    @Override
    public void ShowAllRestaurants(){
        csvService.addLine("Select all restaurants");
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
        int restaurantId = RestaurantRepository.GetIdCounter();
        Product product;

        System.out.println("Enter the information for hamburger: ");
        csvService.addLine("Create product hamburger");
        product = productService.GetNewProduct(restaurantId, "Hamburger");
        if(product == null)
            return;
        Hamburger hamburger = new Hamburger(product);
        hamburger.SetFoodName();

        System.out.println("Enter the information for pizza: ");
        csvService.addLine("Create product pizza");
        product = productService.GetNewProduct(restaurantId, "Pizza");
        if(product == null)
            return;
        Pizza pizza = new Pizza(product);
        pizza.SetFoodName();

        System.out.println("Enter the information for shaorma: ");
        csvService.addLine("Create product shaorma");
        product = productService.GetNewProduct(restaurantId, "Shaorma");
        if(product == null)
            return;
        Shaorma shaorma = new Shaorma(product);
        shaorma.SetFoodName();

        System.out.println("Enter the information for juice: ");
        csvService.addLine("Create product juice");
        product = productService.GetNewProduct(restaurantId, "Juice");
        if(product == null)
            return;
        Juice juice = new Juice(product);
        juice.SetFoodName();

        System.out.println("Enter the information for water: ");
        product = productService.GetNewProduct(restaurantId, "Water");
        if(product == null)
            return;
        Water water = new Water(product);
        water.SetFoodName();

        csvService.addLine("Create restaurant");
        Restaurant restaurant = restaurantService.GetNewRestaurant();

//        restaurantService.AddProduct(restaurant.GetId(), hamburger);
//        restaurantService.AddProduct(restaurant.GetId(), shaorma);
//        restaurantService.AddProduct(restaurant.GetId(), pizza);
//        restaurantService.AddProduct(restaurant.GetId(), juice);
//        restaurantService.AddProduct(restaurant.GetId(), water);

        restaurant.SetIdHamburger(hamburger.GetId());
        restaurant.SetIdShaorma(shaorma.GetId());
        restaurant.SetIdPizza(pizza.GetId());
        restaurant.SetIdJuice(juice.GetId());
        restaurant.SetIdWater(water.GetId());

        csvService.addLine("Update restaurant");
        restaurantService.Update(restaurant);
    }

    @Override
    public Order CreateNewOrder(int idRestaurant, int idUser){
        Restaurant restaurant = restaurantService.GetRestaurantById(idRestaurant);
        Order order = null;
        Scanner myInput = new Scanner( System.in );

        boolean isDelivered = false;
        System.out.print("Do you want to deliver the order at home? (1/0)");
        try {
            if(myInput.nextInt() == 1)
                isDelivered = true;
        } catch (Exception IOException) {
            System.out.println("Invalid input has been given!");

            return null;
        }

        int lengthDeliveryAgents = restaurantService.GetRestaurantById(idRestaurant).GetDeliveryAgentList().size();
        if(isDelivered && lengthDeliveryAgents == 0){
            System.out.println("There are no delivery agents for this restaurant!");
            return null;
        }

        int type = -1;
        int numberOfProducts = 0;
        while(type != 0){
            System.out.println("0. Finish order");
            restaurantService.ShowProducts(idRestaurant);

            System.out.print("Enter the type of the product: ");
            type = myInput.nextInt();

            if(type < 0 || type > 5){
                System.out.println("Invalid type!");
                continue;
            } else if(type == 0){
                break;
            }

            csvService.addLine("Create product");
            Product product = restaurantService.GetProductById(idRestaurant, type - 1);
            Product productType;

            if(type == 1){
                csvService.addLine("Create hamburger");
                productType = hamburgerService.GetNewHamburger(product);
            } else if(type == 3){
                csvService.addLine("Create pizza");
                productType = pizzaService.GetNewPizza(product);
            } else if(type == 2){
                csvService.addLine("Create shaorma");
                productType = shaormaService.GetNewShaorma(product);
            } else if(type == 4){
                csvService.addLine("Create juice");
                productType = juiceService.GetNewJuice(product);
            } else {
                csvService.addLine("Create water");
                productType = waterService.GetNewWater(product);
            }

            if(productType == null) {
                System.out.println("Choose again an option!");
                continue;
            }

            numberOfProducts++;
            if(numberOfProducts == 1) {
                int idDeliveryAgent = -1;
                if (isDelivered)
                    idDeliveryAgent = (int) (Math.random() * lengthDeliveryAgents);

                csvService.addLine("Create order");
                order = orderService.GetNewOrder(idRestaurant, idDeliveryAgent, idUser, isDelivered);
            }

            csvService.addLine("Update order");
            orderService.AddProduct(order.GetId(), productType);
        }

        if(order != null) {

            return orderService.GetOrderById(order.GetId());
        }

        return null;
    }

    @Override
    public void CreateNewUser(){
        csvService.addLine("Create user");
        userService.GetNewUser();
    }

    @Override
    public void ShowProductInRestaurant(int idRestaurant){
        csvService.addLine("Select products from restaurant");
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
        csvService.addLine("Select orders for user");
        userService.ShowHistoryOfOrdersForUser(idUser);
    }

    @Override
    public void ShowProductsFromOrderByIndex(int idUser, int index){

        int idOrder = 0;
        try {
            idOrder = userService.GetUserById(idUser).GetOrderHistory().get(index - 1).GetId();
        } catch (IndexOutOfBoundsException e){
            System.out.println("Invalid index!");

            return;
        }

        csvService.addLine("Select products from order");
        orderService.ShowProducts(idOrder);
    }

    @Override
    public void ShowProductsFromOrdersAscByPrice(int idUser, int index){
        int idOrder = 0;
        try {
            idOrder = userService.GetUserById(idUser).GetOrderHistory().get(index - 1).GetId();
        } catch (IndexOutOfBoundsException e){
            System.out.println("Invalid index!");

            return;
        }

        csvService.addLine("Select products from order");
        orderService.ShowProductsOrderedAscByPrice(idOrder);
    }

    @Override
    public void ShowProductsFromOrdersDescByPrice(int idUser, int index){
        int idOrder = 0;
        try{
            idOrder = userService.GetUserById(idUser).GetOrderHistory().get(index - 1).GetId();
        } catch (IndexOutOfBoundsException e){
            System.out.println("Invalid index!");

            return;
        }

        csvService.addLine("Select products from order");
        orderService.ShowProductsOrderedDescByPrice(idOrder);
    }

    @Override
    public void ShowMostExpensiveProductsFromOrders(int idUser){
        csvService.addLine("Select products from orders");
        userService.ShowMostExpensiveProductsFromOrders(idUser);
    }

    @Override
    public void CheckIfUserOrderedSomethingOnDate(int idUser, LocalDate date){
        csvService.addLine("Select orders on date for user");
        userService.CheckIfUserOrderedSomethingOnDate(idUser, date);
    }

    @Override
    public void ChangeAdress(int idUser, String address){
        csvService.addLine("Update user address");
        User user = userService.GetUserById(idUser);
        user.SetAddress(address);
        userService.Update(user, idUser);
    }

    @Override
    public void ChangePhoneNumber(int idUser, String phoneNumer){
        csvService.addLine("Update user phone number");
        User user = userService.GetUserById(idUser);
        user.SetPhoneNumber(phoneNumer);
        userService.Update(user, idUser);
    }

    @Override
    public void ChangeEmail(int idUser, String email){
        csvService.addLine("Update user email");
        User user = userService.GetUserById(idUser);
        user.SetEmail(email);
        userService.Update(user, idUser);
    }

    @Override
    public void ShowAccountInformationAboutUser(int idUser){
        csvService.addLine("Select information about user");
        userService.ShowAccountInformationAboutUser(idUser);
    }

    @Override
    public void ShowProductsInRestaurantAscByPrice(int idRestaurant){
        csvService.addLine("Select products from restaurant");
        restaurantService.ShowProductsOrderedAscByPrice(idRestaurant);
    }

    @Override
    public void ShowProductsInRestaurantDescByPrice(int idRestaurant){
        csvService.addLine("Select products from restaurant");
        restaurantService.ShowProductsOrderedDescByPrice(idRestaurant);
    }

    @Override
    public void ShowDeliveryAgentInRestaurantByIndex(int idRestaurant, int index){
        try {
            csvService.addLine("Select delivery agent from restaurant");
            System.out.println(restaurantService.GetRestaurantById(idRestaurant).GetDeliveryAgentList().get(index));
        } catch (IndexOutOfBoundsException e){
            System.out.println("Invalid index!");
        }
    }

    @Override
    public void ShowAllDeliveryAgentsInRestaurant(int idRestaurant){
        csvService.addLine("Select all delivery agents from restaurant");
        restaurantService.ShowAllDeliveryAgents(idRestaurant);
    }

    @Override
    public void AddNewDeliveryAgentInRestaurant(int idRestaurant){
        DeliveryAgent deliveryAgent = deliveryAgentService.GetNewDeliveryAgent(idRestaurant);

        if(deliveryAgent != null) {
            csvService.addLine("Create delivery agent");
            restaurantService.AddDeliveryAgent(idRestaurant, deliveryAgent);
        }
    }

    @Override
    public void AddNewOrder(int idUser, Order order){
        csvService.addLine("Update user order history");
        userService.AddNewOrder(idUser, order);
    }

    public Boolean GetLengthOfUsers(int id){
        csvService.addLine("Check if user exists");
        return userService.CheckIfUserExist(id);
    }

    public Boolean CheckIfRestaurantExist(int id){
        csvService.addLine("Check if restaurant exists");
        return restaurantService.CheckIfRestaurantExist(id);
    }
}
