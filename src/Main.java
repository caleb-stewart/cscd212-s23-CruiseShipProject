import Cruise.AbstractCruiseBuilder;
import CruiseManager.CruiseManager;
import CruiseObserver.CruiseObserver;
import CruisePackage.DinnerPackage.FreeDinner;
import CruisePackage.DrinksPackage.NoDrinksPackage;
import CruisePackage.WifiPackage.NoWifi;
import CruisePort.PortManager;
import CruiseRoom.InsideCabinRoom;
import CruiseShip.*;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {

        CruiseManager manager = new CruiseManager();

        AbstractCruiseBuilder cruise1 = manager.buildCruise("AK", "Haines", "BR", "Belem", "Carnival", "Carnival Magic", "Family Friendly", new FreeDinner(), new NoDrinksPackage(), new NoWifi(), new InsideCabinRoom());
        manager.addPortToCruise("WA", "Spokane", 0);

        CruiseObserver observer = new CruiseObserver();
        observer.add(manager);

        manager.menuOptions();


    }
}
