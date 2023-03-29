package Services;

import Orders.Order;
import Restaurants.Restaurant;
import Users.User;

import java.time.LocalDate;

public interface ServiceInterface {

    public void CreateNewRestaurant();

    public void CreateNewUser();

    public Order CreateNewOrder(int idRestaurant);

    public void ShowProductsInRestaurantOrderedDescByPrice(int idRestaurant);

    public void ShowProductsInRestaurantOrderedAscByPrice(int idRestaurant);

    public void ShowProductInRestaurant(int idRestaurant);

    public void ShowProductsInOrder(int idOrder);

    public void ShowProductsInOrderOrderedDescByPrice(int idOrder);

    public void ShowProductsInOrderOrderedAscByPrice(int idOrder);

    public void ShowHistoryOfOrdersForUser(int idUser);

    public void ShowProductsFromOrderByIndex(int idUser, int index);

    public void ShowProductsFromOrdersAscByPrice(int idUser, int index);

    public void ShowProductsFromOrdersDescByPrice(int idUser, int index);

    public void ShowMostExpensiveProductsFromOrders(int idUser);

    public void CheckIfUserOrderedSomethingOnDate(int idUser, LocalDate date);

    public void ChangeAdress(int idUser, String address);

    public void ChangePhoneNumber(int idUser, String phoneNumer);

    public void ChangeEmail(int idUser, String email);

    public void ShowAccountInformationAboutUser(int idUser);

    public void ShowProductsInRestaurantAscByPrice(int idRestaurant);

    public void ShowProductsInRestaurantDescByPrice(int idRestaurant);

    public void ShowDeliveryAgentInRestaurantByIndex(int idRestaurant, int index);

    public void ShowAllDeliveryAgentsInRestaurant(int idRestaurant);

    public void AddNewDeliveryAgentInRestaurant(int idRestaurant);

    public void AddNewOrder(int idUser, Order order);

    public Restaurant GetRestaurantById(int id);

    public void ShowAllRestaurants();

    public Order GetOrderById(int id);

    public User GetUserById(int id);

}
