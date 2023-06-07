package CruiseShip;

public class FamilyFriendlyCruiseShip extends CruiseShip{

    public FamilyFriendlyCruiseShip(final String shipComapny, final String shipName) {

        super(shipComapny, shipName);
        CruiseType = "Family Friendly";
        drinks = "slushies";
        entertainment = "Balloon animals and face painting";
        lightsOutTime = "10pm";
    }
}
