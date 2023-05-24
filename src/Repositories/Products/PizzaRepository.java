package Repositories.Products;

import Database.DatabaseConnection;
import Products.Pizza;
import Products.Product;
import Repositories.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PizzaRepository extends BaseRepository {

    private final String insertSql = "INSERT INTO Pizza (idPizza, idProduct, containsSalt, containsMozzarella, containsTomato, containsPepperoni, containsMushrooms, containsOlives, containsOnion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String updateSql = "UPDATE Pizza SET containsSalt = ?, containsMozzarella = ?, containsTomato = ?, containsPepperoni = ?, containsMushrooms = ?, containsOlives = ?, containsOnion = ? WHERE id = ?";
    private final String selectSql = "SELECT * FROM Products INNER JOIN Pizza ON Products.id = Pizza.idProduct WHERE idPizza = ?";
    private static String selectNextIdSql = "SELECT MAX(idPizza) as MaxId FROM Pizza";

    public PizzaRepository() { }

    public Pizza Select(int id){
        try {
            PreparedStatement statement = db.prepareStatement(selectSql);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getInt("idRestaurant"),
                        resultSet.getInt("weight"),
                        resultSet.getInt("calories"),
                        resultSet.getDouble("price"),
                        resultSet.getDate("expirationDate").toLocalDate(),
                        resultSet.getString("foodName"),
                        resultSet.getString("description")
                );

                return new Pizza (
                        product,
                        resultSet.getInt("idPizza"),
                        resultSet.getInt("idProduct"),
                        resultSet.getString("containsSalt").equalsIgnoreCase("1"),
                        resultSet.getString("containsMozzarella").equalsIgnoreCase("1"),
                        resultSet.getString("containsTomato").equalsIgnoreCase("1"),
                        resultSet.getString("containsPepperoni").equalsIgnoreCase("1"),
                        resultSet.getString("containsMushrooms").equalsIgnoreCase("1"),
                        resultSet.getString("containsOlives").equalsIgnoreCase("1"),
                        resultSet.getString("containsOnion").equalsIgnoreCase("1")
                );
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    private String CBTS01(Boolean val){
        if(val)
            return "1";
        return "0";
    }

    public void Insert(Pizza pizza) {

        try {
            PreparedStatement statement = db.prepareStatement(insertSql);

            statement.setInt(1, pizza.GetIdPizza());
            statement.setInt(2, pizza.GetIdProduct());
            statement.setString(3, CBTS01(pizza.IsContainsSalt()));
            statement.setString(4, CBTS01(pizza.IsContainsMozzarella()));
            statement.setString(5, CBTS01(pizza.IsContainsTomato()));
            statement.setString(6, CBTS01(pizza.IsContainsPepperoni()));
            statement.setString(7, CBTS01(pizza.IsContainsMushrooms()));
            statement.setString(8, CBTS01(pizza.IsContainsOlives()));
            statement.setString(9, CBTS01(pizza.IsContainsOnion()));

            statement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Update(Pizza pizza, int id) {
        try {
            PreparedStatement statement = db.prepareStatement(updateSql);

            statement.setString(1, CBTS01(pizza.IsContainsSalt()));
            statement.setString(2, CBTS01(pizza.IsContainsMozzarella()));
            statement.setString(3, CBTS01(pizza.IsContainsTomato()));
            statement.setString(4, CBTS01(pizza.IsContainsPepperoni()));
            statement.setString(5, CBTS01(pizza.IsContainsMushrooms()));
            statement.setString(6, CBTS01(pizza.IsContainsOlives()));
            statement.setString(7, CBTS01(pizza.IsContainsOnion()));
            statement.setInt(8, id);

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

