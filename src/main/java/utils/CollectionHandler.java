package utils;

import java.util.*;
import java.util.stream.Collectors;

public class CollectionHandler {
    public static Map<String, ?> deleteMainTransaction(HashMap<String, ?> excelSheet) {
        return  excelSheet.entrySet().stream()
                .filter(s -> !s.getKey().contains("MAIN"))
                .filter(s -> !(s.getKey().contains("Percent")))
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
