package gui;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import gui.model.DailyPost;
import gui.view.PostOverviewController;
import gui.view.RootLayoutController;
import gui.view.TimestampPopController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import reader.StreamUserReader;
import utils.CountUtils;
import utils.GUIUtils;
import writer.XMLwriter;

public class MainApp extends Application {
    /**
     * atomicInteger：用于统计用户单击按钮的次数
     */
    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<DailyPost> postData = FXCollections.observableArrayList();

    public RootLayoutController Rcontroller;
    public PostOverviewController Pcontroller;


    public MainApp() {
        /*
         * postData.add(new DailyPost("wac",11)); postData.add(new DailyPost("Sta",10));
         * postData.add(new DailyPost("cow",9)); postData.add(new DailyPost("Sta",8));
         * postData.add(new DailyPost("Sta",7)); postData.add(new DailyPost("Sta",6));
         * postData.add(new DailyPost("Sta",5)); postData.add(new DailyPost("Sta",4));
         * postData.add(new DailyPost("Sta",3)); postData.add(new DailyPost("Sta",2));
         * postData.add(new DailyPost("Sta",2)); postData.add(new DailyPost("Sta",1));
         */
    }

    /* Getter */

    public ObservableList<DailyPost> getPostData() {
        return this.postData;
    }



    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /* Methods */
    public void loadPostFromFile(File file) {
        try {
            this.postData.clear();
            StreamUserReader rd = new StreamUserReader(file);
            List<String> allPostCountList = rd.getPostUserList();
            List<Map.Entry<String, Integer>> orderedFreqList =
                CountUtils.orderedFreqList(CountUtils.sortUserFrequency(allPostCountList));
            int userCount = orderedFreqList.size();
            for (int i = 0; i < userCount; i++) {
                this.postData.add(new DailyPost(orderedFreqList.get(i).getKey(),
                    orderedFreqList.get(i).getValue()));
            }
        } catch (IOException e) {
            System.out.println("IOE!");
            e.printStackTrace();
            return;
        }
        Pcontroller.setSummaryLabel(postData, 5);

    }
    
    public void appendPostFromFile(File file) {
        
        try {
            StreamUserReader rd = new StreamUserReader(file);
            List<String> allPostCountList = rd.getPostUserList();
            List<Map.Entry<String, Integer>> orderedFreqList =
                CountUtils.orderedFreqList(CountUtils.sortUserFrequency(allPostCountList));
            int userCount = orderedFreqList.size();
            // collect into a map for index
            HashMap<String,DailyPost> currentMap = new HashMap<String,DailyPost>();
            for(DailyPost iPost:postData) {
                currentMap.put(iPost.getUSERNAME(),iPost);
            }
            // if contained in the map, add to count; if not, add a new object.
            for (int i = 0; i < userCount; i++) {
                String userQuerying = orderedFreqList.get(i).getKey();
                int userQueryingCount = orderedFreqList.get(i).getValue();
                if(currentMap.containsKey(userQuerying)) {
                    DailyPost checkPost = currentMap.get(userQuerying);
                    checkPost.setPOSTCOUNT(checkPost.getPOSTCOUNT()+userQueryingCount);
                }else {
                    currentMap.put(userQuerying,new DailyPost(userQuerying, userQueryingCount));
                }
                
            }
            //clear old observable list
            postData.clear();
            currentMap.forEach((k,v)->postData.add(v));
            CountUtils.sortObservableArrList(postData);
        } catch (IOException e) {
            System.out.println("IOE!");
            e.printStackTrace();
            return;
        }
        Pcontroller.setSummaryLabel(postData, 5);

    }

    public void saveXML() {
        XMLwriter writer = new XMLwriter(Pcontroller.getPostOverviewSummary(), postData);
        int status = writer.writeAnXML();
        if (status != 1) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(this.getPrimaryStage());
            alert.setTitle("Error Saving");
            alert.setHeaderText("Fatal Error in saving!");
            alert.setContentText("Could not save data to xml file!");
            alert.showAndWait();
        } else if (status == 1) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.initOwner(this.getPrimaryStage());
            alert.setTitle("Saved!");
            alert.setHeaderText("Saving complete!");
            alert.setContentText("Information saved in summary.xml");
            alert.showAndWait();
        }
    }

    public void saveTXT() {
        GUIUtils.showMaintenance(this.getPrimaryStage());
    }

    /* Initializing */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            Rcontroller = loader.getController();
            Rcontroller.setMainApp(this);


            primaryStage.show();

            /*
             * // My test: adding functions to menu. // Result: Passing a stage is ok, but can not
             * pass controller (NPE) // Reason: primaryStage can't be show before passing into
             * controller. RootLayoutController controller= loader.getController();
             * controller.setMainStage(this.primaryStage);
             */
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Try to load last opened person file.
        /*
         * Temporarily Disabled File file = getPersonFilePath(); if (file != null) {
         * loadPersonDataFromFile(file); }
         */
    }

    public void showPostOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PostOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app

            Pcontroller = loader.getController();
            Pcontroller.setMainApp(this);
            Pcontroller.setSummaryLabel(postData, 5);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showTimestampPop() {
        GUIUtils.showMaintenance(this.getPrimaryStage(),
            "Notice:Timestamp is not always reliable. Please check HTML input manually.");
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/TimestampPop.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            TimestampPopController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();


        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    /**
     * 2、然后实现的它的start抽象方法
     *
     * @param primaryStage
     */
    @Override
    /**
     * 学习笔记 必须通过start来override initialization时的动作
     */
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("PostCounter");

        initRootLayout();

        showPostOverview();

    }

    /**
     * 学习笔记 Stage-->stackPane-->Controller-->Action of controller
     */
    public static void main(String[] args) {
        /**
         * GUI程序必须从入口的main方法进入并启动 launch是Application中的，调用它则可启动此GUI程序了
         */
        launch(args);
    }
}
