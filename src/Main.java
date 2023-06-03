import CruisePort.DepartureCruisePort;
import CruiseShip.AbstractCruiseShipFactory;
import CruiseShip.AdultsOnlyCruiseShip;
import CruiseShip.CruiseShipFactory;
import CruiseShip.*;

public class Main {

    public static void main(String args[]) {



        DepartureCruisePort dPort = new DepartureCruisePort();

        dPort.displayStartingOptions();

        AbstractCruiseShipFactory ShipFac = new CruiseShipFactory("Norweigan", "Calebs ship");

        CruiseShip familyFriendly = ShipFac.createShip("Family Friendly");
        familyFriendly.displayCruiseType();


    }
}
