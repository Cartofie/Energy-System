package output;

import entities.EnergyType;
import entities.ProducerMonthlyStats;

import java.util.ArrayList;

public final class OutputProducerData {
    private final int id;
    private final int maxDistributors;
    private final double priceKW;
    private final EnergyType energyType;
    private final int energyPerDistributor;
    private final ArrayList<ProducerMonthlyStats> monthlyStats;

    public OutputProducerData(int id,
                              int maxDistributors,
                              double priceKW,
                              EnergyType energyType,
                              int energyPerDistributor,
                              ArrayList<ProducerMonthlyStats> monthlyStats) {
        this.id = id;
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.energyType = energyType;
        this.energyPerDistributor = energyPerDistributor;
        this.monthlyStats = monthlyStats;
    }

    public int getId() {
        return id;
    }

    public int getMaxDistributors() {
        return maxDistributors;
    }

    public double getPriceKW() {
        return priceKW;
    }

    public EnergyType getEnergyType() {
        return energyType;
    }

    public ArrayList<ProducerMonthlyStats> getMonthlyStats() {
        return monthlyStats;
    }

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }
}
