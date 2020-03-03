package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import gui.model.DailyPost;
import javafx.collections.ObservableList;

public class CountUtils {

    public static HashMap<String, Integer> sortUserFrequency(List<String> postList) {
        //Credit:DZero
        HashMap<String, Integer> frequencyMap = new HashMap<String, Integer>();
        String total = "共计 " + postList.size();
        postList.add(total);

        while (!postList.get(0).equals(total)) {
            String targetName = postList.get(0);
            Integer occurences = Collections.frequency(postList, targetName);
            postList.removeAll(Collections.singletonList(targetName));
            frequencyMap.put(targetName, occurences);
        }

        return frequencyMap;
    }

    public static List<Map.Entry<String, Integer>> orderedFreqList(HashMap<String, Integer> inMap) {
        List<Map.Entry<String, Integer>> orderList =
            new ArrayList<Map.Entry<String, Integer>>(inMap.entrySet());
     // 对HashMap中的 value 进行排序
        Collections.sort(orderList, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                    Map.Entry<String, Integer> o2) {
                return o2.getValue()-o1.getValue();
            }
        });

        return orderList;
    }
    
    public static void sortObservableArrList(ObservableList<DailyPost> postList) {
        Collections.sort(postList, new Comparator<DailyPost>() {
            public int compare(DailyPost o1,
                    DailyPost o2) {
                return o2.getPOSTCOUNT()-o1.getPOSTCOUNT();
            }
        });
    }

}
