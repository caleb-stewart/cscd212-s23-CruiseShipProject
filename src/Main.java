import CruisePort.DepartureCruisePort;
import CruiseRoom.InsideCabinRoom;
import CruiseShip.AbstractCruiseShipFactory;
import CruiseShip.CruiseShipFactory;
import CruiseShip.*;
import CruiseRoom.*;

public class Main {

    public static void main(String args[]) {

        CruiseRoom room = new InsideCabinRoom();
        System.out.println(room.getRoom());
        CruiseRoom room1 = new InsideCabinRoom();
        System.out.println(room1.getRoom());
        System.out.println(room1.getRoom());

        DepartureCruisePort dPort = new DepartureCruisePort();

        dPort.displayStartingOptions();

        AbstractCruiseShipFactory ShipFac = new CruiseShipFactory("Norweigan", "Calebs ship");

        CruiseShip familyFriendly = ShipFac.createShip("Family Friendly");
        familyFriendly.displayCruiseType();


    }
}
