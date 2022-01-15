package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectionHandler {
    public static HashMap<String, ArrayList<Double>> deleteMainTransaction(HashMap<String, ArrayList<Double>> excelSheet) {
        return (HashMap<String, ArrayList<Double>>) excelSheet.entrySet().stream()
                .filter(s -> !s.getKey().contains("MAIN"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static HashMap<Integer, String> deleteEmptyCols(HashMap<Integer, String> map) {
        return (HashMap<Integer, String>) map.entrySet().stream()
                .filter(s -> !s.getValue().equals(""))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
