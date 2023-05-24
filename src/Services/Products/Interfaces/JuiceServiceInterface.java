package Services.Products.Interfaces;

import Products.Juice;
import Products.Product;

import java.io.IOException;

public interface JuiceServiceInterface {

    public Juice GetJuiceById(int id);

    public int ReadNewJuice(Product product) throws IOException, IllegalArgumentException;

    public Juice GetNewJuice(Product product);

}
