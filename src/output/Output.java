package output;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;

@JsonPropertyOrder({
        "consumers",
        "distributors",
        "energyProducers"
})
public final class Output {
    private final ArrayList<OutputConsumerData> consumers;
    private final ArrayList<OutputDistributorData> distributors;
    private final ArrayList<OutputProducerData> energyProducers;

    public Output(final ArrayList<OutputConsumerData> consumers,
                  final ArrayList<OutputDistributorData> distributors,
                  final ArrayList<OutputProducerData> producers) {
        this.consumers = new ArrayList<OutputConsumerData>(consumers);
        this.distributors = new ArrayList<OutputDistributorData>(distributors);
        this.energyProducers = new ArrayList<OutputProducerData>(producers);
    }

    public ArrayList<OutputConsumerData> getConsumers() {
        return consumers;
    }

    public ArrayList<OutputDistributorData> getDistributors() {
        return distributors;
    }

    public ArrayList<OutputProducerData> getEnergyProducers() {
        return energyProducers;
    }
}
