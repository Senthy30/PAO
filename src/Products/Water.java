package Products;

public class Water extends Product {

    private double pH;

    public Water(int idRestaurant){
        super(idRestaurant);
    }

    public Water(Product product){
        super(product);
    }

    public Water(Product product, double pH) {
        super(product);

        this.pH = pH;
    }

    // copy constructor

    public Water(Water water) {
        super(water);

        this.pH = water.pH;
    }

    public void SetDescription(){
        // copilot write something good about water
        String tempDescription = "A wonderful water with a weight of " + this.weight + " grams and with a pH of: " + this.pH + "and a price of: " + this.price + "RON. ";

        this.description = tempDescription;
    }

    public void SetFoodName(){
        this.foodName = "Water";
    }

    public double GetpH() {
        return pH;
    }

    public void SetpH(double pH) {
        this.pH = pH;
    }
}
