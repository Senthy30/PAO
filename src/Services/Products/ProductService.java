package Services.Products;

import Products.Product;
import Repositories.Products.ProductRepository;
import Services.Products.Interfaces.ProductServiceInterface;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductService implements ProductServiceInterface {

    // private List<Product> productsList = new ArrayList<Product>();
    private ProductRepository productRepository = new ProductRepository();
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
        return productRepository.Select(id);
    }

    @Override
    public int ReadNewProduct(int idRestaurant, String foodName) throws IOException {
        Scanner myInput = new Scanner( System.in );

        int idProduct = productRepository.GetIdCounter();
        Product product = new Product(idProduct, idRestaurant);

        System.out.print("Enter the weight of the product: ");
        try{
            product.SetWeight(myInput.nextInt());
        } catch (Exception IOException){
            throw new IOException();
        }

        System.out.print("Enter the calories of the product: ");
        try {
            product.SetCalories(myInput.nextInt());
        } catch (Exception IOException){
            throw new IOException();
        }

        System.out.print("Enter the price of the product: ");
        try {
            product.SetPrice(myInput.nextDouble());
        } catch (Exception IOException){
            throw new IOException();
        }

        //LocalDate expirationDate = LocalDate.now().plusDays(1);
        LocalDate expirationDate = LocalDate.now();
        product.SetExpirationDate(expirationDate);

        product.SetFoodName(foodName);

        productRepository.Insert(product);

        return idProduct;
    }

    @Override
    public Product GetNewProduct(int idRestaurant, String foodName){
        try {
            Product product = GetProductById(ReadNewProduct(idRestaurant, foodName));

            return product;
        } catch (IOException e) {
            System.out.println("Invalid input has been given!");

            return null;
        }
    }

}
