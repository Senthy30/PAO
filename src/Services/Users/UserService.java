package Services.Users;

import Orders.Order;
import Products.Comparator.ProductComparatorAscByPrice;
import Products.Product;
import Repositories.Orders.OrdersRepository;
import Repositories.Products.ProductRepository;
import Repositories.Users.UserRepository;
import Services.Users.Interfaces.UserServiceInterface;
import Users.User;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class UserService implements UserServiceInterface {

    private UserRepository userReposity = new UserRepository();
    private OrdersRepository ordersRepository = new OrdersRepository();
    private ProductRepository productRepository = new ProductRepository();
    private static UserService instance;

    private UserService() { }

    public static UserService getInstance(){
        if(instance == null){
            instance = new UserService();
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
    public User GetUserById(int id) {
        User user = userReposity.Select(id);

        user.SetOrderHistory(ordersRepository.SelectAllForUser(id));
        for(Order order : user.GetOrderHistory()) {
            SetProductsForOrder(order);
            user.GetOrderDates().put(order.GetDate(), true);
        }

        return user;
    }

    @Override
    public double GetTotalPriceOfOrders(int id) {
        User user = GetUserById(id);
        double totalPrice = 0;
        for(Order order : user.GetOrderHistory())
            totalPrice += order.GetTotalPrice();
        return totalPrice;
    }

    @Override
    public User CalculateMostExpensiveProducts(int id) {
        User user = GetUserById(id);
        List<Order> orderHistory = user.GetOrderHistory();
        List<Product> mostExpensiveProducts = new ArrayList<>();

        for(Order order : orderHistory)
            for (Product product : order.GetProducts())
                mostExpensiveProducts.add(product);

        Collections.sort(mostExpensiveProducts, new ProductComparatorAscByPrice());
        Collections.reverse(mostExpensiveProducts);

        user.SetMostExpensiveProducts(mostExpensiveProducts);

        return user;
    }

    @Override
    public void SetTodayInOrderDates(int id){
        User user = GetUserById(id);
        Map<LocalDate, Boolean> orderDates = user.GetOrderDates();
        orderDates.put(LocalDate.now(), true);
    }

    @Override
    public void AddNewOrder(int id, Order order){
        User user = GetUserById(id);
        List<Order> orderHistory = user.GetOrderHistory();
        orderHistory.add(order);
        user.SetOrderHistory(orderHistory);
    }

    @Override
    public void MarkThisDateAsOrderDate(int id, LocalDate date){
        User user = GetUserById(id);
        Map<LocalDate, Boolean> orderDates = user.GetOrderDates();
        orderDates.put(date, true);
        user.SetOrderDates(orderDates);
    }

    @Override
    public int ReadNewUser() throws IOException {
        Scanner scanner = new Scanner(System.in);

        String firstName;
        String lastName;
        String phoneNumber;
        String email;
        String address;

        try {
            System.out.print("Enter first name:");
            firstName = scanner.nextLine();

            System.out.print("Enter last name:");
            lastName = scanner.nextLine();

            System.out.print("Enter phone number:");
            phoneNumber = scanner.nextLine();

            System.out.print("Enter email:");
            email = scanner.nextLine();

            System.out.print("Enter address:");
            address = scanner.nextLine();
        } catch (Exception IOException) {
            throw new IOException("Invalid input has been given!");
        }

        int idUser = userReposity.GetIdCounter();
        User user = new User(idUser, firstName, lastName, phoneNumber, email, address);

        userReposity.Insert(user);

        return idUser;
    }

    @Override
    public User GetNewUser(){
        try {
            User user = GetUserById(ReadNewUser());

            return user;
        } catch (IOException e) {
            System.out.println(e.getMessage());

            return null;
        }
    }

    public void Update(User user, int id){
        userReposity.Update(user, id);
    }

    @Override
    public void ShowMostExpensiveProductsFromOrders(int id){
        User user = CalculateMostExpensiveProducts(id);
        List<Product> mostExpensiveProducts = user.GetMostExpensiveProducts();
        System.out.println("Most expensive products from orders of " + user.GetFirstName() + " " + user.GetLastName() + ":");
        int currentIndex = 1;
        for(Product product : mostExpensiveProducts)
            System.out.println(currentIndex++ + ". " + product);
        System.out.println();
    }

    @Override
    public void CheckIfUserOrderedSomethingOnDate(int idUser, LocalDate date){
        User user = GetUserById(idUser);
        Map<LocalDate, Boolean> orderDates = user.GetOrderDates();
        if(orderDates.containsKey(date))
            System.out.println("User " + user.GetFirstName() + " " + user.GetLastName() + " ordered something on " + date + ".\n");
        else
            System.out.println("User " + user.GetFirstName() + " " + user.GetLastName() + " didn't order anything on " + date + ".\n");
    }

    @Override
    public void ShowHistoryOfOrdersForUser(int idUser){
        User user = GetUserById(idUser);
        List<Order> orderHistory = user.GetOrderHistory();
        System.out.println("Order history of " + user.GetFirstName() + " " + user.GetLastName() + ":");
        int currentIndex = 1;
        for(Order order : orderHistory) {
            System.out.println("Order " + currentIndex + ".\n" + order);
            currentIndex++;
        }
        System.out.println();
    }

    @Override
    public void ShowAccountInformationAboutUser(int idUser){
        User user = GetUserById(idUser);
        System.out.println("Account information of " + user.GetFirstName() + " " + user.GetLastName() + ":");
        System.out.println("Phone number: " + user.GetPhoneNumber());
        System.out.println("Email: " + user.GetEmail());
        System.out.println("Address: " + user.GetAddress());
        System.out.println();
    }

    public int GetLengthHistoryOfOrdersForUser(int idUser){
        User user = GetUserById(idUser);
        List<Order> orderHistory = user.GetOrderHistory();
        return orderHistory.size();
    }

    public Boolean CheckIfUserExist(int id){
        return userReposity.CheckIfUserExist(id);
    }
}
