package entities;

import java.util.ArrayList;

/**
 * The interface Observer.
 */
public interface Observer {
    /**
     * Update.
     *
     * @param producers the ArrayList of all producers
     */
    void update(ArrayList<Producers> producers);
}
