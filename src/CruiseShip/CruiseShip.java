package CruiseShip;

public abstract class CruiseShip {

    protected String CruiseType;
    protected String drinks;
    protected String entertainment;
    protected String lightsOutTime;

    public void displayCruiseType() {
        System.out.println("We are on a " + CruiseType + " cruise");
        System.out.println("We are drinking " + drinks);
        System.out.println("Our entertainment is " + entertainment);
    }
}
