import CruiseManager.CruiseManager;
import CruisePort.PortManager;
import CruiseShip.*;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {

        /*CruiseRoom room = new InsideCabinRoom();
        System.out.println(room.getRoom());
        CruiseRoom room1 = new InsideCabinRoom();
        System.out.println(room1.getRoom());
        System.out.println(room1.getRoom());*/

        /*CruisePortManager dPort = new CruisePort();
        System.out.println(dPort.getCountryPort());
        System.out.println(dPort.getLocationName());*/

        CruiseManager manager = new CruiseManager();
        PortManager startingPort = manager.createCruisePort();
        PortManager endingPort = manager.createCruisePort("AK", "Haines");

        CruiseShip ship = manager.createCruiseShip();

        //CruisePortManager depPort = new CruisePort();

        /*AbstractCruiseShipFactory ShipFac = new CruiseShipFactory();

        CruiseShip familyFriendly = ShipFac.createShip("Family Friendly");
        familyFriendly.displayCruiseType();*/


    }
}
