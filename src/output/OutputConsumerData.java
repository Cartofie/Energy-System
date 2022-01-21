package output;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "isBankrupt",
        "budget"
})
public final class OutputConsumerData {
    private final int id;
    private final boolean isBankrupt;
    private final int budget;

    public OutputConsumerData(final int id, final boolean isBankrupt, final int budget) {
        this.id = id;
        this.isBankrupt = isBankrupt;
        this.budget = budget;
    }

    public int getId() {
        return id;
    }

    public boolean getIsBankrupt() {
        return isBankrupt;
    }

    public int getBudget() {
        return budget;
    }
}
