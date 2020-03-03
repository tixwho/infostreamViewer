package gui.view;

import java.io.File;
import gui.MainApp;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

public class RootLayoutController {
    
 // Reference to the main application
    private MainApp mainApp;

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    /**
     * 
     */
    //todo clear file before load another file
    @FXML
    private void handleLoad() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
            "HTML Files", "*.html", "*.htm");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            mainApp.loadPostFromFile(file);
        }
    }
    //todo change load to append
    @FXML
    private void handleAppendLoad() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
            "HTML Files", "*.html", "*.htm");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            mainApp.appendPostFromFile(file);
        }
    }
    //todo complete save
    @FXML
    private void handleSaveAsTxt() {
        mainApp.saveTXT();
    }
    //todo complete save
    @FXML
    private void handleSaveAsXml() {
        mainApp.saveXML();
        
    }
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleTime() {
        mainApp.showTimestampPop();
        
    }

}
