package Products;

public class Shaorma extends Product {

    private int idShaorma;
    private int idProduct;
    private boolean containsSalt;
    private boolean containsFries;
    private boolean containsOnion;
    private boolean containsTomato;
    private boolean containsLettuce;
    private boolean containsPickles;
    private boolean containsParsley;
    private boolean containsKetchup;
    private boolean containsMayonnaise;

    public Shaorma(int id, int idRestaurant){
        super(id, idRestaurant);
    }

    public Shaorma(Product product){
        super(product);
    }

    public Shaorma(Product product, int idShaorma, int idProduct, boolean containsSalt, boolean containsFries, boolean containsOnion, boolean containsTomato, boolean containsLettuce, boolean containsPickles, boolean containsParsley, boolean containsKetchup, boolean containsMayonnaise) {
        super(product);

        this.idShaorma = idShaorma;
        this.idProduct = idProduct;
        this.containsSalt = containsSalt;
        this.containsFries = containsFries;
        this.containsOnion = containsOnion;
        this.containsTomato = containsTomato;
        this.containsLettuce = containsLettuce;
        this.containsPickles = containsPickles;
        this.containsParsley = containsParsley;
        this.containsKetchup = containsKetchup;
        this.containsMayonnaise = containsMayonnaise;

        SetDescription();
        SetFoodName();
    }

    // copy constructor

    public Shaorma(Shaorma shaorma) {
        super(shaorma);

        this.containsSalt = shaorma.containsSalt;
        this.containsFries = shaorma.containsFries;
        this.containsOnion = shaorma.containsOnion;
        this.containsTomato = shaorma.containsTomato;
        this.containsLettuce = shaorma.containsLettuce;
        this.containsPickles = shaorma.containsPickles;
        this.containsParsley = shaorma.containsParsley;
        this.containsKetchup = shaorma.containsKetchup;
        this.containsMayonnaise = shaorma.containsMayonnaise;

        SetDescription();
        SetFoodName();
    }

    public void SetDescription() {
        String tempDescription = "A wonderful shaorma with a weight of " + this.weight + " grams and with a very delicate content of: ";
        if(containsSalt)
            tempDescription += "salt, ";
        if(containsFries)
            tempDescription += "fries, ";
        if(containsOnion)
            tempDescription += "onion, ";
        if(containsTomato)
            tempDescription += "tomato, ";
        if(containsLettuce)
            tempDescription += "lettuce, ";
        if(containsPickles)
            tempDescription += "pickles, ";
        if(containsParsley)
            tempDescription += "parsley, ";
        if(containsKetchup)
            tempDescription += "ketchup, ";
        if(containsMayonnaise)
            tempDescription += "mayonnaise, ";

        tempDescription = tempDescription.substring(0, tempDescription.length() - 2) + " ";
        tempDescription += "and a price of " + this.price + " RON.";

        this.description = tempDescription;
    }

    public int GetIdShaorma() {
        return idShaorma;
    }

    public void SetIdShaorma(int idShaorma) {
        this.idShaorma = idShaorma;
    }

    public int GetIdProduct() {
        return idProduct;
    }

    public void SetIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public void SetFoodName() {
        this.foodName = "Shaorma";
    }

    public boolean IsContainsSalt() {
        return containsSalt;
    }

    public void SetContainsSalt(boolean containsSalt) {
        this.containsSalt = containsSalt;
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

    public boolean IsContainsPickles() {
        return containsPickles;
    }

    public void SetContainsPickles(boolean containsPickles) {
        this.containsPickles = containsPickles;
    }

    public boolean IsContainsParsley() {
        return containsParsley;
    }

    public void SetContainsParsley(boolean containsParsley) {
        this.containsParsley = containsParsley;
    }

    public boolean IsContainsKetchup() {
        return containsKetchup;
    }

    public void SetContainsKetchup(boolean containsKetchup) {
        this.containsKetchup = containsKetchup;
    }

    public boolean IsContainsMayonnaise() {
        return containsMayonnaise;
    }

    public void SetContainsMayonnaise(boolean containsMayonnaise) {
        this.containsMayonnaise = containsMayonnaise;
    }
}
