package Repositories.Products;

import Database.DatabaseConnection;
import Products.Product;
import Products.Water;
import Repositories.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class WaterRepository extends BaseRepository {

    private final String insertSql = "INSERT INTO Water (idWater, idProduct, pH) VALUES (?, ?, ?)";
    private final String updateSql = "UPDATE Water SET pH = ? WHERE id = ?";
    private final String selectSql = "SELECT * FROM Products INNER JOIN Water ON Products.id = Water.idProduct WHERE idWater = ?";
    private static String selectNextIdSql = "SELECT MAX(idWater) as MaxId FROM Water";

    public WaterRepository() { }

    public Water Select(int id){
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

                return new Water (
                        product,
                        resultSet.getInt("idWater"),
                        resultSet.getInt("idProduct"),
                        resultSet.getDouble("pH")
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

    public void Insert(Water water) {

        try {
            PreparedStatement statement = db.prepareStatement(insertSql);

            statement.setInt(1, water.GetIdWater());
            statement.setInt(2, water.GetIdProduct());
            statement.setDouble(3, water.GetpH());

            statement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Update(Water water, int id) {
        try {
            PreparedStatement statement = db.prepareStatement(updateSql);

            statement.setDouble(1, water.GetpH());
            statement.setInt(2, id);

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


