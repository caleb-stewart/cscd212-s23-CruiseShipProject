package CruisePort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class PortManager implements Cloneable{

    //We need to keep track of what locations have already been used
    //So we are going to apply the prototype pattern to the CruisePorts, mainly so we can keep track of
    //  what ports have been used or not for the destination
    private String countryPort;
    private String portLocationName;

    private File ports = new File("ports.txt");
    private Scanner scnr;
    private Scanner kb = new Scanner(System.in);
    private ArrayList<String> locationName = new ArrayList<>();
    ArrayList<String> countryPorts = new ArrayList<>();



    public PortManager() {
        fileReset();

        while(scnr.hasNext()) {
            String temp = scnr.nextLine();

            countryPorts.add(temp.substring(0, temp.indexOf(",")));

        }

        fileReset();
        while(scnr.hasNext()) {
            String temp = scnr.nextLine();
            locationName.add(temp);
        }
    }

    public void chooseCruise() {
        this.countryPort = displayStartingOptions();
        this.portLocationName = retrievePortLocations(countryPort);
    }

    public PortManager(final String countryPort, final String locationName) {

        if(countryPort == null || countryPort.isEmpty() || locationName == null || locationName.isEmpty()) {
            throw new IllegalArgumentException("Bad params in CruisePort constructor");
        }

        this.countryPort = countryPort;
        this.portLocationName = locationName;
    }

    public String getCountryPort() {return this.countryPort; }

    public String getLocationName() {return this.portLocationName; }

    public ArrayList<String> getLocationNameList() {return this.locationName; }

    public void setLocationName(final ArrayList<String> locationName) {
        this.locationName = locationName;
    }

    public void setCountryPort(final String countryName) {
        this.countryPort = countryName;
    }

    public void setLocationName(final String locationName) {
        this.portLocationName = locationName;
    }


    @Override
    public PortManager clone() throws CloneNotSupportedException { //Shallow copy because we want to destination port to be removed from all port locations
        PortManager clone = (PortManager) super.clone();
        // TODO: copy mutable state here, so the clone can't change the internals of the original
        return clone;
    }

    public void fileReset() {
        try {
            scnr = new Scanner(ports);
        }
        catch(FileNotFoundException FNFE) {
            FNFE.getStackTrace();
        }
    }


    public String displayStartingOptions() {

        System.out.print(countryPorts.get(0) + ", ");
        for(int i = 1; i < countryPorts.size(); ++i) {

            if(!(countryPorts.get(i - 1).equals(countryPorts.get(i))))
                System.out.print(countryPorts.get(i) + ", ");

        }

        System.out.println("\nSelect a Country You Would Like to Depart From: ");
        String choice = kb.nextLine();

        if(countryPorts.contains(choice.toUpperCase())) {
            System.out.println("Success");
            return choice.toUpperCase();

        }
        else {
            throw new IndexOutOfBoundsException(choice + " is not a valid option");
        }
    }

    public String retrievePortLocations(final String country) {

        if(country == null || country.isEmpty()) {
            throw new IllegalArgumentException("Bad params in CruisePortManager retrievePortLocations");
        }


        fileReset();
        int iter = 0;

        for(int i = 0; i < locationName.size(); ++i) {
            if(locationName.get(i).substring(0, locationName.get(i).indexOf(",")).equals(country)) {
                System.out.println(iter + 1 + ".) " + locationName.get(i).substring(locationName.get(0).indexOf(",") + 2));
                iter++;
            }
        }

        System.out.println("Select the port name in " + country);
        int locationChoice = Integer.parseInt(kb.nextLine()) - 1;

        if(locationChoice >= 0 && locationChoice <= iter) {
            String returnPortLocationName = "";

            for(int i = 0; i < locationName.size(); ++i) {
                if(locationName.get(i).substring(0, locationName.get(i).indexOf(",")).equals(country)) {
                    returnPortLocationName = locationName.get(i + locationChoice);
                    locationName.remove(i + locationChoice);
                }
            }
            return returnPortLocationName;

        }
        else {
            throw new IndexOutOfBoundsException(locationChoice + " was not an option");
        }
    }


    }

