import static org.junit.jupiter.api.Assertions.*;

import CruisePort.*;
import CruiseRoom.*;
import CruiseShip.*;
import CruisePackage.DinnerPackage.*;
import CruisePackage.DrinksPackage.*;
import CruisePackage.WifiPackage.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Scanner;

public class tests {


    @Nested
    public class PortManagerTest {
        private PortManager portManager;

        @BeforeEach
        public void setUp() {
            // Initialize PortManager instance with required setup
            portManager = new CruisePort();
            ArrayList<String> locationName = new ArrayList<>();
            locationName.add("USA, New York");
            locationName.add("USA, Los Angeles");
            locationName.add("Canada, Vancouver");
            portManager.setLocationName(locationName);
        }

        @AfterEach
        public void afterEach() {
            this.portManager = null;
            System.setIn(System.in);
        }



        @Test
        public void testSetCountryPort() {
            // Set the country port using the method to be tested
            String countryPort = "USA";
            portManager.setCountryPort(countryPort);

            // Verify the expected results
            assertEquals(countryPort, portManager.getCountryPort(), "Country port mismatch");
        }

        @Test
        public void testSetLocationName() {
            // Set the port location using the method to be tested
            String locationName = "USA, New York";
            portManager.setLocationName(locationName);

            // Verify the expected results
            assertEquals(locationName, portManager.getLocationName(), "Port location mismatch");
        }

        // Write similar test methods for other methods in the PortManager class
        @Test
        public void testGetLocationNameList() {
            // Get the location name list using the method to be tested
            ArrayList<String> expectedLocationNameList = new ArrayList<>();
            expectedLocationNameList.add("USA, New York");
            expectedLocationNameList.add("USA, Los Angeles");
            expectedLocationNameList.add("Canada, Vancouver");

            // Verify the expected results
            assertEquals(expectedLocationNameList, portManager.getLocationNameList(), "Location name list mismatch");
        }

        @Test
        public void testClone1() throws CloneNotSupportedException {
            // Clone the PortManager instance using the method to be tested
            PortManager clonedPortManager = portManager.clone();

            assertNotSame(portManager, clonedPortManager, "Cloned instance is the same as original instance");

        }

        @Test
        public void testClone2() throws CloneNotSupportedException {
            PortManager clonedPortManager = portManager.clone();

            assertEquals(portManager.getCountryPort(), clonedPortManager.getCountryPort(), "Country port mismatch");
        }

        @Test
        public void testClone3() throws CloneNotSupportedException {
            PortManager clonedPortManager = portManager.clone();

            assertEquals(portManager.getLocationName(), clonedPortManager.getLocationName(), "Location name mismatch");
        }

        @Test
        public void testPortManagerNullParameter() {
            assertThrows(IllegalArgumentException.class, ()-> new CruisePort(null, null));
        }

        @Test
        public void testPortManagerEmptyParameter() {
            assertThrows(IllegalArgumentException.class, ()-> new CruisePort("", ""));
        }

    }

    @Nested
    public class CruiseShipFactoryTest {

        AbstractCruiseShipFactory factory;
        @BeforeEach
        public void setUp() {
            this.factory = new CruiseShipFactory("Company 1", "Cool Cruise");

        }

        @Test
        public void testCreateShipFamilyFriendly() {

            CruiseShip type = this.factory.createShip("Family Friendly");

            assertTrue(type instanceof FamilyFriendlyCruiseShip);
        }

        @Test
        public void testCreateShipAdultsOnly() {

            CruiseShip type = this.factory.createShip("Adults Only");

            assertTrue(type instanceof AdultsOnlyCruiseShip);
        }

        @Test
        public void testCreateShipEmptyString() {
            assertThrows(IllegalArgumentException.class, ()-> this.factory.createShip(""));
        }

        @Test
        public void testDisplayCruiseTypeFamilyFriendly() {
            CruiseShip type = new FamilyFriendlyCruiseShip("Company 1", "Cool Cruise");

            String displayCruiseShip = "";
            displayCruiseShip += "We are on a Family Friendly cruise\n";
            displayCruiseShip += "\t\tWe are drinking slushies\n";
            displayCruiseShip += "\t\tOur entertainment is Balloon animals and face painting\n";
            displayCruiseShip += "\t\tOur lights out time is 10pm";
            assertEquals(displayCruiseShip, type.displayCruiseType(), "Display cruise type mismatch");
        }

        @Test
        public void testDisplayCruiseTypeAdultsOnly() {
            CruiseShip type = new AdultsOnlyCruiseShip("Company 1", "Cool Cruise");

            String displayCruiseShip = "";
            displayCruiseShip += "We are on a Adults Only cruise\n";
            displayCruiseShip += "\t\tWe are drinking cocktails\n";
            displayCruiseShip += "\t\tOur entertainment is deck party\n";
            displayCruiseShip += "\t\tOur lights out time is 3am";
            assertEquals(displayCruiseShip, type.displayCruiseType(), "Display cruise type mismatch");
        }
    }

    @Nested
    public class CruiseRoomTest {

        CruiseRoom room;

        @Test
        public void testGenerateRoomNumber() {

            room = new InsideCabinRoom();
            assertTrue(room.generateRoomNumber() >= 0 && room.generateRoomNumber() <= 100);
        }

        @Test
        public void testInsideCabinRoomRoomNumber() {
            room = new InsideCabinRoom();
            assertEquals("A", room.getRoom().substring(0, room.getRoom().indexOf(", ")));
        }

        @Test
        public void testCabinWithPortRoomNumber() {
            room = new CabinWithPort();
            assertEquals("B", room.getRoom().substring(0, room.getRoom().indexOf(", ")));
        }

        @Test
        public void testCabinWithBalconyRoomNumber() {
            room = new CabinWithBalcony();
            assertEquals("C", room.getRoom().substring(0, room.getRoom().indexOf(", ")));
        }

        @Test
        public void testCabinSuiteRoomNumber() {
            room = new CabinSuite();
            assertEquals("D", room.getRoom().substring(0, room.getRoom().indexOf(", ")));
        }
    }

    @Nested
    public class testCruisePackages {

        DinnerPackage dinner;
        DrinksPackage drink;
        WifiPackage wifi;

        @Test
        public void testFreeDinnerPackage() {
            dinner = new FreeDinner();
            assertEquals("ez-Mac and Cheese", dinner.typeOfDinner());
        }

        @Test
        public void testDinnerUpgrade() {
            dinner = new DinnerUpgrade();
            assertEquals("Steak and Potatoes", dinner.typeOfDinner());
        }

        @Test
        public void testDinnerWithCaptain() {
            dinner = new DinnerWithCaptain();
            assertEquals("Steak and Lobster with Captain Caleb Stewart", dinner.typeOfDinner());
        }

        @Test
        public void testNoDrinksPackage() {
            drink = new NoDrinksPackage();
            assertEquals("Water", drink.drinkBeverage());
        }

        @Test
        public void testUnlimitedAlcohol() {
            drink = new UnlimitedAlcohol();
            assertEquals("Margarita", drink.drinkBeverage());
        }

        @Test
        public void testNoWifi() {
            wifi = new NoWifi();
            assertEquals("No Wifi", wifi.wifiStrength());
        }

        @Test
        public void testUnlimitedWifi() {
            wifi = new UnlimitedWifi();
            assertEquals("Strong Wifi", wifi.wifiStrength());
        }
    }
}
