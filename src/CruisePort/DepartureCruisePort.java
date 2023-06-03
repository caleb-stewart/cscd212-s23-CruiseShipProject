package CruisePort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DepartureCruisePort {

    private File ports = new File("ports.txt");
    private Scanner scnr;
    private Scanner kb = new Scanner(System.in);
    private ArrayList<String> locationName = new ArrayList<>();


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

        if(countryPorts.contains(choice)) {
            System.out.println("Success");
            retrievePortLocations(choice);
        }
    }

    public void retrievePortLocations(final String country) {
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
    }

}
