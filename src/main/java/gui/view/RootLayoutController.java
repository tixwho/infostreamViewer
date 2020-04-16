package gui.view;

import java.io.File;
import gui.MainApp;
import gui.helper.PrefHelper;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

/**
 * RootLayoutController. In charge of toolbar above. Also in charge of putting other views in the
 * middle.
 * 
 * @author tixwho
 *
 */

public class RootLayoutController {

    // Reference to the main application
    private MainApp mainApp;
    private PrefHelper prefHelp = new PrefHelper(this.getClass());

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
     * handled in loadPostFromFile method in MainApp class.
     * 
     * @button Load HTML
     */
    // todo preferences
    @FXML
    private void handleLoad() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(
            new File(prefHelp.pref.get("htmlDir", System.getProperty("user.dir"))));
        // Set extension filter
        FileChooser.ExtensionFilter extFilter =
            new FileChooser.ExtensionFilter("HTML Files", "*.html", "*.htm");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            prefHelp.pref.put("htmlDir",file.getParentFile().toString());
            mainApp.loadPostFromFile(file);
            if(prefHelp.pref.get("freqFile","null").compareTo("null")!=0) {
                mainApp.setFrequent(new File(prefHelp.pref.get("freqFile","")));
            }
        }
    }

    /**
     * 
     * handled in appendPostFromFile in MainApp Class.
     * 
     * @button Append HTML
     */
    // todo preferences
    @FXML
    private void handleAppendLoad() {
        FileChooser fileChooser = new FileChooser();
        
        fileChooser.setInitialDirectory(
            new File(prefHelp.pref.get("htmlDir", System.getProperty("user.dir"))));

        // Set extension filter
        FileChooser.ExtensionFilter extFilter =
            new FileChooser.ExtensionFilter("HTML Files", "*.html", "*.htm");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            prefHelp.pref.put("htmlDir",file.getParentFile().toString());
            mainApp.appendPostFromFile(file);
            if(prefHelp.pref.get("freqFile","null").compareTo("null")!=0) {
                mainApp.setFrequent(new File(prefHelp.pref.get("freqFile","")));
            }
        }
    }

    // todo complete loadFrequent
    // todo preferences
    @FXML
    private void handleLoadFrequentTxt() {
        FileChooser fileChooser = new FileChooser();
        
        fileChooser.setInitialDirectory(
            new File(prefHelp.pref.get("freqDir", System.getProperty("user.dir"))));

        // Set extension filter
        FileChooser.ExtensionFilter extFilter =
            new FileChooser.ExtensionFilter("TXT Files", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            prefHelp.pref.put("freqFile",file.toString());
            prefHelp.pref.put("freqDir",file.getParentFile().toString());
            mainApp.setFrequent(file);
        }
    }


    // todo complete save
    @FXML
    private void handleSaveAsTxt() {
        mainApp.saveTXT();
    }

    /**
     * 
     * handled in saveXML in MainApp Class.
     * 
     * @button Save as XML
     */
    @FXML
    private void handleSaveAsXml() {
        mainApp.saveXML();

    }

    /**
     * 
     * handled in showTimestampPop in MainApp Class. Will open a new dialogue controlled by
     * TimestampPopController
     * 
     * @button GenerateTimestamp
     */
    @FXML
    private void handleTime() {
        mainApp.showTimestampPop();

    }

}
