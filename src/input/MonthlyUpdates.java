package input;

import java.util.ArrayList;

public final class MonthlyUpdates {
    private ArrayList<InitialConsumerData> newConsumers;
    private ArrayList<DistributorChanges> distributorChanges;
    private ArrayList<ProducerChanges> producerChanges;

    public MonthlyUpdates() {
    }

    public MonthlyUpdates(final ArrayList<InitialConsumerData> newConsumers,
                          final ArrayList<DistributorChanges> costChanges,
                          final ArrayList<ProducerChanges> producerChanges) {
        this.newConsumers = newConsumers;
        this.distributorChanges = costChanges;
        this.producerChanges = producerChanges;
    }

    public ArrayList<InitialConsumerData> getNewConsumers() {
        return newConsumers;
    }

    public void setNewConsumers(final ArrayList<InitialConsumerData> newConsumers) {
        this.newConsumers = newConsumers;
    }

    public ArrayList<DistributorChanges> getDistributorChanges() {
        return distributorChanges;
    }

    public void setDistributorChanges(final ArrayList<DistributorChanges> costChanges) {
        this.distributorChanges = costChanges;
    }

    public ArrayList<ProducerChanges> getProducerChanges() {
        return producerChanges;
    }

    public void setProducerChanges(ArrayList<ProducerChanges> producerChanges) {
        this.producerChanges = producerChanges;
    }
}
