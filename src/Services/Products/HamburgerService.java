package Services.Products;

import Products.Hamburger;
import Products.Product;
import Repositories.Products.HamburgerRepository;
import Services.Products.Interfaces.HamburgerServiceInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HamburgerService implements HamburgerServiceInterface {

    //private List<Hamburger> hamburgersList = new ArrayList<Hamburger>();
    private HamburgerRepository hamburgerRepository = new HamburgerRepository();
    private static HamburgerService instance;

    private HamburgerService() { }

    public static HamburgerService getInstance(){
        if(instance == null){
            instance = new HamburgerService();
        }
        return instance;
    }

    @Override
    public Hamburger GetHamburgerById(int id) {
        return hamburgerRepository.Select(id);
    }

    @Override
    public int ReadNewHamburger(Product product) throws IOException {
        Scanner myInput = new Scanner( System.in );

        boolean containsSalt = false;
        boolean containsSesame = false;
        boolean containsPepper = false;
        boolean containsFries = false;
        boolean containsOnion = false;
        boolean containsTomato = false;
        boolean containsLettuce = false;
        boolean containsCheese = false;
        boolean containsPickles = false;

        System.out.print("Do you want it to contain salt? (1/0): ");
        try{
            if(myInput.nextInt() == 1)
                containsSalt = true;
        } catch (Exception IOException){
            throw new IOException();
        }

        System.out.print("Do you want it to contain sesame? (1/0): ");
        try {
            if(myInput.nextInt() == 1)
                containsSesame = true;
        } catch (Exception IOException){
            throw new IOException();
        }

        System.out.print("Do you want it to contain pepper? (1/0): ");
        try {
            if(myInput.nextInt() == 1)
                containsPepper = true;
        } catch (Exception IOException){
            throw new IOException();
        }

        System.out.print("Do you want it to contain fries? (1/0): ");
        try {
            if(myInput.nextInt() == 1)
                containsFries = true;
        } catch (Exception IOException){
            throw new IOException();
        }

        System.out.print("Do you want it to contain onion? (1/0): ");
        try {
            if(myInput.nextInt() == 1)
                containsOnion = true;
        } catch (Exception IOException){
            throw new IOException();
        }

        System.out.print("Do you want it to contain tomato? (1/0): ");
        try {
            if(myInput.nextInt() == 1)
                containsTomato = true;
        } catch (Exception IOException){
            throw new IOException();
        }

        System.out.print("Do you want it to contain lettuce? (1/0): ");
        try {
            if(myInput.nextInt() == 1)
                containsLettuce = true;
        } catch (Exception IOException){
            throw new IOException();
        }

        System.out.print("Do you want it to contain cheese? (1/0): ");
        try {
            if(myInput.nextInt() == 1)
                containsCheese = true;
        } catch (Exception IOException){
            throw new IOException();
        }

        System.out.print("Do you want it to contain pickles? (1/0): ");
        try {
            if(myInput.nextInt() == 1)
                containsPickles = true;
        } catch (Exception IOException){
            throw new IOException();
        }

        int id = hamburgerRepository.GetIdCounter();
        Hamburger hamburger = new Hamburger(product, id, product.GetId(), containsSalt, containsSesame, containsPepper, containsFries, containsOnion, containsTomato, containsLettuce, containsCheese, containsPickles);
        hamburger.SetDescription();

        hamburgerRepository.Insert(hamburger);

        return id;
    }

    @Override
    public Hamburger GetNewHamburger(Product product){
        try {
            Hamburger hamburger = GetHamburgerById(ReadNewHamburger(product));

            return hamburger;
        } catch (IOException e) {
            System.out.println("Invalid input has been given!");

            return null;
        }
    }

}
