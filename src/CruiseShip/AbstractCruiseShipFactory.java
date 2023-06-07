package CruiseShip;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public abstract class AbstractCruiseShipFactory {

    private final String parentCompany;
    private final String shipName;

    private File ships = new File("ships.txt");
    private Scanner scnr;
    private Scanner kb = new Scanner(System.in);
    private ArrayList<String> shipCompanyNames = new ArrayList<>();
    private ArrayList<String> companyShipNames = new ArrayList<>();

    public AbstractCruiseShipFactory() {

        fillArrayLists();

        this.parentCompany = availableShipCompanyNames();
        this.shipName = availableShipNames(this.parentCompany);
    }

    public AbstractCruiseShipFactory(final String parentCompany, final String shipName) {
        this.parentCompany = parentCompany;
        this.shipName = shipName;
    }

    public abstract CruiseShip createShip(String shipType);
    public abstract CruiseShip chooseTypeOfShip();

    private void resetFile() {
        try {
            scnr = new Scanner(ships);
        }
        catch(FileNotFoundException fnfe) {
            fnfe.getStackTrace();
        }
    }

    private String availableShipCompanyNames() {

        System.out.print(this.shipCompanyNames.get(0) + ", ");
        for(int i = 1; i < this.shipCompanyNames.size(); ++i) {
            System.out.print(this.shipCompanyNames.get(i) + ", ");
        }
        System.out.println("Select cruise ship company name");
        String companyNameChoice = kb.nextLine();

        if(this.shipCompanyNames.contains(companyNameChoice)) {
            return companyNameChoice;

        }
        else {
            throw new NoSuchElementException(companyNameChoice + " was not found");
        }
    }

    private String availableShipNames(final String company) {

        resetFile();

        while(scnr.hasNext()) {
            String tempCursor = scnr.nextLine();
            if(tempCursor.contains(company)) {
                String[] temp = tempCursor.split(", ");

                companyShipNames.addAll(Arrays.asList(temp).subList(1, temp.length));

            }
        }

        for(int i = 0; i < companyShipNames.size(); ++i) {
            System.out.println(i + 1 + ".) " + companyShipNames.get(i));
        }
        System.out.println("What ship would you like to ride?");
        int shipChoice = Integer.parseInt(kb.nextLine());

        if(shipChoice > 0 && shipChoice < companyShipNames.size() + 1) {
            return companyShipNames.get(shipChoice - 1);
        }
        else
            throw new NoSuchElementException(shipChoice + " was not found");

    }

    public void fillArrayLists() {
        resetFile();

        while(scnr.hasNext()) {
            String tempCursor = scnr.nextLine();
            shipCompanyNames.add(tempCursor.substring(0, tempCursor.indexOf(", ")));

        }


    }
}
