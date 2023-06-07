package Cruise;

import CruisePackage.DinnerPackage.DinnerPackage;
import CruisePackage.DrinksPackage.DrinksPackage;
import CruisePackage.WifiPackage.WifiPackage;
import CruisePort.PortManager;
import CruiseRoom.CabinWithBalcony;
import CruiseShip.CruiseShip;
import CruisePackage.DinnerPackage.*;
import CruisePackage.DrinksPackage.*;
import CruisePackage.WifiPackage.*;
import CruiseRoom.*;

import java.util.Scanner;

public class CruiseBuilder extends AbstractCruiseBuilder {

    Scanner kb = new Scanner(System.in);

    public CruiseBuilder(final PortManager startingPort, final PortManager endingPort, final CruiseShip ship) {
        super(startingPort, endingPort, ship);
    }

    @Override
    public void setDinnerPackageAccommodation() {

        System.out.println("Select what dinner package you would like to add to your cruise: ");
        System.out.println("1. Free Dinner");
        System.out.println("2. Dinner Upgrade");
        System.out.println("3. Dinner With Captain");

        int dinnerChoice = Integer.parseInt(kb.nextLine());

        switch (dinnerChoice) {
            case 1 -> this.dinnerPackage = new FreeDinner();
            case 2 -> this.dinnerPackage = new DinnerUpgrade();
            case 3 -> this.dinnerPackage = new DinnerWithCaptain();
            default -> throw new IndexOutOfBoundsException(dinnerChoice + " was not in the range of options");
        }
    }

    @Override
    public void setDinnerPackageAccommodation(final DinnerPackage dinnerPackage) {
        this.dinnerPackage = dinnerPackage;
    }

    @Override
    public void setDrinksPackageAccommodation() {

        System.out.println("Select what drinks package you would like to add to your cruise: ");
        System.out.println("1. No Drinks Package");
        System.out.println("2. Unlimited Drinks");

        int drinksChoice = Integer.parseInt(kb.nextLine());

        switch (drinksChoice) {
            case 1 -> this.drinksPackage = new NoDrinksPackage();
            case 2 -> this.drinksPackage = new UnlimitedAlcohol();
            default -> throw new IndexOutOfBoundsException(drinksChoice + " was not in the range of options");
        }
    }

    @Override
    public void setDrinksPackageAccommodation(final DrinksPackage drinksPackage) {
        this.drinksPackage = drinksPackage;
    }

    @Override
    public void setWifiPackageAccommodation() {

        System.out.println("Select what wifi package you would like to add to your cruise: ");
        System.out.println("1. No Wifi");
        System.out.println("2. Unlimited Wifi");

        int wifiChoice = Integer.parseInt(kb.nextLine());

        switch (wifiChoice) {
            case 1 -> this.wifiPackage = new NoWifi();
            case 2 -> this.wifiPackage = new UnlimitedWifi();
            default -> throw new IndexOutOfBoundsException(wifiChoice + " was not in the range of options");
        }
    }

    @Override
    public void setWifiPackageAccommodation(final WifiPackage wifiPackage) {
        this.wifiPackage = wifiPackage;
    }

    public void setRoom() {

        System.out.println("Select what room you would like to add to your cruise: ");
        System.out.println("1. Inside Cabin");
        System.out.println("2. Cabin with Port");
        System.out.println("3. Cabin with Balcony");
        System.out.println("4. Suite Cabin");

        int roomChoice = Integer.parseInt(kb.nextLine());

        switch (roomChoice) {
            case 1 -> this.room = new InsideCabinRoom();
            case 2 -> this.room = new CabinWithPort();
            case 3 -> this.room = new CabinWithBalcony();
            case 4 -> this.room = new CabinSuite();
            default -> throw new IndexOutOfBoundsException(roomChoice + " was not in the range of options");
        }
    }

    public void setRoom(final CruiseRoom room) {
        this.room = room;
    }
}
