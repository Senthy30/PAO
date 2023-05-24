package Services.Products.Interfaces;

import Products.Product;

import java.io.IOException;

public interface ProductServiceInterface {

    public Product GetProductById(int id);

    public int ReadNewProduct(int idRestaurant, String foodName) throws IOException;

    public Product GetNewProduct(int idRestaurant, String foodName);

}
