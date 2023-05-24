package Services.Products.Interfaces;

import Products.Pizza;
import Products.Product;

import java.io.IOException;

public interface PizzaServiceInterface {

    public Pizza GetPizzaById(int id);

    public int ReadNewPizza(Product product) throws IOException;

    public Pizza GetNewPizza(Product product);

}
