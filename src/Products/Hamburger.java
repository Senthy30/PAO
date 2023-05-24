package Products;

public class Hamburger extends Product {

    private int idHamburger;
    private int idProduct;
    private boolean containsSalt;
    private boolean containsSesame;
    private boolean containsPepper;
    private boolean containsFries;
    private boolean containsOnion;
    private boolean containsTomato;
    private boolean containsLettuce;
    private boolean containsCheese;
    private boolean containsPickles;

    public Hamburger(int id, int idRestaurant){
        super(id, idRestaurant);
    }

    public Hamburger(Product product){
        super(product);
    }

    public Hamburger(Product product, int idHamburger, int idProduct, boolean containsSalt, boolean containsSesame, boolean containsPepper, boolean containsFries, boolean containsOnion, boolean containsTomato, boolean containsLettuce, boolean containsCheese, boolean containsPickles) {
        super(product);

        this.idHamburger = idHamburger;
        this.idProduct = idProduct;
        this.containsSalt = containsSalt;
        this.containsSesame = containsSesame;
        this.containsPepper = containsPepper;
        this.containsFries = containsFries;
        this.containsOnion = containsOnion;
        this.containsTomato = containsTomato;
        this.containsLettuce = containsLettuce;
        this.containsCheese = containsCheese;
        this.containsPickles = containsPickles;

        SetDescription();
        SetFoodName();
    }

    public Hamburger(Hamburger hamburger) {
        super(hamburger);

        this.containsSalt = hamburger.containsSalt;
        this.containsSesame = hamburger.containsSesame;
        this.containsPepper = hamburger.containsPepper;
        this.containsFries = hamburger.containsFries;
        this.containsOnion = hamburger.containsOnion;
        this.containsTomato = hamburger.containsTomato;
        this.containsLettuce = hamburger.containsLettuce;
        this.containsCheese = hamburger.containsCheese;
        this.containsPickles = hamburger.containsPickles;

        SetDescription();
        SetFoodName();
    }

    public void SetDescription() {
        String tempDescription = "A wonderful hamburger with a weight of " + this.weight + " grams and with a very delicate content of: ";
        if(containsSalt)
            tempDescription += "salt, ";
        if(containsSesame)
            tempDescription += "sesame, ";
        if(containsPepper)
            tempDescription += "pepper, ";
        if(containsFries)
            tempDescription += "fries, ";
        if(containsOnion)
            tempDescription += "onion, ";
        if(containsTomato)
            tempDescription += "tomato, ";
        if(containsLettuce)
            tempDescription += "lettuce, ";
        if(containsCheese)
            tempDescription += "cheese, ";
        if(containsPickles)
            tempDescription += "pickles, ";

        tempDescription = tempDescription.substring(0, tempDescription.length() - 2) + " ";
        tempDescription += "and a price of " + this.price + " RON.";

        this.description = tempDescription;
    }

    public int GetIdHamburger() {
        return idHamburger;
    }

    public void SetIdHamburger(int idHamburger) {
        this.idHamburger = idHamburger;
    }

    public void SetFoodName() {
        this.foodName = "Hamburger";
    }

    public boolean IsContainsSalt() {
        return containsSalt;
    }

    public void SetContainsSalt(boolean containsSalt) {
        this.containsSalt = containsSalt;
    }

    public boolean IsContainsSesame() {
        return containsSesame;
    }

    public void SetContainsSesame(boolean containsSesame) {
        this.containsSesame = containsSesame;
    }

    public boolean IsContainsPepper() {
        return containsPepper;
    }

    public void SetContainsPepper(boolean containsPepper) {
        this.containsPepper = containsPepper;
    }

    public boolean IsContainsFries() {
        return containsFries;
    }

    public void SetContainsFries(boolean containsFries) {
        this.containsFries = containsFries;
    }

    public boolean IsContainsOnion() {
        return containsOnion;
    }

    public void SetContainsOnion(boolean containsOnion) {
        this.containsOnion = containsOnion;
    }

    public boolean IsContainsTomato() {
        return containsTomato;
    }

    public void SetContainsTomato(boolean containsTomato) {
        this.containsTomato = containsTomato;
    }

    public boolean IsContainsLettuce() {
        return containsLettuce;
    }

    public void SetContainsLettuce(boolean containsLettuce) {
        this.containsLettuce = containsLettuce;
    }

    public boolean IsContainsCheese() {
        return containsCheese;
    }

    public void SetContainsCheese(boolean containsCheese) {
        this.containsCheese = containsCheese;
    }

    public boolean IsContainsPickles() {
        return containsPickles;
    }

    public void SetContainsPickles(boolean containsPickles) {
        this.containsPickles = containsPickles;
    }

    public int GetIdProduct() {
        return idProduct;
    }

    public void SetIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
}
