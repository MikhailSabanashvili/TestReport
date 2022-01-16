package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CalculateTransaction {
    public static HashMap<String, Double> calculateAvg(HashMap<String, ArrayList<Double>> map) {
        HashMap<String, Double> resultMap = new HashMap<>();
        for(Map.Entry<String, ArrayList<Double>> entry: map.entrySet()) {
            Double avg = 0.0;
            for (int i = 0; i < entry.getValue().size(); i++) {
                avg += entry.getValue().get(i);
            }
            avg = avg / entry.getValue().size();
            resultMap.put(entry.getKey(), avg);
        }
        return resultMap;
    }

    public static HashMap<String, Double> calculate90Perc(HashMap<String, ArrayList<Double>> map) {
        HashMap<String, Double> resultMap = new HashMap<>();
        for(Map.Entry<String, ArrayList<Double>> entry: map.entrySet()) {
            Collections.sort(entry.getValue());
            int index = (int) Math.ceil(90.0 / 100.0 * entry.getValue().size());
            resultMap.put(entry.getKey(), entry.getValue().get(index - 1));
        }
        return resultMap;
    }
}
