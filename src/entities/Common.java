package entities;

/**
 * The type Common.
 */
public abstract class Common {
    private final int id;
    private double budget;
    private boolean isBankrupt;

    /**
     * Instantiates a new Common.
     *
     * @param id            the id
     * @param initialBudget the initial budget
     */
    public Common(final int id, final double initialBudget) {
        this.id = id;
        this.isBankrupt = false;
        this.budget = initialBudget;
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
     * Gets budget.
     *
     * @return the budget
     */
    public double getBudget() {
        return budget;
    }

    /**
     * Sets budget.
     *
     * @param budget the budget
     */
    public void setBudget(final double budget) {
        this.budget = budget;
    }

    /**
     * Is bankrupt boolean.
     *
     * @return the boolean
     */
    public boolean isBankrupt() {
        return isBankrupt;
    }

    /**
     * Sets bankrupt.
     *
     * @param bankrupt the bankrupt
     */
    public void setBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }
}
