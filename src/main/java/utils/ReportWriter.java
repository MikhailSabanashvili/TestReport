package utils;

import dto.Transaction;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class ReportWriter {
    public static void writeToTxt(Report report, String path) {
        try(Writer writer = new FileWriter(path + "Report.txt")) {
            writer.write("Имя транзакции       " + "Среднее значение       " + "90й перцентиль");
            writer.write(System.getProperty("line.separator"));
            for (Transaction transaction: report.getTransactions()) {
                writer.write(transaction.getTransaction() + "              "
                        + transaction.getAvg() + "            " + transaction.getPercentile());
                writer.write(System.getProperty("line.separator"));
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
