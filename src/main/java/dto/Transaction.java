package dto;

public class Transaction {
    private Integer fail;
    private Integer pass;
    private Double avg;
    private Double percentile;
    private String transaction;
    public static final int classFieldCount = 3;

    public Transaction(Double avg, Double percentile, String transaction, Integer pass, Integer fail) {
        this.avg = avg;
        this.percentile = percentile;
        this.transaction = transaction;
        this.pass = pass;
        this.fail = fail;
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

    public Integer getFail() {
        return fail;
    }

    public Integer getPass() {
        return pass;
    }
}
