package utils;

import dto.Transaction;

import java.util.*;

public class Report {
    private ArrayList<Transaction> transactions;

    public Report(TreeMap<String, Double> avgTree, TreeMap<String, Double> percentileTree, TreeMap<String, ArrayList<Integer>> transact) throws Exception {
        this.transactions = new ArrayList<>();
        Iterator<Map.Entry<String, Double>> iteratorAvg = avgTree.entrySet().iterator();
        Iterator<Map.Entry<String, Double>> iteratorPerc = percentileTree.entrySet().iterator();
        Iterator<Map.Entry<String, ArrayList<Integer>>> iteratorTransact = transact.entrySet().iterator();
        while (iteratorAvg.hasNext() && iteratorPerc.hasNext()) {
            Map.Entry<String, Double> entry = iteratorPerc.next();
            if(entry.getKey().contains("Percent of Transactions")) {
                iteratorAvg.next();
                continue;
            }
            Map.Entry<String, ArrayList<Integer>> entry1 = iteratorTransact.next();
            if(entry.getKey().equals(entry1.getKey())) {
                transactions.add(new Transaction(iteratorAvg.next().getValue(), entry.getValue(), entry.getKey(),
                        entry1.getValue().get(0), entry1.getValue().get(1)));
            } else {
                throw new Exception("Рассинхронизация обхода коллекций");
            }
        }
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}
