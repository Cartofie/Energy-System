package strategies;

import entities.Distributors;

public class Strategy {
    public static StrategyInterface select(Distributors dist) {
        if (dist.getProducerStrategy().equals("GREEN")) {
            return new GreenStrategy();
        } else if (dist.getProducerStrategy().equals("PRICE")) {
            return new PriceStrategy();
        } else if (dist.getProducerStrategy().equals("QUANTITY")) {
            return new QuantityStrategy();
        }
        return null;
    }
}
