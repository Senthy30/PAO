package Repositories.Products;

import Database.DatabaseConnection;
import Products.Product;
import Repositories.BaseRepository;
import Users.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProductRepository extends BaseRepository {

    private final String insertSql = "INSERT INTO Products (id, idRestaurant, weight, calories, price, expirationDate, foodName, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private final String updateSql = "UPDATE Products SET weight = ?, calories = ?, price = ?, expirationDate = ?, foodName = ?, description = ? WHERE id = ?";
    private final String selectSql = "SELECT * FROM Products WHERE id = ?";
    private final String selectByFoodNameSql = "SELECT * FROM Products WHERE idRestaurant = ? AND foodName = ?";
    private static String selectNextIdSql = "SELECT MAX(id) as MaxId FROM Products";

    public ProductRepository() { }

    public Product Select(int id){
        try {
            PreparedStatement statement = db.prepareStatement(selectSql);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Product(
                        resultSet.getInt("id"),
                        resultSet.getInt("idRestaurant"),
                        resultSet.getInt("weight"),
                        resultSet.getInt("calories"),
                        resultSet.getDouble("price"),
                        resultSet.getDate("expirationDate").toLocalDate(),
                        resultSet.getString("foodName"),
                        resultSet.getString("description")
                );
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public Product SelectByFoodName(int id, String foodName){
        try {
            PreparedStatement statement = db.prepareStatement(selectByFoodNameSql);

            statement.setInt(1, id);
            statement.setString(2, foodName);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Product(
                        resultSet.getInt("id"),
                        resultSet.getInt("idRestaurant"),
                        resultSet.getInt("weight"),
                        resultSet.getInt("calories"),
                        resultSet.getDouble("price"),
                        resultSet.getDate("expirationDate").toLocalDate(),
                        resultSet.getString("foodName"),
                        resultSet.getString("description")
                );
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public void Insert(Product product) {
        try {
            PreparedStatement statement = db.prepareStatement(insertSql);

            statement.setInt(1, product.GetId());
            statement.setInt(2, product.GetIdRestaurant());
            statement.setInt(3, product.GetWeight());
            statement.setInt(4, product.GetCalories());
            statement.setDouble(5, product.GetPrice());
            statement.setDate(6,  Date.valueOf(product.GetExpirationDate()));
            statement.setString(7, product.GetFoodName());
            statement.setString(8, product.GetDescription());

            statement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Update(Product product, int id) {
        try {
            PreparedStatement statement = db.prepareStatement(updateSql);

            statement.setInt(1, product.GetWeight());
            statement.setInt(2, product.GetCalories());
            statement.setDouble(3, product.GetPrice());
            statement.setDate(4,  Date.valueOf(product.GetExpirationDate()));
            statement.setString(5, product.GetFoodName());
            statement.setString(6, product.GetDescription());
            statement.setInt(7, id);

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

