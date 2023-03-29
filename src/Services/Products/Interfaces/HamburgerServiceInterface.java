package Services.Products.Interfaces;

import Products.Hamburger;
import Products.Product;

public interface HamburgerServiceInterface {

    public Hamburger GetHamburgerById(int id);

    public int ReadNewHamburger(Product product);

    public Hamburger GetNewHamburger(Product product);

}
