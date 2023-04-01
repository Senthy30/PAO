package Services.Products.Interfaces;

import Products.Hamburger;
import Products.Product;

import java.io.IOException;

public interface HamburgerServiceInterface {

    public Hamburger GetHamburgerById(int id);

    public int ReadNewHamburger(Product product) throws IOException;

    public Hamburger GetNewHamburger(Product product);

}
