package entities;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * The type Producers.
 */
public final class Producers {
    private int id;
    private int maxDistributors;
    private double priceKW;
    private EnergyType strategy;
    private double energyPerDistributor;
    private ArrayList<ProducerMonthlyStats> monthlyStats;
    private ArrayList<Distributors> contractedDistributors;

    /**
     * Instantiates a new Producers.
     *
     * @param id                   the id
     * @param maxDistributors      the max distributors
     * @param priceKW              the price kw
     * @param strategy             the strategy
     * @param energyPerDistributor the energy per distributor
     */
    public Producers(int id,
                     int maxDistributors,
                     double priceKW,
                     EnergyType strategy,
                     double energyPerDistributor) {
        this.id = id;
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.strategy = strategy;
        this.energyPerDistributor = energyPerDistributor;
        this.monthlyStats = new ArrayList();
        this.contractedDistributors = new ArrayList();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets max distributors.
     *
     * @return the max distributors
     */
    public int getMaxDistributors() {
        return maxDistributors;
    }

    /**
     * Sets max distributors.
     *
     * @param maxDistributors the max distributors
     */
    public void setMaxDistributors(int maxDistributors) {
        this.maxDistributors = maxDistributors;
    }

    /**
     * Gets price kw.
     *
     * @return the price kw
     */
    public double getPriceKW() {
        return priceKW;
    }

    /**
     * Sets price kw.
     *
     * @param priceKW the price kw
     */
    public void setPriceKW(double priceKW) {
        this.priceKW = priceKW;
    }

    /**
     * Gets strategy.
     *
     * @return the strategy
     */
    public EnergyType getStrategy() {
        return strategy;
    }

    /**
     * Sets strategy.
     *
     * @param strategy the strategy
     */
    public void setStrategy(EnergyType strategy) {
        this.strategy = strategy;
    }

    /**
     * Gets energy per distributor.
     *
     * @return the energy per distributor
     */
    public double getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    /**
     * Sets energy per distributor.
     *
     * @param energyPerDistributor the energy per distributor
     */
    public void setEnergyPerDistributor(double energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    /**
     * Gets monthly stats.
     *
     * @return the monthly stats
     */
    public ArrayList<ProducerMonthlyStats> getMonthlyStats() {
        return monthlyStats;
    }

    /**
     * Sets monthly stats.
     *
     * @param monthlyStats the monthly stats
     */
    public void setMonthlyStats(ArrayList<ProducerMonthlyStats> monthlyStats) {
        this.monthlyStats = monthlyStats;
    }

    /**
     * Gets contracted distributors.
     *
     * @return the contracted distributors
     */
    public ArrayList<Distributors> getContractedDistributors() {
        return contractedDistributors;
    }

    /**
     * Sets contracted distributors.
     *
     * @param contractedDistributors the contracted distributors
     */
    public void setContractedDistributors(ArrayList<Distributors> contractedDistributors) {
        this.contractedDistributors = contractedDistributors;
    }

    /**
     * Every Month's Report
     *
     * @param month the current month
     */
    public void adjustProducer(int month) {
        ArrayList<Integer> distributorsId = new ArrayList<>();
        for (Distributors dist : contractedDistributors) {
            distributorsId.add(dist.getId());
        }
        monthlyStats.add(new ProducerMonthlyStats(month, distributorsId));
    }

    private static class SortById implements Comparator<Producers> {

        @Override
        public int compare(Producers o1, Producers o2) {
            return o1.getId() - o2.getId();
        }
    }

    /**
     * Sorts by Id in ascending order
     *
     * @param producers the producers
     */
    public static void sort(ArrayList<Producers> producers) {
        producers.sort(new SortById());
    }

    /**
     * Notifies the affected distributors that the price has been changed
     */
    public void updateDistributors() {
        for (Distributors dist : this.getContractedDistributors()) {
            dist.setNeedsUpdate(true);
        }
    }
}
