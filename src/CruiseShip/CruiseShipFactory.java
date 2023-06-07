package CruiseShip;

public class CruiseShipFactory extends AbstractCruiseShipFactory {

    public CruiseShipFactory() {
        super();
    }

    public CruiseShipFactory(final String companyName, final String shipName) {
        super(companyName, shipName);
    }

    public CruiseShip chooseTypeOfShip() {
        System.out.println("What type of ship would you like?");
        System.out.println("1.) Family Friendly");
        System.out.println("2.) Adults Only");

        int shipChoice = Integer.parseInt(kb.nextLine());

        if(shipChoice == 1) {
            return createShip("Family Friendly");
        }
        else if (shipChoice == 2) {
            return createShip("Adults Only");
        }
        else {
            throw new IndexOutOfBoundsException(shipChoice + " was not in the range of options");
        }
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
