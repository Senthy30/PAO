package Services.Products;

import Products.Juice;
import Products.Product;
import Repositories.Products.JuiceRepository;
import Services.Products.Interfaces.JuiceServiceInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JuiceService implements JuiceServiceInterface {

    // private List<Juice> juicesList = new ArrayList<>();
    private JuiceRepository juiceRepository = new JuiceRepository();
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
        return juiceRepository.Select(id);
    }

    @Override
    public int ReadNewJuice(Product product) throws IOException, IllegalArgumentException {
        Scanner scanner = new Scanner(System.in);

        int typeJuice = 0;
        boolean containsSugar = false;

        System.out.print("What type of juice do you want? (1 - Apple, 2 - Orange, 3 - Pineapple, 4 - Strawberry): ");
        try {
            typeJuice = scanner.nextInt();
            if (typeJuice < 1 || typeJuice > 4)
                throw new IllegalArgumentException("Invalid type of juice!");
        } catch (Exception IOException){
            throw new IOException("Invalid input has been give!");
        }

        System.out.print("Do you want it to contain sugar? (1/0): ");
        try {
            if (scanner.nextInt() == 1)
                containsSugar = true;
        } catch (Exception IOException){
            throw new IOException("Invalid input has been give!");
        }

        int id = juiceRepository.GetIdCounter();
        Juice juice = new Juice(product, id, product.GetId(), typeJuice, containsSugar);
        juice.SetDescription();
        juice.SetFoodName();

        juiceRepository.Insert(juice);

        return id;
    }

    @Override
    public Juice GetNewJuice(Product product){
        try {
            Juice juice = GetJuiceById(ReadNewJuice(product));

            return juice;
        } catch (IOException e) {
            System.out.println(e.getMessage());

            return null;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());

            return null;
        }
    }

}
