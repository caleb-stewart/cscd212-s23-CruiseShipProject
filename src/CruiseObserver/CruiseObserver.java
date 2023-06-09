package CruiseObserver;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import CruiseManager.CruiseManager;

public class CruiseObserver implements PropertyChangeListener {

    private String name;

    public CruiseObserver() {
        this.name = "Observer";
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        System.out.println("CruiseObserver - Changed property: " + evt.getPropertyName() + " [old -> "
                + evt.getOldValue() + "] | [new -> " + evt.getNewValue() +"]");
    }

    public void add(final CruiseManager manager) {
        manager.addPropertyChangeListener(this);
        System.out.println(this.name + " was added as an observer of " + manager.getClass().getSimpleName());
    }

    public void remove(final CruiseManager manager) {
        manager.removePropertyChangeListener(this);
        System.out.println(this.name + " was removed as an observer of " + manager.getClass().getSimpleName() + "\n");
    }
}
