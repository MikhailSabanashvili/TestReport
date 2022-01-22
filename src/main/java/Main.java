import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import utils.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;


public class Main {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        String path = "C:\\Users\\USER\\Desktop\\";
        HSSFWorkbook workbook = XlsReader.loadBook(path + "Report4.xls");
        HSSFWorkbook workbook1 = XlsReader.loadBook(path + "Report2.xls");

        HSSFSheet sheet4= workbook.getSheetAt(0);
        HSSFSheet sheet2= workbook1.getSheetAt(0);

        //собираем мапу из названий колонок
        HashMap<Integer, String> nameCols = MapCreator.createTransactionNameMap(sheet4, new HashMap<>());

        //парсинг эксельника в коллекцию
        HashMap<String, ArrayList<Double>> excelSheet4 = MapCreator.createExcelSheetHashMapForReports4(sheet4, nameCols, new HashMap<>());
        HashMap<String, ArrayList<Integer>> excelSheet2 = MapCreator.createExcelSheetHashMapForReports2(sheet2, new HashMap<>());

        System.out.println();

        //удаляем мэйновые транзакции
        excelSheet4 = (HashMap<String, ArrayList<Double>>) CollectionHandler.deleteMainTransaction(excelSheet4);
        excelSheet2 = (HashMap<String, ArrayList<Integer>>) CollectionHandler.deleteMainTransaction(excelSheet2);

        //считаем avg
        HashMap<String, Double> avgMap = CalculateTransaction.calculateAvg(excelSheet4);

        //считаем 90Perc
        HashMap<String, Double> percentileMap = CalculateTransaction.calculate90Perc(excelSheet4);

        //сортировка
        TreeMap<String, Double> avgTree = (TreeMap<String, Double>) CollectionHandler.sortMap(avgMap);
        TreeMap<String, Double> percentileTree = (TreeMap<String, Double>) CollectionHandler.sortMap(percentileMap);
        TreeMap<String, ArrayList<Integer>> transactions = (TreeMap<String, ArrayList<Integer>>) CollectionHandler.sortMap(excelSheet2);

        //упаковываем все расчеты в объект
        Report report = new Report(avgTree, percentileTree, transactions);
        //пишем в файл
        ReportWriter.writeToTxt(report, path);
        ReportWriter.writeToXls(report, path);
    }
}
