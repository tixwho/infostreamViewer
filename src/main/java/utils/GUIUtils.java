package utils;

import gui.model.DailyPost;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class GUIUtils {
    public static int oblistCount(ObservableList<DailyPost> postData) {
        int totalUserCount = postData.size();
        int cumulativeCount = 0;
        for(int i=0;i<totalUserCount;i++) {
            cumulativeCount += postData.get(i).getPOSTCOUNT();
        }
        return cumulativeCount;
    }
    //not examining if topN is valid, please check prior to use.
    public static int oblistCount(ObservableList<DailyPost> postData,int topN) {
        int cumulativeCount = 0;
        for(int i=0;i<topN;i++) {
            cumulativeCount += postData.get(i).getPOSTCOUNT();
        }
        return cumulativeCount;
    }
    
    public static int[] freqOblistCount(ObservableList<DailyPost>postData) {
        int cumulativeCount = 0;
        int freqUserCount = 0;
        for(int i=0;i<postData.size();i++) {
            if(postData.get(i).getISFREQUENT()) {
                freqUserCount +=1;
                cumulativeCount += postData.get(i).getPOSTCOUNT();
            }
        }
        int[] rtrArray = {freqUserCount,cumulativeCount};
        return rtrArray;
    }
    //examine if topN is parsable
    public static int tryParse(String input) {
        try {
            return Integer.parseInt(input);
        }catch(NumberFormatException ne) {
            return -1;
        }
    }
    
    public static void showMaintenance(Stage stage) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.initOwner(stage);
        alert.setTitle("Notice!");
        alert.setHeaderText("Unable to complete request!");
        alert.setContentText("This function is currently under maintenance.");
        alert.showAndWait();
    }
    public static void showMaintenance(Stage stage,String info) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.initOwner(stage);
        alert.setTitle("Notice!");
        alert.setHeaderText("Important Information");
        alert.setContentText(info);
        alert.showAndWait();
    }

}
