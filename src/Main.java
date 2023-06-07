import Cruise.AbstractCruiseBuilder;
import CruiseManager.CruiseManager;
import CruisePackage.DinnerPackage.FreeDinner;
import CruisePackage.DrinksPackage.NoDrinksPackage;
import CruisePackage.WifiPackage.NoWifi;
import CruisePort.PortManager;
import CruiseRoom.InsideCabinRoom;
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
        /*PortManager startingPort = manager.createCruisePort();
        PortManager endingPort = manager.createCruisePort("AK", "Haines");*/

        AbstractCruiseBuilder cruise1 = manager.buildCruise("AK", "Haines", "BR", "Belem", "Carnival", "Carnival Magic", "Family Friendly", new FreeDinner(), new NoDrinksPackage(), new NoWifi(), new InsideCabinRoom());
        manager.displayCruiseSystemDetails();

        manager.buildCruise();
        manager.displayCruiseSystemDetails();


        //CruisePortManager depPort = new CruisePort();

        /*AbstractCruiseShipFactory ShipFac = new CruiseShipFactory();

        CruiseShip familyFriendly = ShipFac.createShip("Family Friendly");
        familyFriendly.displayCruiseType();*/


    }
}
