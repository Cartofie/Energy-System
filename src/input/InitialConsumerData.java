package input;

public final class InitialConsumerData {
    private int id;
    private double initialBudget;
    private double monthlyIncome;

    public InitialConsumerData() {
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public double getInitialBudget() {
        return initialBudget;
    }

    public void setInitialBudget(final double initialBudget) {
        this.initialBudget = initialBudget;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(final double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }
}
