import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import utils.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;


public class Main {
    public static void main(String[] args) {
        String path = "C:\\Users\\USER\\Desktop\\";
        HSSFWorkbook workbook = XlsReader.loadBook(path + "Report4.xls");
        HSSFSheet sheet= workbook.getSheetAt(0);

        //собираем мапу из названий колонок
        HashMap<Integer, String> nameCols = MapCreator.createTransactionNameMap(sheet, new HashMap<>());

        //парсинг эксельника в коллекцию
        HashMap<String, ArrayList<Double>> excelSheet = MapCreator.createExcelSheetHashMap(sheet, nameCols, new HashMap<>());

        //удаляем мэйновые транзакции
        excelSheet = CollectionHandler.deleteMainTransaction(excelSheet);

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

        //упаковываем все расчеты в объект
        Report report = new Report(avgTree, percentileTree);
        //пишем в файл
        ReportWriter.writeToTxt(report, path);
        ReportWriter.writeToXls(report, path);
    }
}
