package input;

public final class InitialDistributorData {
    private int id;
    private int contractLength;
    private double initialBudget;
    private double initialInfrastructureCost;
    private double energyNeededKW;
    private String producerStrategy;

    public InitialDistributorData() {
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getContractLength() {
        return contractLength;
    }

    public void setContractLength(final int contractLength) {
        this.contractLength = contractLength;
    }

    public double getInitialBudget() {
        return initialBudget;
    }

    public void setInitialBudget(final double initialBudget) {
        this.initialBudget = initialBudget;
    }

    public double getInitialInfrastructureCost() {
        return initialInfrastructureCost;
    }

    public void setInitialInfrastructureCost(final double initialInfrastructureCost) {
        this.initialInfrastructureCost = initialInfrastructureCost;
    }

    public double getEnergyNeededKW() {
        return energyNeededKW;
    }

    public void setEnergyNeededKW(double energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    public String getProducerStrategy() {
        return producerStrategy;
    }

    public void setProducerStrategy(String producerStrategy) {
        this.producerStrategy = producerStrategy;
    }
}
