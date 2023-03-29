package Services.Products.Interfaces;

import Products.Juice;
import Products.Product;

public interface JuiceServiceInterface {

    public Juice GetJuiceById(int id);

    public int ReadNewJuice(Product product);

    public Juice GetNewJuice(Product product);

}
