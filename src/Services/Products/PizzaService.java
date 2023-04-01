package Services.Products;

import Products.Pizza;
import Products.Product;
import Services.Products.Interfaces.PizzaServiceInterface;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class PizzaService implements PizzaServiceInterface {

    private List<Pizza> pizzaList = new ArrayList<Pizza>();
    private static PizzaService instance;

    private PizzaService() { }

    public static PizzaService getInstance(){
        if(instance == null){
            instance = new PizzaService();
        }
        return instance;
    }

    @Override
    public Pizza GetPizzaById(int id) {
        return pizzaList.get(id);
    }

    @Override
    public int ReadNewPizza(Product product) throws IOException {
        Scanner myInput = new Scanner( System.in );

        boolean containsSalt = false;
        boolean containsMozzarella = false;
        boolean containsTomato = false;
        boolean containsPepperoni = false;
        boolean containsMushrooms = false;
        boolean containsOlives = false;
        boolean containsOnion = false;

        System.out.print("Do you want it to contain salt? (1/0): ");
        try {
            if(myInput.nextInt() == 1)
                containsSalt = true;
        } catch (Exception IOException){
            throw new IOException("Invalid input has been given!");
        }

        System.out.print("Do you want it to contain mozzarella? (1/0): ");
        try {
            if(myInput.nextInt() == 1)
                containsMozzarella = true;
        } catch (Exception IOException){
            throw new IOException("Invalid input has been given!");
        }

        System.out.print("Do you want it to contain tomato? (1/0): ");
        try {
            if(myInput.nextInt() == 1)
                containsTomato = true;
        } catch (Exception IOException){
            throw new IOException("Invalid input has been given!");
        }

        System.out.print("Do you want it to contain pepperoni? (1/0): ");
        try {
            if(myInput.nextInt() == 1)
                containsPepperoni = true;
        } catch (Exception IOException){
            throw new IOException("Invalid input has been given!");
        }

        System.out.print("Do you want it to contain mushrooms? (1/0): ");
        try {
            if(myInput.nextInt() == 1)
                containsMushrooms = true;
        } catch (Exception IOException){
            throw new IOException("Invalid input has been given!");
        }

        System.out.print("Do you want it to contain olives? (1/0): ");
        try {
            if(myInput.nextInt() == 1)
                containsOlives = true;
        } catch (Exception IOException){
            throw new IOException("Invalid input has been given!");
        }

        System.out.print("Do you want it to contain onion? (1/0): ");
        try {
            if(myInput.nextInt() == 1)
                containsOnion = true;
        } catch (Exception IOException){
            throw new IOException("Invalid input has been given!");
        }

        Pizza pizza = new Pizza(product, containsSalt, containsMozzarella, containsTomato, containsPepperoni, containsMushrooms, containsOlives, containsOnion);
        pizza.SetDescription();
        pizza.SetFoodName();

        pizzaList.add(pizza);

        return pizzaList.size() - 1;
    }

    @Override
    public Pizza GetNewPizza(Product product){
        try {
            Pizza pizza = GetPizzaById(ReadNewPizza(product));

            return pizza;
        } catch (IOException e){
            System.out.println(e.getMessage());

            return null;
        }
    }

}
