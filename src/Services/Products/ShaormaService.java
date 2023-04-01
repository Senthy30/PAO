package Services.Products;

import Products.Product;
import Products.Shaorma;
import Services.Products.Interfaces.ShaormaServiceInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShaormaService implements ShaormaServiceInterface {

    private List<Shaorma> shaormaList = new ArrayList<>();
    private static ShaormaService instance;

    private ShaormaService() { }

    public static ShaormaService getInstance(){
        if(instance == null){
            instance = new ShaormaService();
        }
        return instance;
    }

    @Override
    public Shaorma GetShaormaById(int id) {
        return shaormaList.get(id);
    }

    @Override
    public int ReadNewShaorma(Product product) throws IOException {
        Scanner myInput = new Scanner( System.in );

        boolean containsSalt = false;
        boolean containsFries = false;
        boolean containsOnion = false;
        boolean containsTomato = false;
        boolean containsLettuce = false;
        boolean containsPickles = false;
        boolean containsParsley = false;
        boolean containsKetchup = false;
        boolean containsMayonnaise = false;

        System.out.print("Do you want it to contain salt? (1/0): ");
        try {
            if(myInput.nextInt() == 1)
                containsSalt = true;
        } catch (Exception IOException){
            throw new IOException("Invalid input has been given!");
        }

        System.out.print("Do you want it to contain fries? (1/0): ");
        try {
            if(myInput.nextInt() == 1)
                containsFries = true;
        } catch (Exception IOException){
            throw new IOException("Invalid input has been given!");
        }

        System.out.print("Do you want it to contain onion? (1/0): ");
        try {
            if(myInput.nextInt() == 1)
                containsOnion = true;
        } catch (Exception IOException){
            throw new IOException("Invalid input has been given!");
        }

        System.out.print("Do you want it to contain tomato? (1/0): ");
        try {
            if(myInput.nextInt() == 1)
                containsTomato = true;
        } catch (Exception IOException){
            throw new IOException("Invalid input has been given!");
        }

        System.out.print("Do you want it to contain lettuce? (1/0): ");
        try {
            if(myInput.nextInt() == 1)
                containsLettuce = true;
        } catch (Exception IOException){
            throw new IOException("Invalid input has been given!");
        }

        System.out.print("Do you want it to contain pickles? (1/0): ");
        try {
            if(myInput.nextInt() == 1)
                containsPickles = true;
        } catch (Exception IOException){
            throw new IOException("Invalid input has been given!");
        }

        System.out.print("Do you want it to contain parsley? (1/0): ");
        try {
            if(myInput.nextInt() == 1)
                containsParsley = true;
        } catch (Exception IOException){
            throw new IOException("Invalid input has been given!");
        }

        System.out.print("Do you want it to contain ketchup? (1/0): ");
        try {
            if(myInput.nextInt() == 1)
                containsKetchup = true;
        } catch (Exception IOException){
            throw new IOException("Invalid input has been given!");
        }

        System.out.print("Do you want it to contain mayonnaise? (1/0): ");
        try {
            if(myInput.nextInt() == 1)
                containsMayonnaise = true;
        } catch (Exception IOException){
            throw new IOException("Invalid input has been given!");
        }

        Shaorma shaorma = new Shaorma(product, containsSalt, containsFries, containsOnion, containsTomato, containsLettuce, containsPickles, containsParsley, containsKetchup, containsMayonnaise);
        shaorma.SetDescription();
        shaorma.SetFoodName();

        shaormaList.add(shaorma);

        return shaormaList.size() - 1;
    }

    @Override
    public Shaorma GetNewShaorma(Product product){
        try {
            Shaorma shaorma = GetShaormaById(ReadNewShaorma(product));

            return shaorma;
        } catch (IOException e) {
            System.out.println(e.getMessage());

            return null;
        }
    }

}
