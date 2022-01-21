package entities;

import java.util.ArrayList;
import simulator.Processing;
import strategies.*;

/**
 * The type Distributors.
 */
public final class Distributors extends Common implements Observer {
    private int contractLength;
    private double infrastructureCost;
    private ArrayList<Contracts> contracts;
    private double currentPrice;
    private double profit;
    /**
     * added for phase 2
     */
    private double energyNeededKW;
    private String producerStrategy;
    private StrategyInterface strategy;
    private double productionCost;
    private ArrayList<Integer> chosenProducers;
    private boolean needsUpdate;

    /**
     * Instantiates a new Distributors.
     *
     * @param id                     the id
     * @param budget                 the budget
     * @param contractLength         the contract length
     * @param infrastructureCost     the infrastructure cost
     * @param energyNeededKW         the energy needed kw
     * @param producerStrategyString the producer strategy string
     */
    public Distributors(final int id,
                        final double budget,
                        final int contractLength,
                        final double infrastructureCost,
                        final double energyNeededKW,
                        final String producerStrategyString) {
        super(id, budget);
        this.profit = 0;
        this.contracts = new ArrayList<Contracts>();
        this.contractLength = contractLength;
        this.infrastructureCost = infrastructureCost;
        this.energyNeededKW = energyNeededKW;
        this.producerStrategy = producerStrategyString;
        this.strategy = Strategy.select(this);
        this.productionCost = 0;
    }

    /**
     * Gets contract length.
     *
     * @return the contract length
     */
    public int getContractLength() {
        return contractLength;
    }

    /**
     * Sets contract length.
     *
     * @param contractLength the contract length
     */
    public void setContractLength(final int contractLength) {
        this.contractLength = contractLength;
    }

    /**
     * Gets infrastructure cost.
     *
     * @return the infrastructure cost
     */
    public double getInfrastructureCost() {
        return infrastructureCost;
    }

    /**
     * Sets infrastructure cost.
     *
     * @param infrastructureCost the infrastructure cost
     */
    public void setInfrastructureCost(final double infrastructureCost) {
        this.infrastructureCost = infrastructureCost;
    }

    /**
     * Gets energy needed kw.
     *
     * @return the energy needed kw
     */
    public double getEnergyNeededKW() {
        return energyNeededKW;
    }

