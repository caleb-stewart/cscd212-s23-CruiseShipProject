## Design Pattern Implementations for Cruise Ship Manager

### Strategy Pattern
In the **CruisePackages** module, the Strategy pattern is employed to manage various package types, including **DinnerPackage**, **DrinksPackage**, and **WifiPackage**. Each of these package types adheres to a specific interface (e.g., `DinnerUpgrade` implements `DinnerPackage`), ensuring that any class implementing a given package interface is interchangeable with other classes of the same type. This approach facilitates dynamic adjustments to dinner, drinks, and Wi-Fi options for cruises and supports future extensions without necessitating modifications to the `CruisePackages` class. This implementation aligns with the **Open/Closed Principle**, enabling the system to accommodate new package types without altering existing code.

### Factory Pattern
The **Factory pattern** is utilized in the instantiation of **CruiseShip** objects through the **CruiseShipFactory**. This factory method enables the creation of **CruiseShip** instances based on user preferences, such as selecting between Family-Friendly or Adults-Only cruises. Each cruise type has distinct properties, and by employing an abstract **CruiseShip** class, the Factory pattern allows for the seamless addition of new cruise types in the future with minimal changes to the **CruiseShipFactory**. This design adheres to the **Open/Closed Principle** by facilitating the extension of functionality without extensive modifications.

### Builder Pattern
The **Builder pattern** is leveraged in the comprehensive assembly of the cruise experience. The **CruiseBuilder** class extends **AbstractCruiseBuilder**, coordinating the construction of **CruiseShip**, **CruisePort**, **CruisePackages**, and **CruiseRoom** objects. This pattern ensures a methodical and step-by-step creation process for the entire cruise. The use of **AbstractCruiseBuilder** supports future enhancements by allowing additional components to be incorporated into the builder without disrupting existing functionality, thereby adhering to the **Open/Closed Principle**.

### Observer Pattern
The **Observer pattern** is implemented with the **CruiseObserver** class acting as the observer and **CruiseManager** serving as the subject. Utilizing `PropertyChangeEvent`—rather than the deprecated `Observable` class—ensures that the observer is notified of changes in the `NumDaysSailing` property. This setup guarantees that updates are promptly communicated to the observer when state changes occur. The pattern conforms to the **Open/Closed Principle** by allowing the integration of additional observers without modifying the **CruiseManager** or **CruiseObserver** classes.

### Prototype Pattern
The **Prototype pattern** is instantiated through the `Cloneable` interface in **AbstractCruiseShipFactory** and **PortManager**. The **CruiseManager** employs the `clone` method to generate new instances of **CruiseShipFactory** and **CruisePort** based on existing objects. This approach ensures that modifications to the original `ArrayList` of ships and ports are accurately reflected in the newly created instances. The use of shallow copying maintains consistency and allows the **CruiseManager** to effectively manage and create new ships and ports.

### Facade Pattern
The **Facade pattern** is applied within the **CruiseManager** class, which functions as a facade to streamline interactions with the system's components. By interfacing through the **CruiseManager**, users can create cruises, ports, or ships without engaging with the underlying complexity of the other classes. This design provides a simplified and cohesive interface, encapsulating the complexities of the system and enhancing user experience.
