package Products;

public class Pizza extends Product {

    private boolean containsSalt;
    private boolean containsMozzarella;
    private boolean containsTomato;
    private boolean containsPepperoni;
    private boolean containsMushrooms;
    private boolean containsOlives;
    private boolean containsOnion;

    public Pizza(int idRestaurant){
        super(idRestaurant);
    }

    public Pizza(Product product){
        super(product);
    }

    public Pizza(Product product, boolean containsSalt, boolean containsMozzarella, boolean containsTomato, boolean containsPepperoni, boolean containsMushrooms, boolean containsOlives, boolean containsOnion) {
        super(product);

        this.containsSalt = containsSalt;
        this.containsMozzarella = containsMozzarella;
        this.containsTomato = containsTomato;
        this.containsPepperoni = containsPepperoni;
        this.containsMushrooms = containsMushrooms;
        this.containsOlives = containsOlives;
        this.containsOnion = containsOnion;

        SetDescription();
        SetFoodName();
    }

    // copy constructor

    public Pizza(Pizza pizza) {
        super(pizza);

        this.containsSalt = pizza.containsSalt;
        this.containsMozzarella = pizza.containsMozzarella;
        this.containsTomato = pizza.containsTomato;
        this.containsPepperoni = pizza.containsPepperoni;
        this.containsMushrooms = pizza.containsMushrooms;
        this.containsOlives = pizza.containsOlives;
        this.containsOnion = pizza.containsOnion;

        SetDescription();
        SetFoodName();
    }

    public void SetDescription() {
        String tempDescription = "A wonderful pizza with a weight of " + this.weight + " grams and with a very delicate content of: ";
        if(containsSalt)
            tempDescription += "salt, ";
        if(containsMozzarella)
            tempDescription += "mozzarella, ";
        if(containsTomato)
            tempDescription += "tomato, ";
        if(containsPepperoni)
            tempDescription += "pepperoni, ";
        if(containsMushrooms)
            tempDescription += "mushrooms, ";
        if(containsOlives)
            tempDescription += "olives, ";
        if(containsOnion)
            tempDescription += "onion, ";

        tempDescription = tempDescription.substring(0, tempDescription.length() - 2) + " ";
        tempDescription += "and a price of " + this.price + " RON.";

        this.description = tempDescription;
    }

    public void SetFoodName() {
        this.foodName = "Pizza";
    }

    public boolean IsContainsSalt() {
        return containsSalt;
    }

    public void SetContainsSalt(boolean containsSalt) {
        this.containsSalt = containsSalt;
    }

    public boolean IsContainsMozzarella() {
        return containsMozzarella;
    }

    public void SetContainsMozzarella(boolean containsMozzarella) {
        this.containsMozzarella = containsMozzarella;
    }

    public boolean IsContainsTomato() {
        return containsTomato;
    }

    public void SetContainsTomato(boolean containsTomato) {
        this.containsTomato = containsTomato;
    }

    public boolean IsContainsPepperoni() {
        return containsPepperoni;
    }

    public void SetContainsPepperoni(boolean containsPepperoni) {
        this.containsPepperoni = containsPepperoni;
    }

    public boolean IsContainsMushrooms() {
        return containsMushrooms;
    }

    public void SetContainsMushrooms(boolean containsMushrooms) {
        this.containsMushrooms = containsMushrooms;
    }

    public boolean IsContainsOlives() {
        return containsOlives;
    }

    public void SetContainsOlives(boolean containsOlives) {
        this.containsOlives = containsOlives;
    }

    public boolean IsContainsOnion() {
        return containsOnion;
    }

    public void SetContainsOnion(boolean containsOnion) {
        this.containsOnion = containsOnion;
    }
}
