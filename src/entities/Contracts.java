package entities;

import java.util.Objects;

public final class Contracts {
    private int consumerId;
    private double price;
    private int remainedContractMonths;

    public Contracts() {
    }

    public Contracts(final int consumerId, final double price, final int remainedContractMonths) {
        this.consumerId = consumerId;
        this.price = price;
        this.remainedContractMonths = remainedContractMonths;
    }

    public int getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(final int consumerId) {
        this.consumerId = consumerId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public int getRemainedContractMonths() {
        return remainedContractMonths;
    }

    public void setRemainedContractMonths(final int remainedContractMonths) {
        this.remainedContractMonths = remainedContractMonths;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Contracts)) {
            return false;
        }
        Contracts contracts = (Contracts) o;
        return getConsumerId() == contracts.getConsumerId()
                && Double.compare(contracts.getPrice(), getPrice()) == 0
                && getRemainedContractMonths() == contracts.getRemainedContractMonths();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getConsumerId(), getPrice(), getRemainedContractMonths());
    }
}
