package Repositories.Products;

import Database.DatabaseConnection;
import Products.Hamburger;
import Products.Product;
import Repositories.BaseRepository;
import Users.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HamburgerRepository extends BaseRepository {

    private final String insertSql = "INSERT INTO Hamburger (idHamburger, idProduct, containsSalt, containsSesame, containsPepper, containsFries, containsOnion, containsTomato, containsLettuce, containsCheese, containsPickles) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String updateSql = "UPDATE Hamburger SET containsSalt = ?, containsSesame = ?, containsPepper = ?, containsFries = ?, containsOnion = ?, containsTomato = ?, containsLettuce = ?, containsCheese = ?, containsPickles = ? WHERE id = ?";
    private final String selectSql = "SELECT * FROM Products INNER JOIN Hamburger ON Products.id = Hamburger.idProduct WHERE idHamburger = ?";
    private static String selectNextIdSql = "SELECT MAX(idHamburger) as MaxId FROM Hamburger";

    public HamburgerRepository() { }

    public Hamburger Select(int id){
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

                return new Hamburger (
                    product,
                    resultSet.getInt("idHamburger"),
                    resultSet.getInt("idProduct"),
                    resultSet.getString("containsSalt").equalsIgnoreCase("1"),
                    resultSet.getString("containsSesame").equalsIgnoreCase("1"),
                    resultSet.getString("containsPepper").equalsIgnoreCase("1"),
                    resultSet.getString("containsFries").equalsIgnoreCase("1"),
                    resultSet.getString("containsOnion").equalsIgnoreCase("1"),
                    resultSet.getString("containsTomato").equalsIgnoreCase("1"),
                    resultSet.getString("containsLettuce").equalsIgnoreCase("1"),
                    resultSet.getString("containsCheese").equalsIgnoreCase("1"),
                    resultSet.getString("containsPickles").equalsIgnoreCase("1")
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

    public void Insert(Hamburger hamburger) {

        try {
            PreparedStatement statement = db.prepareStatement(insertSql);

            statement.setInt(1, hamburger.GetIdHamburger());  // Example: Set the id
            statement.setInt(2, hamburger.GetIdProduct());  // Example: Set the idRestaurant
            statement.setString(3, CBTS01(hamburger.IsContainsSalt()));  // Example: Set the containsSalt
            statement.setString(4, CBTS01(hamburger.IsContainsSesame()));  // Example: Set the containsSesame
            statement.setString(5, CBTS01(hamburger.IsContainsPepper()));  // Example: Set the containsPepper
            statement.setString(6, CBTS01(hamburger.IsContainsFries()));  // Example: Set the containsFries
            statement.setString(7, CBTS01(hamburger.IsContainsOnion()));  // Example: Set the containsOnion
            statement.setString(8, CBTS01(hamburger.IsContainsTomato()));  // Example: Set the containsTomato
            statement.setString(9, CBTS01(hamburger.IsContainsLettuce())); // Example: Set the containsLettuce
            statement.setString(10, CBTS01(hamburger.IsContainsCheese())); // Example: Set the containsCheese
            statement.setString(11, CBTS01(hamburger.IsContainsPickles())); // Example: Set the containsPickles

            statement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Update(Hamburger hamburger, int id) {
        try {
            PreparedStatement statement = db.prepareStatement(updateSql);

            statement.setString(1, CBTS01(hamburger.IsContainsSalt()));  // Example: Set the containsSalt
            statement.setString(2, CBTS01(hamburger.IsContainsSesame()));  // Example: Set the containsSesame
            statement.setString(3, CBTS01(hamburger.IsContainsPepper()));  // Example: Set the containsPepper
            statement.setString(4, CBTS01(hamburger.IsContainsFries()));  // Example: Set the containsFries
            statement.setString(5, CBTS01(hamburger.IsContainsOnion()));  // Example: Set the containsOnion
            statement.setString(6, CBTS01(hamburger.IsContainsTomato()));  // Example: Set the containsTomato
            statement.setString(7, CBTS01(hamburger.IsContainsLettuce())); // Example: Set the containsLettuce
            statement.setString(8, CBTS01(hamburger.IsContainsCheese())); // Example: Set the containsCheese
            statement.setString(9, CBTS01(hamburger.IsContainsPickles())); // Example: Set the containsPickles
            statement.setInt(10, id);  // Example: Set the id

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

