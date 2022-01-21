package entities;

import input.DistributorChanges;
import input.InitialConsumerData;
import input.InitialDistributorData;
import input.InitialProducerData;
import output.OutputConsumerData;
import output.OutputContractsData;
import output.OutputDistributorData;
import output.OutputProducerData;

import java.util.ArrayList;

/**
 * The type Entity factory.
 */
public final class EntityFactory {
    private static final EntityFactory INSTANCE = new EntityFactory();

    private EntityFactory() {

    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static EntityFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Create Consumers consumer.
     *
     * @param cons the Corresponding InitialConsumerData
     * @return the consumer
     */
    public static Consumers createConsumer(final InitialConsumerData cons) {
        return new Consumers(cons.getId(),
                cons.getInitialBudget(),
                cons.getMonthlyIncome());
    }

    /**
     * Create Distributors distributor.
     *
     * @param initDist the Corresponding Initial Distributor Data
     * @return the distributor
     */
    public static Distributors createDistributor(final InitialDistributorData initDist) {
        return new Distributors(initDist.getId(),
                initDist.getInitialBudget(),
                initDist.getContractLength(),
                initDist.getInitialInfrastructureCost(),
                initDist.getEnergyNeededKW(),
                initDist.getProducerStrategy());
    }

    /**
     * Create producer producers.
     *
     * @param initProd the Corresponding Initial Producer Data
     * @return the producer
     */
    public static Producers createProducer(final InitialProducerData initProd) {
        return new Producers(initProd.getId(),
                initProd.getMaxDistributors(),
                initProd.getPriceKW(),
                initProd.getEnergyType(),
                initProd.getEnergyPerDistributor());
    }

    /**
     * Create output consumer data.
     *
     * @param cons the Corresponding consumer
     * @return the output consumer data
     */
    public static OutputConsumerData createOutputConsumerData(final Consumers cons) {
        return new OutputConsumerData(cons.getId(),
                cons.isBankrupt(),
                (int) cons.getBudget());
    }

    /**
     * Create output distributor data.
     *
     * @param dist                the Corresponding Distributor
     * @param outputContractsList the Corresponding Contracts list
     * @return the output distributor data
     */
    public static OutputDistributorData createOutputDistributorData(
            final Distributors dist,
            final ArrayList<OutputContractsData> outputContractsList) {
        return new OutputDistributorData(dist.getId(),
                (int) dist.getBudget(),
                dist.isBankrupt(),
                outputContractsList,
                (int) dist.getEnergyNeededKW(),
                (int) dist.getCurrentPrice(),
                dist.getProducerStrategy());
    }

    /**
     * Create output contracts data.
     *
     * @param ct the Corresponding Contract
     * @return the output contracts data
     */
    public static OutputContractsData createOutputContractsData(final Contracts ct) {
        return new OutputContractsData(ct.getConsumerId(),
                (int) ct.getPrice(),
                ct.getRemainedContractMonths());
    }

    /**
     * Create output producer data output producer data.
     *
     * @param prod the Corresponding Producer
     * @return the output producer data
     */
    public static OutputProducerData createOutputProducerData(Producers prod) {
        return new OutputProducerData(prod.getId(),
                prod.getMaxDistributors(),
                prod.getPriceKW(),
                prod.getStrategy(),
                (int) prod.getEnergyPerDistributor(),
                prod.getMonthlyStats());
    }

    /**
     * Create list object.
     *
     * @param entityType the entity type the list contains
     * @return the list
     */
    public static Object createList(final String entityType) {
        switch (entityType) {
            case "consumers": return new ArrayList<Consumers>();
            case "distributors": return new ArrayList<Distributors>();
            case "changes": return new ArrayList<DistributorChanges>();
            case "outputDistributors": return new ArrayList<OutputDistributorData>();
            case "outputConsumers": return new ArrayList<OutputConsumerData>();
            case "outputContracts": return new ArrayList<OutputContractsData>();
            case "producers": return new ArrayList<Producers>();
            case "outputProducers": return new ArrayList<OutputProducerData>();
            default:
                return null;
        }
    }
}
