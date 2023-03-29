package Services.Products;

import Products.Product;
import Products.Water;
import Services.Products.Interfaces.WaterServiceInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WaterService implements WaterServiceInterface {

    private List<Water> waterList = new ArrayList<>();
    private static WaterService instance;

    private WaterService() { }

    public static WaterService getInstance(){
        if(instance == null){
            instance = new WaterService();
        }
        return instance;
    }

    @Override
    public Water GetWaterById(int id) {
        return waterList.get(id);
    }

    @Override
    public int ReadNewWater(Product product) {
        Scanner myInput = new Scanner( System.in );

        double pH = 0;

        System.out.println("Enter the pH of the water: ");
        pH = myInput.nextDouble();

        Water water = new Water(product, pH);
        water.SetDescription();
        water.SetFoodName();

        waterList.add(water);

        return waterList.size() - 1;
    }

    @Override
    public Water GetNewWater(Product product) {
        Water water = GetWaterById(ReadNewWater(product));

        return water;
    }

}
