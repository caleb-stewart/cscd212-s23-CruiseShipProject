package CruisePackage.DinnerPackage;

public class FreeDinner implements DinnerPackage{

    private String dinner;

    public FreeDinner(){
        this.dinner = "ez-Mac and Cheese";
    }

    @Override
    public String typeOfDinner() {
        return this.dinner;
    }
}
