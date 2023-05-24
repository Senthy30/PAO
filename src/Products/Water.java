package Products;

public class Water extends Product {

    private int idWater;
    private int idProduct;
    private double pH;

    public Water(int id, int idRestaurant){
        super(id, idRestaurant);
    }

    public Water(Product product){
        super(product);
    }

    public Water(Product product, int idWater, int idProduct, double pH) {
        super(product);

        this.idWater = idWater;
        this.idProduct = idProduct;
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

    public int GetIdWater() {
        return idWater;
    }

    public void SetIdWater(int idWater) {
        this.idWater = idWater;
    }

    public int GetIdProduct() {
        return idProduct;
    }

    public void SetIdProduct(int idProduct) {
        this.idProduct = idProduct;
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
