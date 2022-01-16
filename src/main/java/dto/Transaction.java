package dto;

public class Transaction {
    private Double avg;
    private Double percentile;
    private String transaction;
    public static final int classFieldCount = 3;

    public Transaction(Double avg, Double percentile, String transaction) {
        this.avg = avg;
        this.percentile = percentile;
        this.transaction = transaction;
    }

    public Double getAvg() {
        return avg;
    }

    public Double getPercentile() {
        return percentile;
    }

    public String getTransaction() {
        return transaction;
    }
}
