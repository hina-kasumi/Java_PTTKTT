package org.hina.buoi7.Knapback;

import java.util.ArrayList;
import java.util.List;

public class KnapbackSolve {
    private Item[] items;
    private int maxProfit;

    public KnapbackSolve(Item[] items, int w) {
        int n = items.length;
        this.items = items;

        int dp[][] = new int[n + 1][w + 1];
        boolean[][] takeItems = new boolean[n + 1][w + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= w; j++) {
                dp[i][j] = dp[i - 1][j];
                if (items[i - 1].getWeight() <= j) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - items[i - 1].getWeight()] + items[i - 1].getProfit());
                }
                takeItems[i][j] = dp[i - 1][j] < dp[i][j];
            }
        }

        for (int i = n, j = w; i > 0; i--) {
            if (takeItems[i][j]) {
                items[i - 1].setTake(true);
                j = j - items[i - 1].getWeight();
            }
        }

        maxProfit = dp[n][w];
    }

    public Item[] getItems() {
        return items;
    }

    public List<Item> getTakedItem() {
        List<Item> list = new ArrayList<>();
        for (Item item : items) {
            if (item.isTake()) {
                list.add(item);
            }
        }

        return list;
    }

    public int getMaxProfit() {
        return maxProfit;
    }

    public static void main(String[] args) {
        KnapbackSolve knapbackSolve = new KnapbackSolve(
                new Item[] {
                        new Item("1", 1, 1),
                        new Item("2", 6, 2),
                        new Item("3", 18, 5),
                        new Item("4", 22, 6),
                        new Item("5", 28, 7)
                }, 11);

        System.out.println("giá trị tối đa có thể lấy được: " + knapbackSolve.getMaxProfit());

        System.out.println("những đồ vật được lấy:");
        knapbackSolve.getTakedItem().forEach(System.out::println);
    }
}