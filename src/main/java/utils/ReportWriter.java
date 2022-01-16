package utils;

import dto.Transaction;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;

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

    public static void writeToXls(Report report, String path) {
        File file = new File(path + "Report.xls");
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet("Report");
        Row firstRow = sheet.createRow(0);
        Cell _name = firstRow.createCell(0);
        _name.setCellValue("Transaction");
        Cell _name1 = firstRow.createCell(1);
        _name1.setCellValue("AVG");
        Cell _name2 = firstRow.createCell(2);
        _name2.setCellValue("90Perc");

        for (int i = 1; i < report.getTransactions().size(); i++) {
            Row row = sheet.createRow(i);
            Cell name = row.createCell(0);
            name.setCellValue(report.getTransactions().get(i).getTransaction());
            Cell name1 = row.createCell(1);
            name1.setCellValue(report.getTransactions().get(i).getAvg());
            Cell name2 = row.createCell(2);
            name2.setCellValue(report.getTransactions().get(i).getPercentile());
        }

        try {
            book.write(new FileOutputStream(file));
            book.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }
}
