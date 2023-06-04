package CruisePort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DepartureCruisePort implements Cloneable{

    //We need to keep track of what locations have already been used
    //So we are going to apply the prototype pattern to the CruisePorts, mainly so we can keep track of
    //  what ports have been used or not for the destination

    private String countryPort;
    private String portLocationName;

    private File ports = new File("ports.txt");
    private Scanner scnr;
    private Scanner kb = new Scanner(System.in);
    private ArrayList<String> locationName = new ArrayList<>();


    @Override
    public DepartureCruisePort clone() throws CloneNotSupportedException { //Shallow copy because we want to destination port to be removed from all port locations
        DepartureCruisePort clone = (DepartureCruisePort) super.clone();
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


    public void displayStartingOptions() {
        ArrayList<String> countryPorts = new ArrayList<>();

        fileReset();

        while(scnr.hasNext()) {
            String temp = scnr.nextLine();

            countryPorts.add(temp.substring(0, temp.indexOf(",")));

           }

        System.out.print(countryPorts.get(0) + ", ");
        for(int i = 1; i < countryPorts.size(); ++i) {

            if(!(countryPorts.get(i - 1).equals(countryPorts.get(i))))
                System.out.print(countryPorts.get(i) + ", ");

        }

        System.out.println("\nSelect a Country You Would Like to Depart From: ");
        String choice = kb.nextLine();

        if(countryPorts.contains(choice.toUpperCase())) {
            System.out.println("Success");
            this.countryPort = choice.toUpperCase();
            retrievePortLocations(choice.toUpperCase());
        }
        else {
            throw new IndexOutOfBoundsException(choice + " is not a valid option");
        }
    }

    private void retrievePortLocations(final String country) {
        fileReset();
        int iter = 1;

        while(scnr.hasNext()) {

            String tempCursor = scnr.nextLine();

            if(tempCursor.contains(country)) {
                locationName.add(tempCursor.substring(tempCursor.indexOf(",") + 2));
                System.out.println(iter + ".) " + tempCursor.substring(tempCursor.indexOf(",") + 2));
                iter++;
            }
        }

        System.out.println("Select the port name in " + countryPort);
        int locationChoice = kb.nextInt();

        if(locationChoice > 0 && locationChoice <= iter) {
            portLocationName = locationName.get(locationChoice - 1);
            
        }
        else {
            throw new IndexOutOfBoundsException(locationChoice + " was not an option");
        }
    }


    }

