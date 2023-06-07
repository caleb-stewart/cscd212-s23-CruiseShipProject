package CruisePackage.DrinksPackage;

public class NoDrinksPackage implements DrinksPackage{

    private String drink;

    public NoDrinksPackage(){
        this.drink = "Water";
    }

    public String drinkBeverage() {
        return this.drink;
    }
}
