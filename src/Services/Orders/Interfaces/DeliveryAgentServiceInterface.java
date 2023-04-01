package Services.Orders.Interfaces;

import Restaurants.DeliveryAgent;

import java.io.IOException;

public interface DeliveryAgentServiceInterface {

    public DeliveryAgent GetDeliveryAgentById(int id);

    public void CalculateFeedBack(int id);

    public int ReadNewDeliveryAgent(int idRestaurant) throws IOException;

    public DeliveryAgent GetNewDeliveryAgent(int idRestaurant);

}
