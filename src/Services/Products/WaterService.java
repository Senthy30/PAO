package Services.Products;

import Products.Product;
import Products.Water;
import Repositories.Products.WaterRepository;
import Services.Products.Interfaces.WaterServiceInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WaterService implements WaterServiceInterface {

    //private List<Water> waterList = new ArrayList<>();
    private WaterRepository waterRepository = new WaterRepository();
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
        return waterRepository.Select(id);
    }

    @Override
    public int ReadNewWater(Product product) throws IOException {
        Scanner myInput = new Scanner( System.in );

        double pH = 0;

        System.out.print("Enter the pH of the water: ");
        try {
            pH = myInput.nextDouble();
        } catch (Exception IOException){
            throw new IOException("Invalid input has been given!");
        }

        int id = waterRepository.GetIdCounter();
        Water water = new Water(product, id, product.GetId(), pH);
        water.SetDescription();
        water.SetFoodName();

        waterRepository.Insert(water);

        return id;
    }

    @Override
    public Water GetNewWater(Product product) {
        try {
            Water water = GetWaterById(ReadNewWater(product));

            return water;
        } catch (IOException e) {
            System.out.println(e.getMessage());

            return null;
        }
    }

}
