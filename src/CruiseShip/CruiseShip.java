package CruiseShip;

public abstract class CruiseShip {

    protected String CruiseType;
    protected String drinks;
    protected String entertainment;
    protected String lightsOutTime;

    protected String shipCompany;
    protected String shipName;

    public CruiseShip(final String shipComapny, final String shipName) {
        this.shipCompany = shipComapny;
        this.shipName = shipName;
    }

    public void displayCruiseType() {
        System.out.println("We are on a " + this.CruiseType + " cruise");
        System.out.println("\t\tWe are drinking " + this.drinks);
        System.out.println("\t\tOur entertainment is " + this.entertainment);
        System.out.println("\t\tOur lights out time is " + this.lightsOutTime);
    }

    public String getShipCompany() {
        return this.shipCompany;
    }

    public String getShipName() {
        return this.shipName;
    }

}
