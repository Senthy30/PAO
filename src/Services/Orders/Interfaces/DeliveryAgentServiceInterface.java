package Services.Orders.Interfaces;

import Restaurants.DeliveryAgent;

public interface DeliveryAgentServiceInterface {

    public DeliveryAgent GetDeliveryAgentById(int id);

    public void CalculateFeedBack(int id);

    public int ReadNewDeliveryAgent(int idRestaurant);

    public DeliveryAgent GetNewDeliveryAgent(int idRestaurant);

}
