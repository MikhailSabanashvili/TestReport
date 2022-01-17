package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CollectionHandler {
    public static HashMap<String, ArrayList<Double>> deleteMainTransaction(HashMap<String, ArrayList<Double>> excelSheet) {
        return (HashMap<String, ArrayList<Double>>) excelSheet.entrySet().stream()
                .filter(s -> !s.getKey().contains("MAIN"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static HashMap<String, ArrayList<Integer>> deleteMainT(HashMap<String, ArrayList<Integer>> excelSheet) {
        return (HashMap<String, ArrayList<Integer>>) excelSheet.entrySet().stream()
                .filter(s -> !s.getKey().contains("MAIN"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static HashMap<Integer, String> deleteEmptyCols(HashMap<Integer, String> map) {
        return (HashMap<Integer, String>) map.entrySet().stream()
                .filter(s -> !s.getValue().equals(""))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static TreeMap<?, ?> sortMap(HashMap<?, ?> map) {
        return new TreeMap<>(map);
    }
}
