package Repositories.Users;

import Database.DatabaseConnection;
import Repositories.BaseRepository;
import Users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRepository extends BaseRepository {

    //private static int idCounter = 1;
    private final String insertSql = "INSERT INTO Users (id, lastName, firstName, phoneNumber, email, address) VALUES (?, ?, ?, ?, ?, ?)";
    private final String updateSql = "UPDATE Users SET lastName = ?, firstName = ?, phoneNumber = ?, email = ?, address = ? WHERE id = ?";
    private final String selectSql = "SELECT * FROM Users WHERE id = ?";
    private static String selectNextIdSql = "SELECT MAX(id) as MaxId FROM Users";

    public UserRepository() { }

    public User Select(int id){
        try {
            PreparedStatement statement = db.prepareStatement(selectSql);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("email"),
                        resultSet.getString("address")
                );
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public void Insert(User employee) {
        try {
            PreparedStatement statement = db.prepareStatement(insertSql);

            statement.setInt(1, employee.GetId());
            statement.setString(2, employee.GetFirstName());
            statement.setString(3, employee.GetLastName());
            statement.setString(4, employee.GetPhoneNumber());
            statement.setString(5, employee.GetEmail());
            statement.setString(6, employee.GetAddress());

            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Update(User user, int id) {
        try {
            PreparedStatement statement = db.prepareStatement(updateSql);

            statement.setString(1, user.GetFirstName());
            statement.setString(2, user.GetLastName());
            statement.setString(3, user.GetPhoneNumber());
            statement.setString(4, user.GetEmail());
            statement.setString(5, user.GetAddress());
            statement.setInt(6, id);

            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean CheckIfUserExist(int id){
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

            if(resultSet.next()){
                int idCounter = resultSet.getInt("MaxId") + 1;

                System.out.println(idCounter);

                return idCounter;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

}
