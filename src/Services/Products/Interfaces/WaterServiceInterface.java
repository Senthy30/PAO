package Services.Products.Interfaces;

import Products.Hamburger;
import Products.Product;
import Products.Water;

import java.io.IOException;

public interface WaterServiceInterface {

    public Water GetWaterById(int id);

    public int ReadNewWater(Product product) throws IOException;

    public Water GetNewWater(Product product);

}
