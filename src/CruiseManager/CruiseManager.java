package CruiseManager;

import Cruise.AbstractCruiseBuilder;
import Cruise.CruiseBuilder;
import CruisePackage.DinnerPackage.DinnerPackage;
import CruisePackage.DrinksPackage.DrinksPackage;
import CruisePackage.WifiPackage.WifiPackage;
import CruisePort.*;
import CruiseShip.*;
import CruiseRoom.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Scanner;

public class CruiseManager {

    private PortManager cruisePort = new CruisePort();
    protected ArrayList<AbstractCruiseBuilder> cruiseList;
    private PropertyChangeSupport pcs;

    public CruiseManager() {

        cruiseList = new ArrayList<AbstractCruiseBuilder>();
        this.pcs = new PropertyChangeSupport(this);
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
                cruiseSystemDetails += "\n\tPorts: ";
                int x = 0;
                while(x < cruiseList.get(i).getPorts().size() - 1) {
                    cruiseSystemDetails += cruiseList.get(i).getPorts().get(x).getCountryPort() + ", " + cruiseList.get(i).getPorts().get(x).getLocationName() + " --> ";
                    ++x;
                }
                cruiseSystemDetails += cruiseList.get(i).getPorts().get(cruiseList.get(i).getPorts().size() - 1).getCountryPort() +
                        ", " + cruiseList.get(i).getPorts().get(cruiseList.get(i).getPorts().size() - 1).getLocationName();

            }
            cruiseSystemDetails += "\n\tShip: " + cruiseList.get(i).getShip().getShipCompany() + ": " + cruiseList.get(i).getShip().getShipName();
            cruiseSystemDetails += "\n\tCruise Type: ";
            cruiseSystemDetails += cruiseList.get(i).getShip().displayCruiseType();
            cruiseSystemDetails += "\n\tRoom: " + cruiseList.get(i).getRoom().getRoom();
            cruiseSystemDetails += "\n\tDinner Package: " + cruiseList.get(i).getDinnerPackage().typeOfDinner();
            cruiseSystemDetails += "\n\tDrinks Package: " + cruiseList.get(i).getDrinksPackage().drinkBeverage();
            cruiseSystemDetails += "\n\tWifi Package: " + cruiseList.get(i).getWifiPackage().wifiStrength();
            cruiseSystemDetails += "\n\tDeparture Date: " + cruiseList.get(i).getDate();
            cruiseSystemDetails += "\n\tNights: " + cruiseList.get(i).getNumDaysSailing();
            cruiseSystemDetails += "\n\tDays in destination port: " + cruiseList.get(i).getNumDaysInDestinationPort() + "\n";
            cruiseSystemDetails += "-----------------------------------------------------\n";


        }
        return cruiseSystemDetails;
    }

    public AbstractCruiseBuilder buildCruise() throws CloneNotSupportedException {

        PortManager startingPort = createCruisePort();
        PortManager endingPort = createCruisePort();

        CruiseShip ship = createCruiseShip();

        AbstractCruiseBuilder cruise = new CruiseBuilder(startingPort, endingPort, ship);

        cruise.setRoom();

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

    public void changeAccommodationForCruise() {

        int cruiseChoice = chooseCruiseFromList();

        cruiseList.get(cruiseChoice).setDinnerPackageAccommodation();
        cruiseList.get(cruiseChoice).setDrinksPackageAccommodation();
        cruiseList.get(cruiseChoice).setWifiPackageAccommodation();

        cruiseList.get(cruiseChoice).setRoom();

    }

    public void addPortToCruise () throws CloneNotSupportedException {
        int cruiseChoice = chooseCruiseFromList();

        cruiseList.get(cruiseChoice).addPort(createCruisePort());
        cruiseList.get(cruiseChoice).setNumDaysSailing(cruiseList.get(cruiseChoice).getNumDaysSailing() + 5);
    }

    public void addPortToCruise(final String countryPort, final String locationName, final int cruiseChoice) throws CloneNotSupportedException {

        if(countryPort == null || countryPort.isEmpty() || locationName == null || locationName.isEmpty()) {
            throw new IllegalArgumentException("Bad Params in CruiseManager addPortToCruise");
        }

        cruiseList.get(cruiseChoice).addPort(createCruisePort(countryPort, locationName));
        cruiseList.get(cruiseChoice).setNumDaysSailing(cruiseList.get(cruiseChoice).getNumDaysSailing() + 5);
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

    public void menuOptions() {

        Scanner kb = new Scanner(System.in);
        int choice = 0;

        do {
            ArrayList<AbstractCruiseBuilder> newCruiseList = this.cruiseList;
            System.out.println("1.) Create a new cruise");
            System.out.println("2.) Add a port to a cruise");
            System.out.println("3.) Change the accommodation for a cruise");
            System.out.println("4.) Display all cruises");
            System.out.println("5.) Exit");
            System.out.println("Please choose an option: ");
            choice = Integer.parseInt(kb.nextLine());

            switch(choice) {
                case 1:
                    try {
                        buildCruise();
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        addPortToCruise();
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    changeAccommodationForCruise();
                    break;
                case 4:
                    System.out.println(displayCruiseSystemDetails());
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
            updateObserver(newCruiseList);
        } while(choice != 5);
    }

    public void addPropertyChangeListener(final PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(final PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

    public void updateObserver(final ArrayList<AbstractCruiseBuilder> oldCruiseList) {
        for(int i = 0; i < cruiseList.size(); i++) {
            this.pcs.firePropertyChange("CruiseList", oldCruiseList, cruiseList);
        }

    }
}
