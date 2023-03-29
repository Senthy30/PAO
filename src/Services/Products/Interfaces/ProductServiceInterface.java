package Services.Products.Interfaces;

import Products.Product;

public interface ProductServiceInterface {

    public Product GetProductById(int id);

    public int ReadNewProduct(int idRestaurant);

    public Product GetNewProduct(int idRestaurant);

}
