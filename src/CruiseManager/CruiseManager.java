package CruiseManager;

import Cruise.AbstractCruiseBuilder;
import Cruise.CruiseBuilder;
import CruisePackage.DinnerPackage.DinnerPackage;
import CruisePackage.DrinksPackage.DrinksPackage;
import CruisePackage.WifiPackage.WifiPackage;
import CruisePort.*;
import CruiseShip.*;
import CruiseRoom.*;

import java.util.ArrayList;

public class CruiseManager {//Observer???

    PortManager cruisePort = new CruisePort();

    protected ArrayList<AbstractCruiseBuilder> cruiseList;

    public CruiseManager() {
        cruiseList = new ArrayList<AbstractCruiseBuilder>();
    }

    public PortManager createCruisePort() throws CloneNotSupportedException{

        PortManager cruisePortCreate = cruisePort.clone();
        cruisePortCreate.chooseCruise();

        cruisePort.setLocationName(cruisePortCreate.getLocationNameList());

        return new CruisePort(cruisePortCreate.getCountryPort(), cruisePortCreate.getLocationName());

    }

    public PortManager createCruisePort(final String countryName, final String locationName) {

        if(cruisePort.getLocationNameList().contains(countryName + ", " + locationName)) {
            cruisePort.getLocationNameList().remove(countryName + ", " + locationName);
        }

        return new CruisePort(countryName, locationName);
    }

    public CruiseShip createCruiseShip() {

        AbstractCruiseShipFactory shipFac = new CruiseShipFactory();
        return shipFac.chooseTypeOfShip();

    }

    public CruiseShip createCruiseShip(final String companyName, final String shipName, final String shipType) {
        if(companyName == null || companyName.isEmpty() || shipName == null || shipName.isEmpty() || shipType == null || shipType.isEmpty()) {
            throw new IllegalArgumentException("Bad Params in CruiseManager createCruiseShip");
        }

        AbstractCruiseShipFactory shipFac = new CruiseShipFactory(companyName, shipName);
        return shipFac.createShip(shipType);

    }

    public void displayCruiseSystemDetails() {

        for(int i = 0; i < cruiseList.size(); i++) {
            System.out.println(i + 1 + ".) " + cruiseList.get(i).getPorts().get(0) + " to " + cruiseList.get(i).getPorts().get(cruiseList.get(i).getPorts().size()));
            System.out.println("Ship: " + cruiseList.get(i).getShip();
        }

    }

    public AbstractCruiseBuilder buildCruise() throws CloneNotSupportedException {

        PortManager startingPort = createCruisePort();
        PortManager endingPort = createCruisePort();

        CruiseShip ship = createCruiseShip();

        AbstractCruiseBuilder cruise = new CruiseBuilder(startingPort, endingPort, ship);

        cruise.setDinnerPackageAccommodation();
        cruise.setDrinksPackageAccommodation();
        cruise.setWifiPackageAccommodation();

        this.cruiseList.add(cruise);
        return cruise;
    }

    public AbstractCruiseBuilder buildCruise(final String startingCountryName, final String startingLocationName, final String endingCountryName,
                            final String endingLocationName, final String companyName, final String shipName, final String shipType,
                            final DinnerPackage dinnerPackage, final DrinksPackage drinksPackage, final WifiPackage wifiPackage,
                                             final CruiseRoom room) {

        if(startingCountryName == null || startingCountryName.isEmpty() || startingLocationName == null || startingLocationName.isEmpty() ||
                endingLocationName == null || endingLocationName.isEmpty() || endingCountryName == null || endingCountryName.isEmpty() ||
                companyName == null || companyName.isEmpty() || shipName == null || shipName.isEmpty() || shipType == null ||
                shipType.isEmpty() || dinnerPackage == null || drinksPackage == null || wifiPackage == null) {

            throw new IllegalArgumentException("Bad Params in CruiseManager buildCruise");
        }

        PortManager startingPort = createCruisePort(startingCountryName, startingLocationName);
        PortManager endingPort = createCruisePort(endingCountryName, endingLocationName);

        CruiseShip ship = createCruiseShip(companyName, shipName, shipType);
        AbstractCruiseBuilder cruise = new CruiseBuilder(startingPort, endingPort, ship);

        cruise.setWifiPackageAccommodation(wifiPackage);
        cruise.setDrinksPackageAccommodation(drinksPackage);
        cruise.setDinnerPackageAccommodation(dinnerPackage);
        cruise.setRoom(room);

        this.cruiseList.add(cruise);
        return cruise;
    }

    public void changeAccommodationForCruise(final DinnerPackage dinnerPackage, final DrinksPackage drinksPackage, final WifiPackage wifiPackage) {
        if(dinnerPackage == null || drinksPackage == null || wifiPackage == null) {
            throw new IllegalArgumentException("Bad Params in CruiseManager changeAccommodationForCruise");
        }

        System.out.println("What cruise would you like to change the accommodation for?");

        /*for(int i = 0; i < cruiseList.size(); i++) {
            System.out.println(i + ".) " + cruiseList.get(i).getStartingPort().getLocationName() + " to " + cruiseList.get(i).getEndingPort().getLocationName());
        }*/
    }
}
