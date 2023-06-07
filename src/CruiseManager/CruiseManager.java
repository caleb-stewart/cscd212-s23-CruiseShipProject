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
import java.util.Scanner;

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

        return new CruisePort(cruisePortCreate.getCountryPort().strip(),
                cruisePortCreate.getLocationName().substring(cruisePortCreate.getLocationName().indexOf(",")+ 1).strip());

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

    public String displayCruiseSystemDetails() {
        String cruiseSystemDetails = "";

        for(int i = 0; i < cruiseList.size(); i++) {
            cruiseSystemDetails += i + 1 + ".) " + cruiseList.get(i).getPorts().get(0).getCountryPort() + ", " + cruiseList.get(i).getPorts().get(0).getLocationName()
                    + " to " + cruiseList.get(i).getPorts().get(cruiseList.get(i).getPorts().size() - 1).getCountryPort() + ", " +
                            cruiseList.get(i).getPorts().get(cruiseList.get(i).getPorts().size() - 1).getLocationName();

            if(cruiseList.get(i).getPorts().size() > 2) {
                cruiseSystemDetails += "\n\tStops along the way: ";
                int x = 1;
                while(x < cruiseList.get(i).getPorts().size() - 1) {
                    cruiseSystemDetails += cruiseList.get(i).getPorts().get(x).getCountryPort() + ", " + cruiseList.get(i).getPorts().get(x).getLocationName() + " -> ";
                    ++x;
                }

            }
            cruiseSystemDetails += "\n\tShip: " + cruiseList.get(i).getShip().getShipCompany() + ": " + cruiseList.get(i).getShip().getShipName();
            cruiseSystemDetails += "\n\tCruise Type: ";
            cruiseSystemDetails += cruiseList.get(i).getShip().displayCruiseType();
            cruiseSystemDetails += "\n\tRoom: " + cruiseList.get(i).getRoom().getRoom();
            cruiseSystemDetails += "\n\tDinner Package: " + cruiseList.get(i).getDinnerPackage().typeOfDinner();
            cruiseSystemDetails += "\n\tDrinks Package: " + cruiseList.get(i).getDrinksPackage().drinkBeverage();
            cruiseSystemDetails += "\n\tWifi Package: " + cruiseList.get(i).getWifiPackage().wifiStrength();
            cruiseSystemDetails += "\n\tNights: " + cruiseList.get(i).getNumDaysSailing();
            cruiseSystemDetails += "\n\tDays in destination port: " + cruiseList.get(i).getNumDaysInDestinationPort() + "\n";


        }
        return cruiseSystemDetails;
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

    public void addPortToCruise () throws CloneNotSupportedException {
        int cruiseChoice = chooseCruiseFromList();

        cruiseList.get(cruiseChoice).addPort(createCruisePort());
    }

    public int chooseCruiseFromList() {

        for(int i = 0; i < cruiseList.size(); i++) {
            System.out.println(i + 1 + ".) " + cruiseList.get(i).getPorts().get(0).getCountryPort() + ", " + cruiseList.get(i).getPorts().get(0).getLocationName()
                    + " to " + cruiseList.get(i).getPorts().get(cruiseList.get(i).getPorts().size() - 1).getCountryPort() + ", " +
                    cruiseList.get(i).getPorts().get(cruiseList.get(i).getPorts().size() - 1).getLocationName());
        }

        Scanner kb = new Scanner(System.in);
        System.out.println("Please choose a cruise from the list above: ");
        int cruiseChoice = Integer.parseInt(kb.nextLine());

        return cruiseChoice - 1;
    }
}
