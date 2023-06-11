
Caleb Stewarts Design Pattern Implementations for CSCD212-s23-finalProject

Strategy Pattern: I used the strategy pattern for the CruisePackages, which included a DinnerPackage, DrinksPackage, and a WifiPackage.
    Each CruisePackage contains an implementation of the Package interface, Ex. DinnerUpgrade implements DinnerPackage.
    Any class that implements the DinnerPackage to be interchangeable with any other class that implements the DinnerPackage.
    Any class that implements the DrinksPackage to be interchangeable with any other class that implements the DrinksPackage.
    Any class that implements the WifiPackage to be interchangeable with any other class that implements the WifiPackage.
    This allows the user to dynamically change the package of dinner, drinks, and wifi for each cruise.
    The user can also add more packages in the future without modifying the CruisePackages class. - Open/Closed


Factory Pattern: I used the factory pattern in creating the CruiseShip. The CruiseShipFactory creates a CruiseShip based on the type of CruiseShip the user wants.
    The program prompts the user to choose between a Family Friendly cruise or a Adults Only cruise.
    With the user input, the CruiseShipFactory creates the appropriate CruiseShip at runtime.
    A family friendly cruise and an adults only cruise have different properties.
    Because the CruiseShip class is abstract, you can create new CruiseTypes in the future without changing the CruiseShipFactory too heavily. - Open/Closed


Builder Pattern: I used the builder pattern in creating the entire Cruise. The builder pattern connects all of the other classes together, constructing
the entire cruise step by step.
    The builder connects the CruiseShip, CruisePort, CruisePackages, and CruiseRoom classes together, creating each object in the AbstractCruiseBuilder class.
    The CruiseBuilder class extends the AbstractCruiseBuilder class, and uses all of those objects to create one big Cruise class
    Because of the AbstractCruiseBuilder, in the future you can add more classes to the builder with more details and it will still work. - Open/Closed


Observer Pattern: I used the observer pattern in the CruiseObserver class and the CruiseManager class.
    The CruiseObserver is the observer, and the CruiseManager is the subject.
    I used the PropertyChangeEvent class instead of the deprecated Observable class.
    Whenever the state of NumDaysSailing is increased (which can be increased by the CruiseManager), the CruiseObserver is notified and updates the state of the CruiseManager.
    Follows Open/Closed because you can add more observers in the future without changing the CruiseManager or CruiseObserver class.


Prototype Pattern: Cloneable is implemented in AbstractCruiseShipFactory, and PortManager.
    CruiseManager creates a new CruiseShipFactory and CruisePort object using the dvc, which fills the ArrayList of CruiseShips and Ports.
    Whenever CruiseManager wants to create a new CruiseShip to CruisePort object, it uses the clone method of the original CruiseShipFactory and CruisePort object.
    The clone is a shallow copy, which is needed because whenever the CruiseShipFactory or CruisePort object is cloned the ArrayLists of the original
        of available CruiseShips and Ports are modified as well.
    This allows CruiseManager to keep track of ports and ships that have already been used, and to create new ones when needed.


Facade Pattern: The Facade pattern is used in the CruiseManager class. this class is the facade, and it hides the complexity of the other classes from the user.
    The user can use the CruiseManager to create a new cruise, or port, or ship, and the CruiseManager will call to other methods in the program.
    The user only needs to interact with the CruiseManager object to create a cruise and access all the other classes.
