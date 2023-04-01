package Services.Users.Interfaces;

import Orders.Order;
import Users.User;

import java.io.IOException;
import java.time.LocalDate;

public interface UserServiceInterface {

    public User GetUserById(int id);

    public double GetTotalPriceOfOrders(int id);

    public void CalculateMostExpensiveProducts(int id);

    public void SetTodayInOrderDates(int id);

    public void AddNewOrder(int id, Order order);

    public int ReadNewUser() throws IOException;

    public User GetNewUser();

    public void ShowMostExpensiveProductsFromOrders(int id);

    public void CheckIfUserOrderedSomethingOnDate(int idUser, LocalDate date);

    public void ShowHistoryOfOrdersForUser(int idUser);

    public void ShowAccountInformationAboutUser(int idUser);

    public void MarkThisDateAsOrderDate(int id, LocalDate date);

}
