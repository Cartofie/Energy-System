package input;

import java.util.ArrayList;

public final class InitialData {
    private ArrayList<InitialConsumerData> consumers;
    private ArrayList<InitialDistributorData> distributors;
    private ArrayList<InitialProducerData> producers;

    public InitialData() {
    }

    public ArrayList<InitialConsumerData> getConsumers() {
        return consumers;
    }

    public void setConsumers(final ArrayList<InitialConsumerData> consumers) {
        this.consumers = consumers;
    }

    public ArrayList<InitialDistributorData> getDistributors() {
        return distributors;
    }

    public void setDistributors(final ArrayList<InitialDistributorData> distributors) {
        this.distributors = distributors;
    }

    public ArrayList<InitialProducerData> getProducers() {
        return producers;
    }

    public void setProducers(final ArrayList<InitialProducerData> producers) {
        this.producers = producers;
    }
}
