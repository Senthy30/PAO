package Repositories.Orders;

import Database.DatabaseConnection;
import Repositories.BaseRepository;
import Restaurants.DeliveryAgent;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DeliveryAgentsRepository extends BaseRepository {

    // private static int idCounter = 1;
    private final String insertSql = "INSERT INTO DeliveryAgents (id, idRestaurant, firstName, lastName, phoneNumber, email, salary, noPersFeedback, feedback) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String updateSql = "UPDATE DeliveryAgents SET lastName = ?, firstName = ?, phoneNumber = ?, email = ?, salary = ?, noPersFeedback = ?, feedback = ? WHERE id = ?";
    private final String selectSql = "SELECT * FROM DeliveryAgents WHERE id = ?";
    private final String selectAllSql = "SELECT * FROM DeliveryAgents where idRestaurant = ?";
    private static String selectNextIdSql = "SELECT MAX(id) as MaxId FROM DeliveryAgents";

    public DeliveryAgentsRepository() { }

    public DeliveryAgent Select(int id){
        try {
            PreparedStatement statement = db.prepareStatement(selectSql);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new DeliveryAgent(
                        resultSet.getInt("id"),
                        resultSet.getInt("idRestaurant"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("email"),
                        resultSet.getInt("salary"),
                        resultSet.getInt("noPersFeedback"),
                        resultSet.getDouble("feedback")
                );
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public void Insert(DeliveryAgent deliveryAgent) {
        try {
            PreparedStatement statement = db.prepareStatement(insertSql);

            statement.setInt(1, deliveryAgent.GetId());
            statement.setInt(2, deliveryAgent.GetIdRestaurant());
            statement.setString(3, deliveryAgent.GetFirstName());
            statement.setString(4, deliveryAgent.GetLastName());
            statement.setString(5, deliveryAgent.GetPhoneNumber());
            statement.setString(6, deliveryAgent.GetEmail());
            statement.setInt(7, deliveryAgent.GetSalary());
            statement.setInt(8, deliveryAgent.GetNoPersFeedback());
            statement.setDouble(9, deliveryAgent.GetFeedback());

            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Update(DeliveryAgent deliveryAgent, int id) {
        try {
            PreparedStatement statement = db.prepareStatement(updateSql);

            statement.setString(1, deliveryAgent.GetFirstName());
            statement.setString(2, deliveryAgent.GetLastName());
            statement.setString(3, deliveryAgent.GetPhoneNumber());
            statement.setString(4, deliveryAgent.GetEmail());
            statement.setInt(5, deliveryAgent.GetSalary());
            statement.setInt(6, deliveryAgent.GetNoPersFeedback());
            statement.setDouble(7, deliveryAgent.GetFeedback());
            statement.setInt(8, deliveryAgent.GetId());

            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<DeliveryAgent> SelectAll(int idRestaurant){
        try {
            PreparedStatement statement = db.prepareStatement(selectAllSql);

            statement.setInt(1, idRestaurant);

            ResultSet resultSet = statement.executeQuery();

            List <DeliveryAgent> deliveryAgentsList = new ArrayList<>();
            while(resultSet.next()){
                deliveryAgentsList.add(
                        new DeliveryAgent(
                                resultSet.getInt("id"),
                                resultSet.getInt("idRestaurant"),
                                resultSet.getString("firstName"),
                                resultSet.getString("lastName"),
                                resultSet.getString("phoneNumber"),
                                resultSet.getString("email"),
                                resultSet.getInt("salary"),
                                resultSet.getInt("noPersFeedback"),
                                resultSet.getDouble("feedback")
                        )
                );
            }

            return deliveryAgentsList;

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


