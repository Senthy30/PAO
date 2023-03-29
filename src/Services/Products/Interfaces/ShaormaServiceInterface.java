package Services.Products.Interfaces;

import Products.Product;
import Products.Shaorma;

public interface ShaormaServiceInterface {

    public Shaorma GetShaormaById(int id);

    public int ReadNewShaorma(Product product);

    public Shaorma GetNewShaorma(Product product);


}
