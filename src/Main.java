import Cruise.AbstractCruiseBuilder;
import CruiseManager.CruiseManager;
import CruiseObserver.CruiseObserver;
import CruisePackage.DinnerPackage.*;
import CruisePackage.DrinksPackage.*;
import CruisePackage.WifiPackage.*;
import CruiseRoom.*;
import CruiseShip.*;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {

        CruiseManager manager = new CruiseManager();

        AbstractCruiseBuilder cruise1 = manager.buildCruise("AK", "Homer", "BR", "Natal",
                "Carnival", "Carnival Magic", "Family Friendly",
                new FreeDinner(), new NoDrinksPackage(), new NoWifi(), new InsideCabinRoom());
        manager.addPortToCruise("WA", "Spokane", 0);

        AbstractCruiseBuilder cruise2 = manager.buildCruise("JP", "Tokyo", "CU", "Havana",
                "CSCD212 Cruise", "Stu Steiners Ship", "Adults Only",
                new DinnerWithCaptain(), new UnlimitedAlcohol(), new UnlimitedWifi(), new CabinSuite());
        manager.addPortToCruise("NY", "New York", 1);

        CruiseObserver observer = new CruiseObserver();
        observer.add(manager);

        manager.menuOptions();


    }
}
