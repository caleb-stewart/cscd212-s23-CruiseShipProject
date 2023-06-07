package CruisePackage.DrinksPackage;

public class UnlimitedAlcohol implements DrinksPackage{

    private String drink;

    public UnlimitedAlcohol(){
        this.drink = "Margarita";
    }

    public String drinkBeverage() {
        return this.drink;
    }

}
