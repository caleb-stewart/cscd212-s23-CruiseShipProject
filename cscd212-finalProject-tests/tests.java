import static org.junit.jupiter.api.Assertions.*;

import Cruise.*;
import CruisePort.*;
import CruiseRoom.*;
import CruiseShip.*;
import CruiseManager.*;
import CruisePackage.DinnerPackage.*;
import CruisePackage.DrinksPackage.*;
import CruisePackage.WifiPackage.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

public class tests {


    @Nested
    public class PortManagerTest {
        private PortManager portManager;

        @BeforeEach
        public void setUp() {
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
            portManager.setCountryPort("USA");

            assertEquals("USA", portManager.getCountryPort(), "Country port mismatch");
        }

        @Test
        public void testSetLocationName() {
            String locationName = "USA, New York";
            portManager.setLocationName(locationName);

            assertEquals(locationName, portManager.getLocationName(), "Port location mismatch");
        }

        @Test
        public void testGetLocationNameList() {
            ArrayList<String> expectedLocationNameList = new ArrayList<>();
            expectedLocationNameList.add("USA, New York");
            expectedLocationNameList.add("USA, Los Angeles");
            expectedLocationNameList.add("Canada, Vancouver");

            assertEquals(expectedLocationNameList, portManager.getLocationNameList(), "Location name list mismatch");
        }

        @Test
        public void testClone1() throws CloneNotSupportedException {
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

    @Nested
    public class testCruiseBuilder {

        AbstractCruiseBuilder builder;
        @BeforeEach
        public void setUp() {
            PortManager startingPort = new CruisePort("Starting Country", "Starting Location");
            PortManager endingPort = new CruisePort("Ending Country", "Ending Location");

            CruiseShip ship = new FamilyFriendlyCruiseShip("Company 1", "Cool Cruise");
            builder = new CruiseBuilder(startingPort, endingPort, ship);
        }

        @Test
        public void testCruiseBuilderDinnerPackage() {
            assertTrue(builder.dinnerPackage instanceof FreeDinner);
        }

        @Test
        public void testCruiseBuilderDrinksPackage() {
            assertTrue(builder.drinksPackage instanceof NoDrinksPackage);
        }

        @Test
        public void testCruiseBuilderWifiPackage() {
            assertTrue(builder.wifiPackage instanceof NoWifi);
        }

        @Test
        public void testCruiseBuilderRoom() {
            assertTrue(builder.room instanceof InsideCabinRoom);
        }

        @Test
        public void testCruiseBuilderCalculateNumDaysSailing() {
            assertEquals(10, builder.numDaysSailing);
        }

        @Test
        public void testCruiseBuilderAddPortGetSize() {
            PortManager port = new CruisePort("Country", "Location");
            builder.addPort(port);
            assertEquals(3, builder.getPorts().size());
        }

        @Test
        public void testCruiseBuilderAddPortLocationInArray() {
            PortManager port = new CruisePort("Country", "Location");
            builder.addPort(port);
            assertEquals("Location", builder.getPorts().get(1).getLocationName());
        }
    }

    @Nested
    public class testCruiseManager {

        CruiseManager manager;

        @BeforeEach
        public void setUp() {
            manager = new CruiseManager();
        }

        @Test
        public void testCreateCruisePort() {
            PortManager port = manager.createCruisePort("Country", "Location");

            assertEquals("Location", port.getLocationName());
        }

        @Test
        public void testCreateCruisePortExceptionThrow() {
            assertThrows(IllegalArgumentException.class, ()-> manager.createCruisePort("", ""));
        }

        @Test
        public void testCreateCruiseShipFamilyFriendly() {
            CruiseShip ship = manager.createCruiseShip("Company 1", "Cool Cruise", "Family Friendly");

            assertTrue(ship instanceof FamilyFriendlyCruiseShip);
        }

        @Test
        public void testCreateCruiseShip() {
            CruiseShip ship = manager.createCruiseShip("Company 1", "Cool Cruise", "Family Friendly");

            assertEquals("Company 1", ship.getShipCompany());
        }

        @Test
        public void testCreateCruiseShipExceptionThrow() {
            assertThrows(IllegalArgumentException.class, ()-> manager.createCruiseShip("", "", ""));
        }

        @Test
        public void testCruiseManagerBuildCruise() {
            AbstractCruiseBuilder cruise = manager.buildCruise("Starting Country", "Starting Location", "Ending Country", "Ending Location", "Company 1", "Cool Cruise", "Family Friendly",
                    new FreeDinner(), new NoDrinksPackage(), new NoWifi(), new InsideCabinRoom());

            assertTrue(cruise instanceof CruiseBuilder);
        }

        @Test
        public void testCruiseManagerBuildCruiseExceptionThrow() {
            assertThrows(IllegalArgumentException.class, ()-> manager.buildCruise("", "", "", "", "", "", "", null, null, null, null));
        }

        @Test
        public void testCruiseManagerIncreaseNightsCruising() {
            AbstractCruiseBuilder cruise = manager.buildCruise("Starting Country", "Starting Location", "Ending Country", "Ending Location", "Company 1", "Cool Cruise", "Family Friendly",
                    new FreeDinner(), new NoDrinksPackage(), new NoWifi(), new InsideCabinRoom());

            manager.increaseNightsCruising();

            assertEquals(12, cruise.numDaysSailing);
        }

        @Test
        public void testCruiseManagerAddPortToCruise() {
            AbstractCruiseBuilder cruise = manager.buildCruise("Starting Country", "Starting Location", "Ending Country", "Ending Location", "Company 1", "Cool Cruise", "Family Friendly",
                    new FreeDinner(), new NoDrinksPackage(), new NoWifi(), new InsideCabinRoom());

            manager.addPortToCruise("New Country", "New Location", 0);

            assertEquals(3, cruise.getPorts().size());
        }

        @Test
        public void testCruiseManagerAddPortToCruiseException() {
            AbstractCruiseBuilder cruise = manager.buildCruise("Starting Country", "Starting Location", "Ending Country", "Ending Location", "Company 1", "Cool Cruise", "Family Friendly",
                    new FreeDinner(), new NoDrinksPackage(), new NoWifi(), new InsideCabinRoom());

            assertThrows(IllegalArgumentException.class, ()-> manager.addPortToCruise("", "", 0));
        }

    }
}
