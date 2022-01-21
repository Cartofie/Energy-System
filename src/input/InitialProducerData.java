package input;

import entities.EnergyType;

public final class InitialProducerData {
    private int id;
    private EnergyType energyType;
    private int maxDistributors;
    private double priceKW;
    private double energyPerDistributor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EnergyType getEnergyType() {
        return energyType;
    }

    public void setEnergyType(EnergyType energyType) {
        this.energyType = energyType;
    }

    public int getMaxDistributors() {
        return maxDistributors;
    }

    public void setMaxDistributors(int maxDistributors) {
        this.maxDistributors = maxDistributors;
    }

    public double getPriceKW() {
        return priceKW;
    }

    public void setPriceKW(double priceKW) {
        this.priceKW = priceKW;
    }

    public double getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public void setEnergyPerDistributor(double energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }
}
