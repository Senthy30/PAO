package Services.Products.Interfaces;

import Products.Product;

import java.io.IOException;

public interface ProductServiceInterface {

    public Product GetProductById(int id);

    public int ReadNewProduct(int idRestaurant) throws IOException;

    public Product GetNewProduct(int idRestaurant);

}
