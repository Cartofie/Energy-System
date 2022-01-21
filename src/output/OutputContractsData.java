package output;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "consumerId",
        "price",
        "remainedContractMonths"
})
public final class OutputContractsData {
    private final int consumerId;
    private final int price;
    private final int remainedContractMonths;

    public OutputContractsData(final int consumerId,
                               final int price,
                               final int remainedContractMonths) {
        this.consumerId = consumerId;
        this.price = price;
        this.remainedContractMonths = remainedContractMonths;
    }

    public int getConsumerId() {
        return consumerId;
    }

    public int getPrice() {
        return price;
    }

    public int getRemainedContractMonths() {
        return remainedContractMonths;
    }
}
