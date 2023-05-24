package Restaurants;

import Users.Person;

public class DeliveryAgent extends Person {

    public static int idCounter = 0;

    private int id;
    private int idRestaurant;
    private int salary;
    private int noPersFeedback;
    private double feedback;

    public DeliveryAgent(int id, int idRestaurant, String firstName, String lastName, String phoneNumber, String email, int salary, int noPersFeedback, double feedback) {
        this.id = id;
        this.idRestaurant = idRestaurant;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.salary = salary;
        this.noPersFeedback = noPersFeedback;
        this.feedback = feedback;
    }

    public DeliveryAgent(DeliveryAgent deliveryAgent) {
        this.id = deliveryAgent.id;
        this.idRestaurant = deliveryAgent.idRestaurant;
        this.firstName = deliveryAgent.firstName;
        this.lastName = deliveryAgent.lastName;
        this.phoneNumber = deliveryAgent.phoneNumber;
        this.email = deliveryAgent.email;
        this.salary = deliveryAgent.salary;
        this.feedback = deliveryAgent.feedback;
    }

    @Override
    public String toString() {
        String returnMessage = "Delivery agent " + this.firstName + " " + this.lastName + " with id " + this.id + " from restaurant with id " + this.idRestaurant + " has a salary of " + this.salary + " RON and a feedback of " + this.feedback + "\nContact information: " + this.phoneNumber + ", " + this.email + "\n";

        return returnMessage;
    }

    public int GetSalary() {
        return salary;
    }

    public void SetSalary(int salary) {
        this.salary = salary;
    }

    public double GetFeedback() {
        return feedback;
    }

    public void SetFeedback(double feedback) {
        this.feedback = feedback;
    }

    public static int GetIdCounter() {
        return idCounter;
    }

    public int GetId() {
        return id;
    }

    public int GetIdRestaurant() {
        return idRestaurant;
    }

    public int GetNoPersFeedback() {
        return noPersFeedback;
    }

    public void SetNoPersFeedback(int noPersFeedback) {
        this.noPersFeedback = noPersFeedback;
    }
}