    /**
     * Sets energy needed kw.
     *
     * @param energyNeededKW the energy needed kw
     */
    public void setEnergyNeededKW(double energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    /**
     * Gets producer strategy.
     *
     * @return the producer strategy
     */
    public String getProducerStrategy() {
        return producerStrategy;
    }

    /**
     * Sets producer strategy.
     *
     * @param producerStrategy the producer strategy
     */
    public void setProducerStrategy(String producerStrategy) {
        this.producerStrategy = producerStrategy;
    }

    /**
     * Gets strategy.
     *
     * @return the strategy
     */
    public StrategyInterface getStrategy() {
        return strategy;
    }

    /**
     * Sets strategy.
     *
     * @param strategy the strategy
     */
    public void setStrategy(StrategyInterface strategy) {
        this.strategy = strategy;
    }

    /**
     * Gets production cost.
     *
     * @return the production cost
     */
    public ArrayList<Contracts> getContracts() {
        return contracts;
    }

    /**
     * Sets contracts.
     *
     * @param contracts the contracts
     */
    public void setContracts(final ArrayList<Contracts> contracts) {
        this.contracts = contracts;
    }

    /**
     * Gets current price.
     *
     * @return the current price
     */
    public double getCurrentPrice() {
        return currentPrice;
    }

    /**
     * Sets current price.
     *
     * @param currentPrice the current price
     */
    public void setCurrentPrice(final double currentPrice) {
        this.currentPrice = currentPrice;
    }

    /**
     * Gets profit.
     *
     * @return the profit
     */
    public double getProductionCost() {
        return productionCost;
    }

    /**
     * Sets production cost.
     *
     * @param productionCost the production cost
     */
    public void setProductionCost(double productionCost) {
        this.productionCost = Math.round(Math.floor(productionCost / 10));
    }

    /**
     * Gets chosen producers.
     *
     * @return the chosen producers
     */
    public ArrayList<Integer> getChosenProducers() {
        return chosenProducers;
    }

    /**
     * Sets chosen producers.
     *
     * @param chosenProducers the chosen producers
     */
    public void setChosenProducers(ArrayList<Integer> chosenProducers) {
        this.chosenProducers = chosenProducers;
    }

    /**
     * Gets profit.
     *
     * @return the profit
     */
    public double getProfit() {
        return profit;
    }

    /**
     * Sets profit.
     *
     * @param profit the profit
     */
    public void setProfit(final double profit) {
        this.profit = profit;
    }

    /**
     * Add contract.
     *
     * @param contract the contract
     */
    public void addContract(final Contracts contract) {
        this.contracts.add(contract);
    }

    /**
     * Remove contract.
     *
     * @param contract the contract
     */
    public void removeContract(final Contracts contract) {
        this.contracts.remove(contract);
    }

    /**
     * Remove contracts.
     *
     * @param distId  the bankrupt distributor's id
     * @param newTest the Current test filled data
     */
    public static void removeContracts(final int distId, final Processing newTest) {
        for (Consumers cons : newTest.getConsumers()) {
            if (cons.getContractedDistributor().getId() == distId) {
                cons.setContractedDistributor(null);
                cons.setContractedMonthsRemaining(0);
            }
        }
    }

    /**
     * Gets needs update.
     *
     * @return the needs update
     */
    public boolean getNeedsUpdate() {
        return needsUpdate;
    }

    /**
     * Sets needs update.
     *
     * @param needsUpdate the needs update
     */
    public void setNeedsUpdate(boolean needsUpdate) {
        this.needsUpdate = needsUpdate;
    }

    /**
     * Removes the distributor from the producers.
     *
     * @param prod the prod
     */
    public void removeProducers(ArrayList<Producers> prod) {
        if (chosenProducers != null) {
            for (int i = 0; i < chosenProducers.size(); i++) {
                prod.get(chosenProducers.get(i)).getContractedDistributors().remove(this);
            }
        }
    }

    /**
     * Adjust current price.
     * <p>
     * Formulas from https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/proiect/etapa1
     */
    public void adjustCurrentPrice() {
        setProfit(Math.round(Math.floor(0.2 * this.productionCost)));
        if (this.contracts.size() > 0) {
            setCurrentPrice(Math.round(Math.floor(this.infrastructureCost / this.contracts.size()) + this.productionCost + this.profit));
        } else {
            setCurrentPrice(this.infrastructureCost + this.productionCost + this.profit);
        }
    }

    /**
     * Adjust distributor.
     * <p>
     * This is what happens to a distributor after 1 month passes
     *
     * @param newTest the Current test filled data
     */
    public void adjustDistributor(final Processing newTest) {
        /**
         * in case the distributor is bankrupt we ignore him
         */
        if (this.isBankrupt()) {
            return;
        }
        int income = 0;
        ArrayList<Contracts> expired = new ArrayList<>();

        /**
         * Calculating this month's income
         */
        for (Consumers cons : newTest.getConsumers()) {
            if (!cons.isBankrupt() && cons.getRestanta() == 0 && this == cons.getContractedDistributor()) {
                income += cons.getPrice();
            }
        }
        /**
         * Adjusting and removing expired contracts from the list
         */
        for (Contracts ct : this.contracts) {
            ct.setRemainedContractMonths(ct.getRemainedContractMonths() - 1);
            if (ct.getRemainedContractMonths() < 0) {
                expired.add(ct);
            }
        }
        for (Contracts ct : expired) {
            this.contracts.remove(ct);
        }

        if (this.getBudget() + income - (this.infrastructureCost + this.productionCost * this.contracts.size()) < 0) {
            /**
             * the distributor became bankrupt so we remove him accordingly
             */
            this.setBankrupt(true);
            removeContracts(this.getId(), newTest);
            this.contracts.clear();
            this.setBudget(this.getBudget() - this.infrastructureCost);
        } else {
            /**
             * updating the budget
             */
            setBudget(this.getBudget() + income - (this.infrastructureCost + this.productionCost * this.contracts.size()));
        }
    }

    @Override
    public void update(ArrayList<Producers> prod) {
        this.getStrategy().producerSelection(this, prod);
        this.setNeedsUpdate(false);
    }
}
