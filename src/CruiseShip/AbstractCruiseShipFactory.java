package CruiseShip;

public abstract class AbstractCruiseShipFactory {

    private String parentCompany;
    private String shipName;

    public AbstractCruiseShipFactory(final String parentCompany, final String shipName) {
        this.parentCompany = parentCompany;
        this.shipName = shipName;
    }

    public abstract CruiseShip createShip(String shipType);
}
