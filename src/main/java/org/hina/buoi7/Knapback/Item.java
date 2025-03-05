package org.hina.buoi7.Knapback;

public class Item {
    private final String name;
    private final int profit;
    private final int weight;
    private boolean take;

    public Item(String name, int profit, int weight) {
        this.name = name;
        this.profit = profit;
        this.weight = weight;
    }

    public void setTake(boolean take) {
        this.take = take;
    }

    public boolean isTake() {
        return take;
    }

    public int getProfit() {
        return profit;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return name + " " + profit + " " + weight;
    }
}
