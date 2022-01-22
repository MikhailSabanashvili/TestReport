package utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.*;

public class MapCreator {
    public static HashMap<Integer, String> createTransactionNameMap(HSSFSheet sheet, HashMap<Integer, String> nameCols) {
        HSSFRow row = sheet.getRow(0);
        Integer key = 0;
        Iterator<Cell> cellIterator = row.iterator();
        while (cellIterator.hasNext()) {
            nameCols.put(key, cellIterator.next().getStringCellValue());
            key++;
        }

        return CollectionHandler.deleteEmptyCols(nameCols);
    }

    public static HashMap<String, ArrayList<Double>> createExcelSheetHashMapForReports4(HSSFSheet sheet, HashMap<Integer, String> nameCols, HashMap<String, ArrayList<Double>> excelSheet) {
        for (int col = 0; col < nameCols.size(); col++) {
            Iterator<Row> iterator = sheet.rowIterator();
            ArrayList<Double> cells = new ArrayList<>();
            while (iterator.hasNext()) {
                try {
                    Row row = iterator.next();
                    HSSFCell cell = (HSSFCell) row.getCell(col);
                    cells.add(Double.parseDouble(cell.getStringCellValue()));
                } catch (NumberFormatException e) {
                }
            }
            excelSheet.put(nameCols.get(col), cells);
        }

        return excelSheet;
    }

    public static HashMap<String, ArrayList<Integer>> createExcelSheetHashMapForReports2(HSSFSheet sheet, HashMap<String, ArrayList<Integer>> excelSheet) {
        ArrayList<String> transactions = new ArrayList<>();
        ArrayList<Integer> passed = new ArrayList<>();
        ArrayList<Integer> failed = new ArrayList<>();
        Iterator<Row> iterator = sheet.rowIterator();
        iterator.next();
        while (iterator.hasNext()) {
            try {
                Row row = iterator.next();
                HSSFCell cell = (HSSFCell) row.getCell(0);
                transactions.add(cell.getStringCellValue());
                cell = (HSSFCell) row.getCell(1);
                passed.add(Integer.parseInt(cell.getStringCellValue()));
                cell = (HSSFCell) row.getCell(2);
                failed.add(Integer.parseInt(cell.getStringCellValue()));
            } catch (NumberFormatException e) { }
        }
        for (int i = 0; i < transactions.size() - 2; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(passed.get(i));
            list.add(failed.get(i));
            excelSheet.put(transactions.get(i), list);
        }

        return excelSheet;
    }
}
