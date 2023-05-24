package Repositories.Orders;

import Database.DatabaseConnection;
import Orders.Order;
import Products.Product;
import Repositories.BaseRepository;
import Users.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrdersRepository extends BaseRepository {

    private final String insertSql = "INSERT INTO Orders (id, idRestaurant, idDeliveryAgent, idUser, isDelivered, totalPrice, orderDate, products) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private final String updateSql = "UPDATE Orders SET idRestaurant = ?, idDeliveryAgent = ?, isDelivered = ?, totalPrice = ?, orderDate = ?, products = ? WHERE id = ?";
    private final String selectSql = "SELECT * FROM Orders WHERE id = ?";
    private final String selectAllForUserSql = "SELECT * FROM Orders WHERE idUser = ?";
    private static String selectNextIdSql = "SELECT MAX(id) as MaxId FROM Orders";

    public OrdersRepository() { }

    public Order Select(int id){
        try {
            PreparedStatement statement = db.prepareStatement(selectSql);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Order(
                    resultSet.getInt("id"),
                    resultSet.getInt("idRestaurant"),
                    resultSet.getInt("idDeliveryAgent"),
                    resultSet.getInt("idUser"),
                    resultSet.getString("isDelivered").equalsIgnoreCase("1"),
                    resultSet.getDouble("totalPrice"),
                    resultSet.getDate("orderDate").toLocalDate(),
                    resultSet.getString("products")
                );
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public List<Order> SelectAllForUser(int idUser){
        try {
            PreparedStatement statement = db.prepareStatement(selectAllForUserSql);

            statement.setInt(1, idUser);

            ResultSet resultSet = statement.executeQuery();

            List<Order> orders = new ArrayList<>();

            while (resultSet.next()) {
                orders.add(new Order(
                    resultSet.getInt("id"),
                    resultSet.getInt("idRestaurant"),
                    resultSet.getInt("idDeliveryAgent"),
                    resultSet.getInt("idUser"),
                    resultSet.getString("isDelivered").equalsIgnoreCase("1"),
                    resultSet.getDouble("totalPrice"),
                    resultSet.getDate("orderDate").toLocalDate(),
                    resultSet.getString("products")
                ));
            }

            return orders;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String CBTS01(Boolean val){
        if(val)
            return "1";
        return "0";
    }

    public void Insert(Order order) {
        try {
            PreparedStatement statement = db.prepareStatement(insertSql);

            statement.setInt(1, order.GetId());
            statement.setInt(2, order.GetIdRestaurant());
            statement.setInt(3, order.GetIdDeliveryAgent());
            statement.setInt(4, order.GetIdUser());
            statement.setString(5, CBTS01(order.IsDelivered()));
            statement.setDouble(6, order.GetTotalPrice());
            statement.setDate(7, Date.valueOf(order.GetDate()));
            statement.setString(8, order.GetProductsAsString());

            statement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Update(Order order, int id) {
        try {
            PreparedStatement statement = db.prepareStatement(updateSql);

            statement.setInt(1, order.GetIdRestaurant());
            statement.setInt(2, order.GetIdDeliveryAgent());
            statement.setString(3, CBTS01(order.IsDelivered()));
            statement.setDouble(4, order.GetTotalPrice());
            statement.setDate(5, Date.valueOf(order.GetDate()));
            statement.setString(6, order.GetProductsAsString());
            statement.setInt(7, order.GetId());

            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean CheckIfProductExist(int id){
        try {
            PreparedStatement statement = db.prepareStatement(selectSql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return true;
            }

            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static int GetIdCounter(){
        try {
            Connection db = DatabaseConnection.getSession();
            PreparedStatement statement = db.prepareStatement(selectNextIdSql);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                int idCounter = resultSet.getInt("MaxId") + 1;

                return idCounter;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return 0;

    }

}

