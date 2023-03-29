package Services.Orders;

import Restaurants.DeliveryAgent;
import Services.Orders.Interfaces.DeliveryAgentServiceInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryAgentService implements DeliveryAgentServiceInterface {

    List<DeliveryAgent> deliveryAgentsList = new ArrayList<DeliveryAgent>();
    private static DeliveryAgentService instance;

    private DeliveryAgentService() { }

    public static DeliveryAgentService getInstance(){
        if(instance == null){
            instance = new DeliveryAgentService();
        }
        return instance;
    }

    @Override
    public DeliveryAgent GetDeliveryAgentById(int id) {
        return deliveryAgentsList.get(id);
    }

    @Override
    public void CalculateFeedBack(int id) {
        DeliveryAgent deliveryAgent = deliveryAgentsList.get(id);

        int noPersFeedback = deliveryAgent.GetNoPersFeedback();
        double feedback = deliveryAgent.GetFeedback() * noPersFeedback;

        if(noPersFeedback > 0)
            feedback = feedback / noPersFeedback;

        deliveryAgent.SetFeedback(feedback);
        deliveryAgent.SetNoPersFeedback(noPersFeedback + 1);
    }

    @Override
    public int ReadNewDeliveryAgent(int idRestaurant) {
        Scanner myInput = new Scanner( System.in );

        String firstName = "";
        String lastName = "";
        String phoneNumber = "";
        String email = "";
        int salary = 0;

        System.out.println("Enter the name of the delivery agent: ");
        firstName = myInput.nextLine();

        System.out.println("Enter the surname of the delivery agent: ");
        lastName = myInput.nextLine();

        System.out.println("Enter the phone number of the delivery agent: ");
        phoneNumber = myInput.nextLine();

        System.out.println("Enter the email of the delivery agent: ");
        email = myInput.nextLine();

        System.out.println("Enter the salary of the delivery agent: ");
        salary = myInput.nextInt();

        DeliveryAgent deliveryAgent = new DeliveryAgent(idRestaurant, firstName, lastName, phoneNumber, email, salary, 0);

        deliveryAgentsList.add(deliveryAgent);

        return deliveryAgentsList.size() - 1;
    }

    @Override
    public DeliveryAgent GetNewDeliveryAgent(int idRestaurant) {
        DeliveryAgent deliveryAgent = GetDeliveryAgentById(ReadNewDeliveryAgent(idRestaurant));

        return deliveryAgent;
    }

}
