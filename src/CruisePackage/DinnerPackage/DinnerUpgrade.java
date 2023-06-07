package CruisePackage.DinnerPackage;

public class DinnerUpgrade implements DinnerPackage{

    private String dinner;

    public DinnerUpgrade(){
        this.dinner = "Steak and Potatoes";
    }

    @Override
    public String typeOfDinner() {
        return this.dinner;
    }
}
