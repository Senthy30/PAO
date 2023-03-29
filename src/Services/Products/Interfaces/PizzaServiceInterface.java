package Services.Products.Interfaces;

import Products.Pizza;
import Products.Product;

public interface PizzaServiceInterface {

    public Pizza GetPizzaById(int id);

    public int ReadNewPizza(Product product);

    public Pizza GetNewPizza(Product product);

}
