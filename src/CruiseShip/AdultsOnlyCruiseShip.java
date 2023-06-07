package CruiseShip;

public class AdultsOnlyCruiseShip extends CruiseShip{

    public AdultsOnlyCruiseShip(final String shipComapny, final String shipName) {

        super(shipComapny, shipName);
        CruiseType = "Adults Only";
        drinks = "cocktails";
        entertainment = "deck party";
        lightsOutTime = "3am";
    }

}
