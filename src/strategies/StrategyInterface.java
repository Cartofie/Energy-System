package strategies;

import entities.Distributors;
import entities.Producers;

import java.util.ArrayList;

/**
 * The interface Strategy interface.
 */
public interface StrategyInterface {
    /**
     * Producer selection.
     *
     * Each Strategy will implement the way distributors select their producers
     * based on their priorities
     *
     * @param dist the distributor
     * @param prod the Producers
     */
    void producerSelection(Distributors dist, ArrayList<Producers> prod);
}
