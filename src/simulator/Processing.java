package simulator;

import entities.Consumers;
import entities.Contracts;
import entities.Distributors;
import entities.EntityFactory;
import entities.Producers;
import input.DistributorChanges;
import input.InitialConsumerData;
import input.InitialDistributorData;
import input.InitialProducerData;
import input.Input;
import input.MonthlyUpdates;
import input.ProducerChanges;
import output.Output;
import output.OutputConsumerData;
import output.OutputContractsData;
import output.OutputDistributorData;
import output.OutputProducerData;

import java.util.ArrayList;

/**
 * The type Processing.
 */
@SuppressWarnings("unchecked")
public final class Processing {

    private ArrayList<Consumers> consumers;

    {
        consumers = (ArrayList<Consumers>) EntityFactory.createList("consumers");
    }

    private ArrayList<Distributors> distributors;

    {
        distributors = (ArrayList<Distributors>) EntityFactory.createList("distributors");
    }

    private ArrayList<Producers> producers;

    {
        producers = (ArrayList<Producers>) EntityFactory.createList("producers");
    }

    /**
     * Instantiates a new Processing.
     */
    public Processing() {
    }

    /**
     * Gets consumers.
     *
     * @return the consumers
     */
    public ArrayList<Consumers> getConsumers() {
        return consumers;
    }

    /**
     * Sets consumers.
     *
     * @param consumers the consumers
     */
    public void setConsumers(final ArrayList<Consumers> consumers) {
        this.consumers = consumers;
    }

    /**
     * Gets distributors.
     *
     * @return the distributors
     */
    public ArrayList<Distributors> getDistributors() {
        return distributors;
    }

    /**
     * Sets distributors.
     *
     * @param distributors the distributors
     */
    public void setDistributors(final ArrayList<Distributors> distributors) {
        this.distributors = distributors;
    }

    public ArrayList<Producers> getProducers() {
        return producers;
    }

    public void setProducers(ArrayList<Producers> producers) {
        this.producers = producers;
    }

    /**
     * Sets first month's contracts
     */
    public void setupFirstMonth() {
        for (Distributors dist : distributors) {
            dist.getStrategy().producerSelection(dist, producers);
            dist.adjustCurrentPrice();
        }
        for (Consumers cons : consumers) {
            cons.lookForContract(this);
        }
        for (Consumers cons : consumers) {
            cons.adjustConsumer();
        }
        for (Distributors dist : distributors) {
            dist.adjustDistributor(this);
            dist.adjustCurrentPrice();
        }
        Producers.sort(producers);
    }

    /**
     * Run simulation.
     *
     * @param numberOfTurns  the number of turns
     * @param monthlyUpdates the monthly updates list from Input
     */
    public void runSimulation(final int numberOfTurns,
                              final ArrayList<MonthlyUpdates> monthlyUpdates) {
        MonthlyUpdates iter;
        Consumers newCons;

        for (int i = 1; i < numberOfTurns; i++) {
            /**
             * this month's updates in terms of new consumers and cost changes
             */
            iter = monthlyUpdates.get(i - 1);

            if (iter.getNewConsumers() != null) {
                /**
                 * iterating through this month's new consumers and adding them to our list
                 */
                for (InitialConsumerData it : iter.getNewConsumers()) {
                    newCons = EntityFactory.createConsumer(it);
                    newCons.setContractedMonthsRemaining(0);
                    consumers.add(newCons);
                }
            }
            if (iter.getDistributorChanges() != null) {
                /**
                 * iterating through this month's cost changes
                 * and setting the involved distributors accordingly
                 */
                for (DistributorChanges it : iter.getDistributorChanges()) {
                    for (Distributors dist : distributors) {
                        if (it.getId() == dist.getId()) {
                            dist.setInfrastructureCost(it.getInfrastructureCost());
                            dist.adjustCurrentPrice();
                            break;
                        }
                    }
                }
            }
            for (Consumers cons : consumers) {
                /**
                 * if consumers currently have an expired contract
                 * and they are not bankrupt they set up a new one
                 * after the cost changes have gone through
                 */
                if (!cons.isBankrupt() && cons.getContractedMonthsRemaining() == 0) {
                    cons.lookForContract(this);
                }
            }
            for (Consumers cons : consumers) {
                cons.adjustConsumer();
            }
            for (Distributors dist : distributors) {
                dist.adjustDistributor(this);
            }
            if (iter.getProducerChanges() != null) {
                /**
                 * iterating through this month's producer changes
                 * and setting the involved producers accordingly
                 * while also checking if the distributors opt for different providers
                 */
                for (ProducerChanges it : iter.getProducerChanges()) {
                    for (Producers prod : producers) {
                        if (it.getId() == prod.getId()) {
                            prod.setEnergyPerDistributor(it.getEnergyPerDistributor());
                            prod.updateDistributors();
                            break;
                        }
                    }
                }
                for (Distributors dist : distributors) {
                    if (dist.getNeedsUpdate()) {
                        dist.update(producers);
                    }
                    if (i != numberOfTurns - 1) {
                        dist.adjustCurrentPrice();
                    }
                }
            }
            for (Producers prod : producers) {
                prod.adjustProducer(i);
            }
        }
    }

