import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import utils.CalculateTransaction;
import utils.CollectionHandler;
import utils.MapCreator;
import utils.XlsReader;

import java.util.ArrayList;
import java.util.HashMap;


public class Main {
    public static void main(String[] args) {
        HSSFWorkbook workbook = XlsReader.loadBook("C:\\Users\\USER\\Desktop\\Report4.xls");
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

        //TODO: реализовать рассчет по транзакциям
        System.out.println();
    }
}
