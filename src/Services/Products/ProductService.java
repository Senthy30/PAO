package Services.Products;

import Products.Product;
import Services.Products.Interfaces.ProductServiceInterface;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductService implements ProductServiceInterface {

    private List<Product> productsList = new ArrayList<Product>();
    private static ProductService instance;

    private ProductService() { }

    public static ProductService getInstance(){
        if(instance == null){
            instance = new ProductService();
        }
        return instance;
    }

    @Override
    public Product GetProductById(int id) {
        return productsList.get(id);
    }

    @Override
    public int ReadNewProduct(int idRestaurant) {
        Scanner myInput = new Scanner( System.in );
        Product product = new Product(idRestaurant);

        System.out.print("Enter the weight of the product: ");
        product.SetWeight(myInput.nextInt());

        System.out.print("Enter the calories of the product: ");
        product.SetCalories(myInput.nextInt());

        System.out.print("Enter the price of the product: ");
        product.SetPrice(myInput.nextDouble());

        LocalDate expirationDate = LocalDate.now();
        product.SetExpirationDate(expirationDate);

        productsList.add(product);

        return productsList.size() - 1;
    }

    @Override
    public Product GetNewProduct(int idRestaurant){
        Product product = GetProductById(ReadNewProduct(idRestaurant));

        return product;
    }

}
