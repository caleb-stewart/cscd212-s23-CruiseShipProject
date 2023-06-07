package Cruise;

import CruisePackage.DinnerPackage.*;
import CruisePackage.DrinksPackage.*;
import CruisePackage.WifiPackage.*;
import CruisePort.PortManager;
import CruiseShip.CruiseShip;
import CruiseRoom.*;

import java.util.ArrayList;
import java.util.Date;

public abstract class AbstractCruiseBuilder {

        protected ArrayList<PortManager> ports;

        public CruiseShip ship;
        public DinnerPackage dinnerPackage;
        public DrinksPackage drinksPackage;
        public WifiPackage wifiPackage;
        public CruiseRoom room;
        public Date date;

        public int numDaysSailing;
        public int numDaysInDestinationPort;



        public AbstractCruiseBuilder(final PortManager startingPort, final PortManager endingPort, final CruiseShip ship) {
            this.ports = new ArrayList<PortManager>();
            this.ports.add(startingPort);
            this.ports.add(endingPort);

            this.ship = ship;

            this.dinnerPackage = new FreeDinner();
            this.drinksPackage = new NoDrinksPackage();
            this.wifiPackage = new NoWifi();

            this.room = new InsideCabinRoom();

            this.date = new Date();
            this.numDaysSailing = calculateNumDaysSailing();
            this.numDaysInDestinationPort = this.ports.size();

        }

        protected int calculateNumDaysSailing() {
            return this.ports.size() * 5;
        }

        public void addPort(final PortManager port) {
            this.ports.add(port);
        }

        public abstract void setDinnerPackageAccommodation();
        public abstract void setDinnerPackageAccommodation(final DinnerPackage dinnerPackage);

        public abstract void setDrinksPackageAccommodation();
        public abstract void setDrinksPackageAccommodation(final DrinksPackage drinksPackage);

        public abstract void setWifiPackageAccommodation();
        public abstract void setWifiPackageAccommodation(final WifiPackage wifiPackage);

        public abstract void setRoom();
        public abstract void setRoom(final CruiseRoom room);


        public ArrayList<PortManager> getPorts() {
            return ports;
        }

        public CruiseShip getShip() {
            return ship;
        }

        public DinnerPackage getDinnerPackage() {
            return dinnerPackage;
        }

        public DrinksPackage getDrinksPackage() {
            return drinksPackage;
        }

        public WifiPackage getWifiPackage() {
            return wifiPackage;
        }

        public CruiseRoom getRoom() {
            return room;
        }

        public Date getDate() {
            return date;
        }

        public int getNumDaysSailing() {
            return numDaysSailing;
        }

        public int getNumDaysInDestinationPort() {
            return numDaysInDestinationPort;
        }
}
