package utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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

    public static HashMap<String, ArrayList<Double>> createExcelSheetHashMap(HSSFSheet sheet, HashMap<Integer, String> nameCols, HashMap<String, ArrayList<Double>> excelSheet) {
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
}
