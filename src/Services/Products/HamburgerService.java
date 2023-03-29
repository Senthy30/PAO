package Services.Products;

import Products.Hamburger;
import Products.Product;
import Services.Products.Interfaces.HamburgerServiceInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HamburgerService implements HamburgerServiceInterface {

    private List<Hamburger> hamburgersList = new ArrayList<Hamburger>();
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
        return hamburgersList.get(id);
    }

    @Override
    public int ReadNewHamburger(Product product) {
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
        if(myInput.nextInt() == 1)
            containsSalt = true;

        System.out.print("Do you want it to contain sesame? (1/0): ");
        if(myInput.nextInt() == 1)
            containsSesame = true;

        System.out.print("Do you want it to contain pepper? (1/0): ");
        if(myInput.nextInt() == 1)
            containsPepper = true;

        System.out.print("Do you want it to contain fries? (1/0): ");
        if(myInput.nextInt() == 1)
            containsFries = true;

        System.out.print("Do you want it to contain onion? (1/0): ");
        if(myInput.nextInt() == 1)
            containsOnion = true;

        System.out.print("Do you want it to contain tomato? (1/0): ");
        if(myInput.nextInt() == 1)
            containsTomato = true;

        System.out.print("Do you want it to contain lettuce? (1/0): ");
        if(myInput.nextInt() == 1)
            containsLettuce = true;

        System.out.print("Do you want it to contain cheese? (1/0): ");
        if(myInput.nextInt() == 1)
            containsCheese = true;

        System.out.print("Do you want it to contain pickles? (1/0): ");
        if(myInput.nextInt() == 1)
            containsPickles = true;

        Hamburger hamburger = new Hamburger(product, containsSalt, containsSesame, containsPepper, containsFries, containsOnion, containsTomato, containsLettuce, containsCheese, containsPickles);
        hamburger.SetDescription();
        hamburger.SetFoodName();

        hamburgersList.add(hamburger);

        return hamburgersList.size() - 1;
    }

    @Override
    public Hamburger GetNewHamburger(Product product){
        Hamburger hamburger = GetHamburgerById(ReadNewHamburger(product));

        return hamburger;
    }

}
