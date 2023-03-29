package Services.Products;

import Products.Juice;
import Products.Product;
import Services.Products.Interfaces.JuiceServiceInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JuiceService implements JuiceServiceInterface {

    private List<Juice> juicesList = new ArrayList<>();
    private static JuiceService juiceService;

    private JuiceService() { }

    public static JuiceService getInstance(){
        if(juiceService == null){
            juiceService = new JuiceService();
        }
        return juiceService;
    }

    @Override
    public Juice GetJuiceById(int id) {
        return juicesList.get(id);
    }

    @Override
    public int ReadNewJuice(Product product){
        Scanner scanner = new Scanner(System.in);

        int typeJuice = 0;
        boolean containsSugar = false;

        System.out.print("What type of juice do you want? (1 - Apple, 2 - Orange, 3 - Pineapple, 4 - Strawberry): ");
        typeJuice = scanner.nextInt();
        if(typeJuice < 1 || typeJuice > 4)
            throw new IllegalArgumentException("Invalid type of juice!");

        System.out.print("Do you want it to contain sugar? (1/0): ");
        if(scanner.nextInt() == 1)
            containsSugar = true;

        Juice juice = new Juice(product, typeJuice, containsSugar);
        juice.SetDescription();
        juice.SetFoodName();

        juicesList.add(juice);

        return juicesList.size() - 1;
    }

    @Override
    public Juice GetNewJuice(Product product){
        Juice juice = GetJuiceById(ReadNewJuice(product));

        return juice;
    }

}
