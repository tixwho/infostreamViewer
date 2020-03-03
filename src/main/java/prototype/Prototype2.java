package prototype;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import reader.StreamUserReader;
import utils.CountUtils;

public class Prototype2 {

    public static void main(String[] args) {
        String readingAddr = "E:\\lzx\\etc\\ss\\stream\\新手区 - SS同盟.html";
        List<String> rawNames;
        int postCount;
        int userCount;
        try {
            StreamUserReader hReader = new StreamUserReader(readingAddr);
            rawNames = hReader.getPostUserList();
        } catch (IOException e1) {
            System.out.println("Failed Reading raw html!");
            System.err.print(e1.getMessage());
            return;
        }
        postCount = rawNames.size();
        List<Map.Entry<String, Integer>> orderedFreqList =
            CountUtils.orderedFreqList(CountUtils.sortUserFrequency(rawNames));
        userCount = orderedFreqList.size();
        System.out.println("TOTAL POST: "+ postCount);
        
        for (int i = 0; i < userCount; i++) {
            String id = orderedFreqList.get(i).toString();
            System.out.println(id);
        }
    }

}