    /**
     * Fills the data stored inside the input object into the corresponding lists
     *
     * @param input the input
     */
    public void fillData(final Input input) {
        Consumers cons;
        Distributors dist;
        Producers prod;

        for (InitialConsumerData it : input.getInitialData().getConsumers()) {
            /**
             * puts the Initial Consumer Data from the input object into the list
             */
            cons = EntityFactory.createConsumer(it);
            this.consumers.add(cons);
        }
        for (InitialDistributorData it : input.getInitialData().getDistributors()) {
            /**
             * puts the Initial Distributor Data from the input object into the list
             */
            dist = EntityFactory.createDistributor(it);
            dist.setCurrentPrice(dist.getInfrastructureCost() + dist.getProductionCost() + Math.round(Math.floor(0.2 * dist.getProductionCost())));
            this.distributors.add(dist);
        }
        for (InitialProducerData it : input.getInitialData().getProducers()) {
            /**
             * puts the Initial Producer Data from the input object into the list
             */
            prod = EntityFactory.createProducer(it);
            this.producers.add(prod);
        }
        setupFirstMonth();
        runSimulation(input.getNumberOfTurns() + 1, input.getMonthlyUpdates());
    }

    /**
     * Fills the processed data into the output object in order to be printed as a json
     *
     * @return the output
     */
    public Output prepareData() {
        ArrayList<OutputConsumerData> outputConsumerList = (ArrayList<OutputConsumerData>) EntityFactory.createList("outputConsumers");
        OutputConsumerData outputConsumer;
        ArrayList<OutputDistributorData> outputDistributorList = (ArrayList<OutputDistributorData>) EntityFactory.createList("outputDistributors");
        OutputDistributorData outputDistributor;
        ArrayList<OutputProducerData> outputProducerList = (ArrayList<OutputProducerData>) EntityFactory.createList("outputProducers");
        OutputProducerData outputProducer;

        for (Consumers cons : this.consumers) {
            /**
             * puts the Output Consumer Data from the processed consumer into the list
             */
            outputConsumer = EntityFactory.createOutputConsumerData(cons);
            outputConsumerList.add(outputConsumer);
        }
        for (Distributors dist : this.distributors) {
            /**
             * puts the Output Distributor Data from the processed distributor into the list
             */
            ArrayList<OutputContractsData> outputContractsList = (ArrayList<OutputContractsData>) EntityFactory.createList("outputContracts");
            OutputContractsData outputContract;
            for (Contracts cont : dist.getContracts()) {
                /**
                 * puts the Output Contracts Data from the processed contracts into the list
                 */
                outputContract = EntityFactory.createOutputContractsData(cont);
                outputContractsList.add(outputContract);
            }
            outputDistributor = EntityFactory.createOutputDistributorData(dist,
                    outputContractsList);
            outputDistributorList.add(outputDistributor);
        }
        for (Producers prod : this.producers) {
            /**
             * puts the Output Consumer Data from the processed consumer into the list
             */
            outputProducer = EntityFactory.createOutputProducerData(prod);
            outputProducerList.add(outputProducer);
        }
        /**
         * instantiates the new output object with the output data
         */
        return new Output(outputConsumerList, outputDistributorList, outputProducerList);
    }
}
