package Repositories.Products;

import Database.DatabaseConnection;
import Products.Product;
import Products.Shaorma;
import Repositories.BaseRepository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ShaormaRepository extends BaseRepository {

    private final String insertSql = "INSERT INTO Shaorma (idShaorma, idProduct, containsSalt, containsFries, containsOnion, containsTomato, containsLettuce, containsPickles, containsParsley, containsKetchup, containsMayonnaise) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String updateSql = "UPDATE Shaorma SET containsSalt = ?, containsFries = ?, containsOnion = ?, containsTomato = ?, containsLettuce = ?, containsPickles = ?, containsParsley = ?, containsKetchup = ?, containsMayonnaise = ? WHERE id = ?";
    private final String selectSql = "SELECT * FROM Products INNER JOIN Shaorma ON Products.id = Shaorma.idProduct WHERE idShaorma = ?";
    private static String selectNextIdSql = "SELECT MAX(idShaorma) as MaxId FROM Shaorma";

    public ShaormaRepository() { }

    public Shaorma Select(int id){
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

                return new Shaorma (
                        product,
                        resultSet.getInt("idShaorma"),
                        resultSet.getInt("idProduct"),
                        resultSet.getString("containsSalt").equalsIgnoreCase("1"),
                        resultSet.getString("containsFries").equalsIgnoreCase("1"),
                        resultSet.getString("containsOnion").equalsIgnoreCase("1"),
                        resultSet.getString("containsTomato").equalsIgnoreCase("1"),
                        resultSet.getString("containsLettuce").equalsIgnoreCase("1"),
                        resultSet.getString("containsPickles").equalsIgnoreCase("1"),
                        resultSet.getString("containsParsley").equalsIgnoreCase("1"),
                        resultSet.getString("containsKetchup").equalsIgnoreCase("1"),
                        resultSet.getString("containsMayonnaise").equalsIgnoreCase("1")
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

    public void Insert(Shaorma shaorma) {

        try {
            PreparedStatement statement = db.prepareStatement(insertSql);

            statement.setInt(1, shaorma.GetIdShaorma());
            statement.setInt(2, shaorma.GetIdProduct());
            statement.setString(3, CBTS01(shaorma.IsContainsSalt()));
            statement.setString(4, CBTS01(shaorma.IsContainsFries()));
            statement.setString(5, CBTS01(shaorma.IsContainsOnion()));
            statement.setString(6, CBTS01(shaorma.IsContainsTomato()));
            statement.setString(7, CBTS01(shaorma.IsContainsLettuce()));
            statement.setString(8, CBTS01(shaorma.IsContainsPickles()));
            statement.setString(9, CBTS01(shaorma.IsContainsParsley()));
            statement.setString(10, CBTS01(shaorma.IsContainsKetchup()));
            statement.setString(11, CBTS01(shaorma.IsContainsMayonnaise()));


            statement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Update(Shaorma shaorma, int id) {
        try {
            PreparedStatement statement = db.prepareStatement(updateSql);

            statement.setString(1, CBTS01(shaorma.IsContainsSalt()));
            statement.setString(2, CBTS01(shaorma.IsContainsFries()));
            statement.setString(3, CBTS01(shaorma.IsContainsOnion()));
            statement.setString(4, CBTS01(shaorma.IsContainsTomato()));
            statement.setString(5, CBTS01(shaorma.IsContainsLettuce()));
            statement.setString(6, CBTS01(shaorma.IsContainsPickles()));
            statement.setString(7, CBTS01(shaorma.IsContainsParsley()));
            statement.setString(8, CBTS01(shaorma.IsContainsKetchup()));
            statement.setString(9, CBTS01(shaorma.IsContainsMayonnaise()));
            statement.setInt(10, id);

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

