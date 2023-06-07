package CruisePackage.DinnerPackage;

public class DinnerWithCaptain implements DinnerPackage{

    private String dinner;

    public DinnerWithCaptain(){
        this.dinner = "Steak and Lobster with Captain Caleb Stewart";
    }

    @Override
    public String typeOfDinner() {
        return this.dinner;
    }
}
