package Products.Comparator;

import Products.Product;

import java.util.Comparator;

public class ProductComparatorAscByPrice implements Comparator<Product> {

    @Override
    public int compare(Product product1, Product product2) {
        double val = product1.GetPrice() - product2.GetPrice();
        if(val > 0)
            return 1;
        else if(val < 0)
            return -1;
        else
            return 0;
    }
}
