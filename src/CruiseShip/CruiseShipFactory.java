package CruiseShip;

public class CruiseShipFactory extends AbstractCruiseShipFactory {

    public CruiseShipFactory(final String parentCompany, final String shipName) {
        super(parentCompany, shipName);
    }

    @Override
    public CruiseShip createShip(final String shipType) {

        if(shipType.equals("Family Friendly")) {
            return new FamilyFriendlyCruiseShip();
        }

        else if(shipType.equals("Adults Only")) {
            return new AdultsOnlyCruiseShip();
        }

        else {
            throw new IllegalArgumentException("Bad Params in CruiseShipFactory createShip");
        }
    }
}
