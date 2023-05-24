package Repositories.Restaurants;

import Database.DatabaseConnection;
import Products.Product;
import Repositories.BaseRepository;
import Restaurants.Restaurant;
import Users.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RestaurantRepository extends BaseRepository {

    // private static int idCounter = 1;
    private final String insertSql = "INSERT INTO Restaurants (id, idHamburger, idShaorma, idPizza, idJuice, idWater, address) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String updateSql = "UPDATE Restaurants SET idHamburger = ?, idShaorma = ?, idPizza = ?, idJuice = ?, idWater = ?, address = ? WHERE id = ?";
    private final String selectSql = "SELECT * FROM Restaurants WHERE id = ?";
    private final String selectAllSql = "SELECT id FROM Restaurants";
    private static String selectNextIdSql = "SELECT MAX(id) as MaxId FROM Restaurants";

    public RestaurantRepository() { }

    public Restaurant Select(int id){
        try {
            PreparedStatement statement = db.prepareStatement(selectSql);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Restaurant(
                        resultSet.getInt("id"),
                        resultSet.getInt("idHamburger"),
                        resultSet.getInt("idShaorma"),
                        resultSet.getInt("idPizza"),
                        resultSet.getInt("idJuice"),
                        resultSet.getInt("idWater"),
                        resultSet.getString("address")
                );
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public List<Integer> SelectAll(){
        try {
            PreparedStatement statement = db.prepareStatement(selectAllSql);

            ResultSet resultSet = statement.executeQuery();
            List<Integer> restaurantsIds = new ArrayList<>();

            while (resultSet.next()) {
                restaurantsIds.add(
                    resultSet.getInt("id")
                );
            }

            return restaurantsIds;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Insert(Restaurant restaurant) {
        try {
            PreparedStatement statement = db.prepareStatement(insertSql);

            statement.setInt(1, restaurant.GetId());
            statement.setInt(2, restaurant.GetIdHamburger());
            statement.setInt(3, restaurant.GetIdShaorma());
            statement.setInt(4, restaurant.GetIdPizza());
            statement.setInt(5, restaurant.GetIdJuice());
            statement.setInt(6, restaurant.GetIdWater());
            statement.setString(7, restaurant.GetAddress());

            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Update(Restaurant restaurant, int id) {
        try {
            PreparedStatement statement = db.prepareStatement(updateSql);

            statement.setInt(1, restaurant.GetIdHamburger());
            statement.setInt(2, restaurant.GetIdShaorma());
            statement.setInt(3, restaurant.GetIdPizza());
            statement.setInt(4, restaurant.GetIdJuice());
            statement.setInt(5, restaurant.GetIdWater());
            statement.setString(6, restaurant.GetAddress());
            statement.setInt(7, restaurant.GetId());

            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean CheckIfRestaurantExist(int id){
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


