package Repositories.Products;

import Database.DatabaseConnection;
import Products.Juice;
import Products.Product;
import Repositories.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JuiceRepository extends BaseRepository {

    private final String insertSql = "INSERT INTO Juice (idJuice, idProduct, typeJuice, containsSugar) VALUES (?, ?, ?, ?)";
    private final String updateSql = "UPDATE Juice SET typeJuice = ?, containsSugar = ? WHERE id = ?";
    private final String selectSql = "SELECT * FROM Products INNER JOIN Juice ON Products.id = Juice.idProduct WHERE idJuice = ?";
    private static String selectNextIdSql = "SELECT MAX(idJuice) as MaxId FROM Juice";

    public JuiceRepository() { }

    public Juice Select(int id){
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

                return new Juice (
                        product,
                        resultSet.getInt("idJuice"),
                        resultSet.getInt("idProduct"),
                        resultSet.getInt("typeJuice"),
                        resultSet.getString("containsSugar").equalsIgnoreCase("1")
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

    public void Insert(Juice juice) {

        try {
            PreparedStatement statement = db.prepareStatement(insertSql);

            statement.setInt(1, juice.GetIdJuice());
            statement.setInt(2, juice.GetIdProduct());
            statement.setInt(3, juice.GetTypeJuice());
            statement.setString(4, CBTS01(juice.IsContainsSugar()));

            statement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Update(Juice juice, int id) {
        try {
            PreparedStatement statement = db.prepareStatement(updateSql);

            statement.setInt(1, juice.GetTypeJuice());
            statement.setString(2, CBTS01(juice.IsContainsSugar()));
            statement.setInt(3, id);

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

