package Services.Orders;

import Repositories.Orders.DeliveryAgentsRepository;
import Restaurants.DeliveryAgent;
import Services.Orders.Interfaces.DeliveryAgentServiceInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryAgentService implements DeliveryAgentServiceInterface {

    // List<DeliveryAgent> deliveryAgentsList = new ArrayList<DeliveryAgent>();
    private DeliveryAgentsRepository deliveryAgentsRepository = new DeliveryAgentsRepository();
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
        return deliveryAgentsRepository.Select(id);
    }

    @Override
    public void CalculateFeedBack(int id) {
        DeliveryAgent deliveryAgent = GetDeliveryAgentById(id);

        int noPersFeedback = deliveryAgent.GetNoPersFeedback();
        double feedback = deliveryAgent.GetFeedback() * noPersFeedback;

        if(noPersFeedback > 0)
            feedback = feedback / noPersFeedback;

        deliveryAgent.SetFeedback(feedback);
        deliveryAgent.SetNoPersFeedback(noPersFeedback + 1);
    }

    @Override
    public int ReadNewDeliveryAgent(int idRestaurant) throws IOException {
        Scanner myInput = new Scanner( System.in );

        String firstName = "";
        String lastName = "";
        String phoneNumber = "";
        String email = "";
        int salary = 0;

        try {
            System.out.print("Enter the name of the delivery agent: ");
            firstName = myInput.nextLine();

            System.out.print("Enter the surname of the delivery agent: ");
            lastName = myInput.nextLine();

            System.out.print("Enter the phone number of the delivery agent: ");
            phoneNumber = myInput.nextLine();

            System.out.print("Enter the email of the delivery agent: ");
            email = myInput.nextLine();

            System.out.print("Enter the salary of the delivery agent: ");
            salary = myInput.nextInt();
        } catch (Exception IOException) {
            throw new IOException("Invalid input has been given!");
        }

        int id = DeliveryAgentsRepository.GetIdCounter();
        DeliveryAgent deliveryAgent = new DeliveryAgent(id, idRestaurant, firstName, lastName, phoneNumber, email, salary, 0, 0);

        deliveryAgentsRepository.Insert(deliveryAgent);

        return id;
    }

    @Override
    public DeliveryAgent GetNewDeliveryAgent(int idRestaurant) {
        try {
            DeliveryAgent deliveryAgent = GetDeliveryAgentById(ReadNewDeliveryAgent(idRestaurant));

            return deliveryAgent;
        } catch (IOException e) {
            System.out.println(e.getMessage());

            return null;
        }
    }

}
