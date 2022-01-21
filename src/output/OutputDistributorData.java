package output;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;

@JsonPropertyOrder({
        "id",
        "energyNeededKW",
        "contractCost",
        "budget",
        "producerStrategy",
        "isBankrupt",
        "contracts"
})
public final class OutputDistributorData {
    private final int id;
    private final int energyNeededKW;
    private final int contractCost;
    private final int budget;
    private final String producerStrategy;
    private final boolean isBankrupt;
    private final ArrayList<OutputContractsData> contracts;

    public OutputDistributorData(final int id,
                                 final int budget,
                                 final boolean isBankrupt,
                                 final ArrayList<OutputContractsData> contracts,
                                 final int energyNeededKW,
                                 final int contractCost,
                                 final String producerStrategy) {
        this.id = id;
        this.budget = budget;
        this.isBankrupt = isBankrupt;
        this.contracts = contracts;
        this.energyNeededKW = energyNeededKW;
        this.contractCost = contractCost;
        this.producerStrategy = producerStrategy;
    }

    public int getId() {
        return id;
    }

    public int getBudget() {
        return budget;
    }

    public boolean getIsBankrupt() {
        return isBankrupt;
    }

    public ArrayList<OutputContractsData> getContracts() {
        return contracts;
    }

    public int getEnergyNeededKW() {
        return energyNeededKW;
    }

    public int getContractCost() {
        return contractCost;
    }

    public String getProducerStrategy() {
        return producerStrategy;
    }
}
