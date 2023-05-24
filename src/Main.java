import Orders.Order;
import Products.Hamburger;
import Products.Product;
import Repositories.Users.UserRepository;
import Restaurants.Restaurant;
import Services.Products.HamburgerService;
import Services.Products.ProductService;
import Services.Service;
import Database.DatabaseConnection;
import Users.User;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    private static Service service = Service.getInstance();

    public static void main(String[] args) {

        UserRepository ur = new UserRepository();

        //User tempUser = new User(1, "Denis", "Cringanu", "0787520574", "denisflorin69@yahoo.com", "Strada Principala");
        //ur.Insert(tempUser);

        int option = -1;
        Scanner scanner = new Scanner(System.in);

        while(option != 0){
            System.out.println("Choose an option: ");
            System.out.println("0. Exit");
            System.out.println("1. Create new restaurant");
            System.out.println("2. Create new user");
            System.out.println("3. Browse through users");
            System.out.println("4. Browse through restaurants");
            System.out.print("Choose an option: ");

            option = scanner.nextInt();

            if(option == 1){
                service.CreateNewRestaurant();
            } else if (option == 2){
                service.CreateNewUser();
            } else if (option == 3){
                UserMenu();
            } else if (option == 4){
                RestaurantMenu();
            } else if (option == 0){
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid option!");
            }
        }
    }

    private static void RestaurantMenu(){
        int idRestaurant = -1;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the id of the restaurant: ");

        idRestaurant = scanner.nextInt();
        if(!service.CheckIfRestaurantExist(idRestaurant)) {
            System.out.println("Invalid index for restaurant!");

            return;
        }

        int option = -1;
        while(option != 0){

            System.out.println("0. Exit");
            System.out.println("1. Show products");
            System.out.println("2. Show products ordered ascendent by price");
            System.out.println("3. Show products ordered descendent by price");
            System.out.println("4. Show all information about a delivery agents by index");
            System.out.println("5. Show all information about all delivery agents");
            System.out.println("6. Add new delivery agent");

            System.out.print("Choose an option: ");
            option = scanner.nextInt();

            if(option == 1){
                ShowProductInRestaurant(idRestaurant);
            } else if (option == 2){
                ShowProductsInRestaurantAscByPrice(idRestaurant);
            } else if (option == 3){
                ShowProductsInRestaurantDescByPrice(idRestaurant);
            } else if (option == 4){
                ShowDeliveryAgentInRestaurantByIndex(idRestaurant);
            } else if (option == 5){
                ShowAllDeliveryAgentsInRestaurant(idRestaurant);
            } else if (option == 6){
                AddNewDeliveryAgentInRestaurant(idRestaurant);
            } else if (option == 0){
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid option!");
            }
        }
    }

    private static void ShowProductInRestaurant(int idRestaurant){
        service.ShowProductInRestaurant(idRestaurant);
    }

    private static void ShowProductsInRestaurantAscByPrice(int idRestaurant){
        service.ShowProductsInRestaurantAscByPrice(idRestaurant);
    }

    private static void ShowProductsInRestaurantDescByPrice(int idRestaurant){
        service.ShowProductsInRestaurantDescByPrice(idRestaurant);
    }

    private static void ShowDeliveryAgentInRestaurantByIndex(int idRestaurant){
        Scanner scanner = new Scanner(System.in);

        int index;
        index = scanner.nextInt();

        service.ShowDeliveryAgentInRestaurantByIndex(idRestaurant, index);
    }

    private static void ShowAllDeliveryAgentsInRestaurant(int idRestaurant){
        service.ShowAllDeliveryAgentsInRestaurant(idRestaurant);
    }

    private static void AddNewDeliveryAgentInRestaurant(int idRestaurant){
        service.AddNewDeliveryAgentInRestaurant(idRestaurant);
    }

    private static void UserMenu(){
        int idUser = -1;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the id of the user: ");

        idUser = scanner.nextInt();
        if(service.GetLengthOfUsers(idUser) == false){
            System.out.println("Invalid index for user!");

            return;
        }

        int option = -1;
        while(option != 0){

            System.out.println("0. Exit");
            System.out.println("1. Create new order");
            System.out.println("2. Show history of orders");
            System.out.println("3. Show products from orders by index");
            System.out.println("4. Show products from orders ascendent by price");
            System.out.println("5. Show products from orders descendent by price");
            System.out.println("6. Show most expensive products from orders");
            System.out.println("7. Check if you ordered something on a date");
            System.out.println("8. Change adress");
            System.out.println("9. Change phone number");
            System.out.println("10. Change email");
            System.out.println("11. Show account information about this user");

            System.out.print("Choose an option: ");
            option = scanner.nextInt();

            if(option == 1){
                PlaceNewCommand(idUser);
            } else if (option == 2){
                ShowHistoryOfOrders(idUser);
            } else if (option == 3) {
                ShowProductsFromOrdersByIndex(idUser);
            } else if (option == 4){
                ShowProductsFromOrdersAscByPrice(idUser);
            } else if (option == 5){
                ShowProductsFromOrdersDescByPrice(idUser);
            } else if (option == 6){
                ShowMostExpensiveProductsFromOrders(idUser);
            } else if (option == 7){
                CheckIfUserOrderedSomethingOnDate(idUser);
            } else if (option == 8){
                ChangeAdress(idUser);
            } else if (option == 9){
                ChangePhoneNumber(idUser);
            } else if (option == 10){
                ChangeEmail(idUser);
            } else if (option == 11){
                ShowAccountInformationAboutUser(idUser);
            } else if (option == 0){
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid option!");
            }
        }
    }

    private static void PlaceNewCommand(int idUser){
        int option = -1;

        service.ShowAllRestaurants();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose a restaurant: ");
        option = scanner.nextInt();

        Restaurant restaurant = service.GetRestaurantById(option);
        if(restaurant == null)
            return;

        Order order = service.CreateNewOrder(restaurant.GetId(), idUser);
        if(order != null)
            service.AddNewOrder(idUser, order);
    }

    private static void ShowHistoryOfOrders(int idUser){
        service.ShowHistoryOfOrdersForUser(idUser);
    }

    private static void ShowProductsFromOrdersByIndex(int idUser){
        int option = -1;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose an order: ");
        option = scanner.nextInt();

        service.ShowProductsFromOrderByIndex(idUser, option);
    }

    private static void ShowProductsFromOrdersAscByPrice(int idUser){
        int option = -1;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose an order: ");
        option = scanner.nextInt();

        service.ShowProductsFromOrdersAscByPrice(idUser, option);
    }

    private static void ShowProductsFromOrdersDescByPrice(int idUser){
        int option = -1;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose an order: ");
        option = scanner.nextInt();

        service.ShowProductsFromOrdersDescByPrice(idUser, option);
    }

    private static void ShowMostExpensiveProductsFromOrders(int idUser){
        service.ShowMostExpensiveProductsFromOrders(idUser);
    }

    private static void CheckIfUserOrderedSomethingOnDate(int idUser){
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter the date: ");
            System.out.print("Year: ");
            int year = scanner.nextInt();
            System.out.print("Month: ");
            int month = scanner.nextInt();
            System.out.print("Day: ");
            int day = scanner.nextInt();
            System.out.println();

            LocalDate date = LocalDate.of(year, month, day);
            service.CheckIfUserOrderedSomethingOnDate(idUser, date);
        } catch (Exception e) {
            System.out.println("Invalid date!");

            return;
        }
    }

    private static void ChangeAdress(int idUser){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the new adress: ");
        String newAdress = scanner.nextLine();

        service.ChangeAdress(idUser, newAdress);
    }

    private static void ChangePhoneNumber(int idUser){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the new phone number: ");
        String newPhoneNumber = scanner.next();

        service.ChangePhoneNumber(idUser, newPhoneNumber);
    }

    private static void ChangeEmail(int idUser){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the new email: ");
        String newEmail = scanner.next();

        service.ChangeEmail(idUser, newEmail);
    }

    private static void ShowAccountInformationAboutUser(int idRestaurant){
        service.ShowAccountInformationAboutUser(idRestaurant);
    }
}