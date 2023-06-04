package CruiseShip;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public abstract class AbstractCruiseShipFactory {

    private String parentCompany;
    private String shipName;

    File ships = new File("ships.txt");
    Scanner scnr;
    Scanner kb = new Scanner(System.in);
    ArrayList<String> shipCompanyNames = new ArrayList<>();
    ArrayList<String> companyShipNames = new ArrayList<>();

    public AbstractCruiseShipFactory(/*final String parentCompany, final String shipName*/) {
        presentShipCompanyName();
    }

    public abstract CruiseShip createShip(String shipType);

    private void resetFile() {
        try {
            scnr = new Scanner(ships);
        }
        catch(FileNotFoundException fnfe) {
            fnfe.getStackTrace();
        }
    }

    private void presentShipCompanyName() {

        resetFile();

        while(scnr.hasNext()) {
            String tempCursor = scnr.nextLine();
            shipCompanyNames.add(tempCursor.substring(0, tempCursor.indexOf(", ")));

        }

        System.out.print(shipCompanyNames.get(0) + ", ");
        for(int i = 1; i < shipCompanyNames.size(); ++i) {
            System.out.print(shipCompanyNames.get(i) + ", ");
        }
        System.out.println("Select cruise ship company name");
        String companyNameChoice = kb.nextLine();

        if(shipCompanyNames.contains(companyNameChoice)) {
            this.parentCompany = companyNameChoice;
            presentCompanyShipNames(companyNameChoice);
        }
    }

    private void presentCompanyShipNames(final String company) {

        resetFile();

        while(scnr.hasNext()) {
            String tempCursor = scnr.nextLine();
            if(tempCursor.contains(company)) {
                String[] temp = tempCursor.split(", ");
                for(int i = 1; i < temp.length; ++i) {
                    companyShipNames.add(temp[i]);
                }
                //companyShipNames.addAll(Arrays.asList(tempCursor.split(", ")));
            }
        }

        for(int i = 0; i < companyShipNames.size(); ++i) {
            System.out.println(i + 1 + ".) " + companyShipNames.get(i));
        }
        System.out.println("What ship would you like to ride?");
        int shipChoice = kb.nextInt();

        if(shipChoice > 0 && shipChoice < companyShipNames.size() + 1) {
            this.shipName = companyShipNames.get(shipChoice - 1);
        }
        else
            throw new NoSuchElementException(shipChoice + " was not found");

    }
}
