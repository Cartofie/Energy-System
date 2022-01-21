package strategies;

import entities.Distributors;
import entities.Producers;

import java.util.ArrayList;
import java.util.Comparator;

public final class GreenStrategy implements StrategyInterface {
    private class GreenSorter implements Comparator<Producers> {

        @Override
        public int compare(Producers o1, Producers o2) {
            if (o1.getStrategy().isRenewable() && !o2.getStrategy().isRenewable()) {
                return -1;
            }
            if (!o1.getStrategy().isRenewable() && o2.getStrategy().isRenewable()) {
                return 1;
            }
            if (o1.getPriceKW() > o2.getPriceKW()) {
                return 1;
            }
            if (o1.getPriceKW() < o2.getPriceKW()) {
                return -1;
            }
            if (o1.getEnergyPerDistributor() > o2.getEnergyPerDistributor()) {
                return -1;
            }
            return 1;
        }
    }

    @Override
    public void producerSelection(Distributors dist, ArrayList<Producers> prod) {
        double energyNeeded = dist.getEnergyNeededKW();
        double price = 0;
        ArrayList<Producers> copy = new ArrayList<>(prod);
        ArrayList<Integer> chosen = new ArrayList<>();
        copy.sort(new GreenSorter());
        dist.removeProducers(prod);
        for (Producers it : copy) {
            if (energyNeeded > 0 && it.getContractedDistributors().size() < it.getMaxDistributors()) {
                energyNeeded -= it.getEnergyPerDistributor();
                price += it.getPriceKW() * it.getEnergyPerDistributor();
                chosen.add(it.getId());
                if (!it.getContractedDistributors().contains(dist)) {
                    it.getContractedDistributors().add(dist);
                }
            }
            if (energyNeeded <= 0) {
                dist.setProductionCost(price);
                dist.setChosenProducers(chosen);
            }
        }
    }
}
