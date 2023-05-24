package Services.Products.Interfaces;

import Products.Product;
import Products.Shaorma;

import java.io.IOException;

public interface ShaormaServiceInterface {

    public Shaorma GetShaormaById(int id);

    public int ReadNewShaorma(Product product) throws IOException;

    public Shaorma GetNewShaorma(Product product);


}
