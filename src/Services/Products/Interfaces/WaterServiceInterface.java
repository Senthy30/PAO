package Services.Products.Interfaces;

import Products.Hamburger;
import Products.Product;
import Products.Water;

public interface WaterServiceInterface {

    public Water GetWaterById(int id);

    public int ReadNewWater(Product product);

    public Water GetNewWater(Product product);

}
