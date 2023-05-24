package Products;

public class Juice extends Product {

    private int idJuice;
    private int idProduct;
    private int typeJuice;
    private boolean containsSugar;

    public Juice(int id, int idRestaurant){
        super(id, idRestaurant);
    }

    public Juice(Product product){
        super(product);
    }

    public Juice(Product product, int idJuice, int idProduct, int typeJuice, boolean containsSugar) {
        super(product);

        this.idJuice = idJuice;
        this.idProduct = idProduct;
        this.typeJuice = typeJuice;
        this.containsSugar = containsSugar;
    }

    // copy constructor

    public Juice(Juice juice) {
        super(juice);

        this.typeJuice = juice.typeJuice;
        this.containsSugar = juice.containsSugar;
    }

    public void SetDescription(){
        // copilot write something good about juice
        String tempDescription = "A wonderful juice with a weight of " + this.weight + " grams and with a type of: ";
        if(typeJuice == 1)
            tempDescription += "Orange juice ";
        if(typeJuice == 2)
            tempDescription += "Apple juice ";
        if(typeJuice == 3)
            tempDescription += "Pineapple juice ";
        if(typeJuice == 4)
            tempDescription += "Strawberry juice ";

        if(containsSugar)
            tempDescription += "and contains sugar ";
        else tempDescription += "and doesn't contain sugar ";

        tempDescription += "and a price of: " + this.price + "RON. ";

        this.description = tempDescription;
    }

    public int GetIdJuice() {
        return idJuice;
    }

    public void SetIdJuice(int idJuice) {
        this.idJuice = idJuice;
    }

    public int GetIdProduct() {
        return idProduct;
    }

    public void SetIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public void SetFoodName(){
        this.foodName = "Juice";
    }

    public int GetTypeJuice() {
        return typeJuice;
    }

    public void SetTypeJuice(int typeJuice) {
        this.typeJuice = typeJuice;
    }

    public boolean IsContainsSugar() {
        return containsSugar;
    }

    public void SetContainsSugar(boolean containsSugar) {
        this.containsSugar = containsSugar;
    }
}
