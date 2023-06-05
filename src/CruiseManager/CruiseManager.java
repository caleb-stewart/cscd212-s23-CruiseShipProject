package CruiseManager;

import CruisePort.*;
import CruiseShip.*;

public class CruiseManager {//Observer???

    PortManager cruisePort = new CruisePort();

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

    public void displayCruiseSystemDetails() {

    }

    public void buildCruise() {

    }
}
