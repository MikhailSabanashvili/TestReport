package utils;

import dto.Transaction;

import java.util.*;

public class Report {
    private ArrayList<Transaction> transactions;

    public Report(TreeMap<String, Double> avgTree, TreeMap<String, Double> percentileTree) {
        this.transactions = new ArrayList<>();
        Iterator<Map.Entry<String, Double>> iteratorAvg = avgTree.entrySet().iterator();
        Iterator<Map.Entry<String, Double>> iteratorPerc = percentileTree.entrySet().iterator();
        while (iteratorAvg.hasNext() && iteratorPerc.hasNext()) {
            Map.Entry<String, Double> entry = iteratorPerc.next();
            if(entry.getKey().contains("Percent of Transactions")) {
                iteratorAvg.next();
                continue;
            }
            transactions.add(new Transaction(iteratorAvg.next().getValue(), entry.getValue(), entry.getKey()));
        }
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}
