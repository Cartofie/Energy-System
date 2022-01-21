package entities;

import simulator.Processing;

/**
 * The type Consumers.
 */
public final class Consumers extends Common {
    private double monthlyIncome;
    private double restanta;
    private Distributors contractedDistributor;
    private int contractedMonthsRemaining;
    private double price;
    private double penalty;

    /**
     * Instantiates a new Consumers.
     *
     * @param id            the id
     * @param budget        the initial budget
     * @param monthlyIncome the monthly income
     */
    public Consumers(final int id, final double budget, final double monthlyIncome) {
        super(id, budget);
        this.restanta = 0;
        this.contractedMonthsRemaining = 1;
        this.penalty = 1.2;
        this.price = 0;
        this.monthlyIncome = monthlyIncome;
    }

    /**
     * Gets monthly income.
     *
     * @return the monthly income
     */
    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    /**
     * Sets monthly income.
     *
     * @param monthlyIncome the monthly income
     */
    public void setMonthlyIncome(final double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    /**
     * Gets restanta.
     *
     * @return the restanta
     */
    public double getRestanta() {
        return restanta;
    }

    /**
     * Sets restanta.
     *
     * @param restanta the restanta
     */
    public void setRestanta(final double restanta) {
        this.restanta = restanta;
    }

    /**
     * Gets contracted distributor.
     *
     * @return the contracted distributor
     */
    public Distributors getContractedDistributor() {
        return contractedDistributor;
    }

    /**
     * Sets contracted distributor.
     *
     * @param contractedDistributor the contracted distributor
     */
    public void setContractedDistributor(final Distributors contractedDistributor) {
        this.contractedDistributor = contractedDistributor;
    }

    /**
     * Gets contracted months remaining.
     *
     * @return the contracted months remaining
     */
    public int getContractedMonthsRemaining() {
        return contractedMonthsRemaining;
    }

    /**
     * Sets contracted months remaining.
     *
     * @param contractedMonthsRemaining the contracted months remaining
     */
    public void setContractedMonthsRemaining(final int contractedMonthsRemaining) {
        this.contractedMonthsRemaining = contractedMonthsRemaining;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(final double price) {
        this.price = price;
    }

    /**
     * Look for contract.
     *
     * The consumer checks every contract from the available distributors list
     * stored in newTest and chooses the lowest price one
     *
     * @param newTest the Current test filled data
     */
    public void lookForContract(final Processing newTest) {
        double bestPrice = Double.POSITIVE_INFINITY;
        Distributors bestChoice = null;
        for (Distributors dist : newTest.getDistributors()) {
            if (dist.getCurrentPrice() < bestPrice && !dist.isBankrupt()) {
                bestPrice = dist.getCurrentPrice();
                bestChoice = dist;
            }
        }
        /**
         * sets up the contract
         */
        this.contractedDistributor = bestChoice;
        this.contractedMonthsRemaining = bestChoice.getContractLength();
        this.price = bestPrice;
        Contracts contract = new Contracts(this.getId(),
                this.price,
                this.contractedMonthsRemaining);
        bestChoice.addContract(contract);
    }

    /**
     * Adjust consumer.
     *
     * This is what happens to a consumer after 1 month passes
     *
     */
    public void adjustConsumer() {
        /**
         * in case the consumer is bankrupt we ignore him
         */
        if (this.isBankrupt()) {
            return;
        }
        this.setBudget(this.getBudget() + this.monthlyIncome);
        this.contractedMonthsRemaining--;
        if (this.getBudget() - (this.restanta + this.price) < 0) {
            if (this.restanta != 0) {
                /**
                 * meaning the consumer just became bankrupt
                 */
                Contracts toBeRemoved = new Contracts();
                setBankrupt(true);
                /**
                 * removes contract from distributor's side
                 */
                for (Contracts ct : this.contractedDistributor.getContracts()) {
                    if (ct.getConsumerId() == this.getId()) {
                        toBeRemoved = ct;
                    }
                }
                this.contractedDistributor.removeContract(toBeRemoved);
                this.contractedDistributor.setBudget(this.contractedDistributor.getBudget() - this.contractedDistributor.getProductionCost());
            } else {
                /**
                 * doesn't have money to pay this month's bill
                 */
                setRestanta(this.penalty * this.price);
            }
        } else {
            /**
             * pays this months bill and any other arrears
             */
            setBudget(this.getBudget() - (this.restanta + this.price));
            this.restanta = 0;
        }
    }
}
