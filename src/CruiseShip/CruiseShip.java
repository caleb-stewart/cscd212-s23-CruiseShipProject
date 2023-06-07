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

    public String displayCruiseType() {
        String displayCruiseShip = "";
        displayCruiseShip += "We are on a " + this.CruiseType + " cruise\n";
        displayCruiseShip += "\t\tWe are drinking " + this.drinks + "\n";
        displayCruiseShip += "\t\tOur entertainment is " + this.entertainment + "\n";
        displayCruiseShip += "\t\tOur lights out time is " + this.lightsOutTime;
        return displayCruiseShip;
    }

    public String getShipCompany() {
        return this.shipCompany;
    }

    public String getShipName() {
        return this.shipName;
    }

}
