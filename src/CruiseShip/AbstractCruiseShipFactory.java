package CruiseShip;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public abstract class AbstractCruiseShipFactory implements Cloneable{

    protected String parentCompany;
    protected String shipName;

    private File ships = new File("ships.txt");
    private Scanner scnr;
    protected Scanner kb = new Scanner(System.in);
    /*private ArrayList<String> shipCompanyNames = new ArrayList<>();*/
    private ArrayList<String> companyShipNames = new ArrayList<>();
    private ArrayList<String> namesOfShips = new ArrayList<>();

    public AbstractCruiseShipFactory() {
        /*fillArrayLists();*/
        fillNamesOfShips();
    }

    public AbstractCruiseShipFactory(final String parentCompany, final String shipName) {
        this.parentCompany = parentCompany;
        this.shipName = shipName;
    }

    public void chooseCruise() {
        this.parentCompany = availableShipCompanyNames();
        this.shipName = availableShipNames(this.parentCompany);
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
        String companyName = this.namesOfShips.get(0).substring(0, this.namesOfShips.get(0).indexOf(","));
        System.out.print(companyName + ", ");

        for(int i = 0; i < this.namesOfShips.size(); ++i) {
            String companyName2 = this.namesOfShips.get(i).substring(0, this.namesOfShips.get(i).indexOf(","));
            companyShipNames.add(companyName2);
            if(!(companyName.equals(companyName2) || companyName2.equals(this.namesOfShips.get(i - 1).substring(0, this.namesOfShips.get(i - 1).indexOf(","))))) {
                System.out.print(this.namesOfShips.get(i).substring(0, this.namesOfShips.get(i).indexOf(",")) + ", ");

            }

        }
        System.out.println("Select cruise ship company name");
        String companyNameChoice = kb.nextLine();

        if(this.companyShipNames.contains(companyNameChoice)) {
            return companyNameChoice;

        }
        else {
            throw new NoSuchElementException(companyNameChoice + " was not found");
        }
    }

    public void fillNamesOfShips() {

        resetFile();

        while(scnr.hasNext()) {
            String tempCursor = scnr.nextLine();

            String shipCompany = tempCursor.substring(0, tempCursor.indexOf(", "));
            String[] temp = tempCursor.split(", ");
            for(int i = 1; i < temp.length; ++i) {
                namesOfShips.add(shipCompany + ", " + temp[i]);
            }
        }

    }


    private String availableShipNames(final String company) {

        /*resetFile();

        while(scnr.hasNext()) {
            String tempCursor = scnr.nextLine();
            if(tempCursor.contains(company)) {
                String[] temp = tempCursor.split(", ");

                companyShipNames.addAll(Arrays.asList(temp).subList(1, temp.length));

            }
        }*/

        int iter = 1;
        for(int i = 0; i < namesOfShips.size(); ++i) {
            if(namesOfShips.get(i).contains(company)) {
                System.out.println(iter + ".) " + namesOfShips.get(i));
                iter++;
            }
        }

        System.out.println("What ship would you like to ride?");
        int shipChoice = Integer.parseInt(kb.nextLine());

        String returnShipName = "";
        if(shipChoice > 0 && shipChoice <= iter - 1) {
            for(int i = 0; i < namesOfShips.size(); ++i) {
                if(namesOfShips.get(i).contains(company)) {
                    returnShipName = namesOfShips.get(i + shipChoice - 1);
                    namesOfShips.remove(i + shipChoice - 1);
                    companyShipNames.remove(i + shipChoice - 1);
                    return returnShipName;
                }
            }
            return returnShipName;
        }
        else
            throw new NoSuchElementException(shipChoice + " was not found");

    }

    /*public void fillArrayLists() {
        resetFile();

        while(scnr.hasNext()) {
            String tempCursor = scnr.nextLine();
            shipCompanyNames.add(tempCursor.substring(0, tempCursor.indexOf(", ")));

        }


    }*/

    @Override
    public AbstractCruiseShipFactory clone() {
        try {
            return (AbstractCruiseShipFactory) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public void setCompanyShipNames(ArrayList<String> companyShipNames) {
        this.companyShipNames = companyShipNames;
    }

    public ArrayList<String> getCompanyShipNames() {
        return companyShipNames;
    }

}
