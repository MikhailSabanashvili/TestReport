import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import utils.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class Main {
    public static void main(String[] args) {
        String path = "C:\\Users\\USER\\Desktop\\";
        HSSFWorkbook workbook = XlsReader.loadBook(path + "Report4.xls");
        HSSFWorkbook workbook1 = XlsReader.loadBook(path + "Report2.xls");

        HSSFSheet sheet= workbook.getSheetAt(0);
        HSSFSheet sheet1= workbook1.getSheetAt(0);

        //собираем мапу из названий колонок
        HashMap<Integer, String> nameCols = MapCreator.createTransactionNameMap(sheet, new HashMap<>());
        HashMap<Integer, String> nameCols1 = MapCreator.createTransactionNameMap(sheet1, new HashMap<>());

        //парсинг эксельника в коллекцию
        HashMap<String, ArrayList<Double>> excelSheet = MapCreator.createExcelSheetHashMap(sheet, nameCols, new HashMap<>());
        HashMap<String, ArrayList<Integer>> excelSheet1 = MapCreator.createExcelSheetHM(sheet1, new HashMap<>());

        //удаляем мэйновые транзакции
        excelSheet = CollectionHandler.deleteMainTransaction(excelSheet);
        excelSheet1 = CollectionHandler.deleteMainT(excelSheet1);

        //считаем avg
        HashMap<String, Double> avgMap = CalculateTransaction.calculateAvg(excelSheet);

        //считаем 90Perc
        HashMap<String, Double> percentileMap = CalculateTransaction.calculate90Perc(excelSheet);

        //сортировка
        @SuppressWarnings("unchecked")
        TreeMap<String, ArrayList<Double>> excel =  (TreeMap<String, ArrayList<Double>>) CollectionHandler.sortMap(excelSheet);
        @SuppressWarnings("unchecked")
        TreeMap<String, Double> avgTree = (TreeMap<String, Double>) CollectionHandler.sortMap(avgMap);
        @SuppressWarnings("unchecked")
        TreeMap<String, Double> percentileTree = (TreeMap<String, Double>) CollectionHandler.sortMap(percentileMap);
        @SuppressWarnings("unchecked")
        TreeMap<String, ArrayList<Integer>> transactions = (TreeMap<String, ArrayList<Integer>>) CollectionHandler.sortMap(excelSheet1);

        //упаковываем все расчеты в объект
        Report report = new Report(avgTree, percentileTree, transactions);
        //пишем в файл
        ReportWriter.writeToTxt(report, path);
        ReportWriter.writeToXls(report, path);
    }
}
