package entities;

import java.util.ArrayList;
import java.util.Collections;

public final class ProducerMonthlyStats {
    private int month;
    private ArrayList<Integer> distributorsIds;

    public ProducerMonthlyStats(int monthNumber, ArrayList<Integer> distributorsID) {
        this.month = monthNumber;
        Collections.sort(distributorsID);
        this.distributorsIds = new ArrayList<>(distributorsID);
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int monthNumber) {
        this.month = monthNumber;
    }

    public ArrayList<Integer> getDistributorsIds() {
        return distributorsIds;
    }

    public void setDistributorsIds(ArrayList<Integer> distributorsID) {
        this.distributorsIds = distributorsID;
    }
}
