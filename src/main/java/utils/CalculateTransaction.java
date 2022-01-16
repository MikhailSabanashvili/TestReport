package utils;

import java.util.ArrayList;
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
}
