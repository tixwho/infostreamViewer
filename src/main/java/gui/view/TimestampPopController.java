package gui.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.TimeUtils;

public class TimestampPopController {
    
    @FXML
    private TextField startTimeField;
    @FXML
    private TextField endTimeField;
    @FXML
    private TextField outputField;
    
    @SuppressWarnings("unused")
    private Stage dialogStage;

    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        setInitTime();
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setInitTime() {
        this.endTimeField.setText(TimeUtils.currentFormattedDate());
    }
    
    public void handleCalculate() {
        String starttime = this.startTimeField.getText();
        String endtime = this.endTimeField.getText();
        this.outputField.setText(calculateTimestamp(starttime,endtime));
    }
    
    private String calculateTimestamp(String start,String end) {
        return"&before="+TimeUtils.getTimeStamp(end)+"&latest="+TimeUtils.getTimeStamp(start);
    }

}
