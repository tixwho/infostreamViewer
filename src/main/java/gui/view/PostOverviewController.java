package gui.view;

import java.text.DecimalFormat;
import gui.MainApp;
import gui.model.DailyPost;
import gui.model.PostOverviewSummary;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import utils.GUIUtils;

/**
 * Controller of PostOverview (currently the panel in the middle.)
 * 
 * @author tixwho
 *
 */

public class PostOverviewController {

    @FXML
    private TableView<DailyPost> postTable;
    @FXML
    private TableColumn<DailyPost, String> usernameColumn;
    @FXML
    private TableColumn<DailyPost, Integer> postcountColumn;
    @FXML
    private TableColumn<DailyPost, Boolean> isfrequentColumn;
    @FXML
    private PieChart topPieChart = new PieChart();
    @FXML
    private PieChart freqPieChart = new PieChart();


    @FXML
    private TextField TopNumTextfield = new TextField();
    @FXML
    private Label SummaryLabel;


    ObservableList<PieChart.Data> topPieData = FXCollections.observableArrayList();
    ObservableList<PieChart.Data> freqPieData = FXCollections.observableArrayList();
    PostOverviewSummary summary = new PostOverviewSummary();

    private MainApp mainApp;


    /**
     * The constructor. The constructor is called before the initialize() method.
     */
    public PostOverviewController() {}

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        postTable.setItems(mainApp.getPostData());
    }

    public String getTopNumTextField() {
        return TopNumTextfield.getText();
    }

    public void setTopPieChart(int topN, int total) {
        topPieData.clear();
        topPieData.add(new PieChart.Data("top", topN));
        topPieData.add(new PieChart.Data("rest", total - topN));
        this.topPieChart.setData(topPieData);
    }

    public void setFreqPieChart(int freq, int infreq) {
        freqPieData.clear();
        freqPieData.add(new PieChart.Data("freq", freq));
        freqPieData.add(new PieChart.Data("infreq", infreq));
        this.freqPieChart.setData(freqPieData);
    }

    public void setSummaryLabel(ObservableList<DailyPost> postData, int topNum) {
        int totalUserCount = postData.size();
        // first check if its null
        if (totalUserCount == 0) {
            this.SummaryLabel.setText("请先载入文件！");
            return;
        } else if (topNum > totalUserCount) {
            this.SummaryLabel.setText("范围过大！请输入小于总人数的数值。");
            return;
        } else if (topNum == -1) {
            this.SummaryLabel.setText("请输入符合要求的数值！");
            return;
        }
        DecimalFormat df2 = new DecimalFormat("###.00");
        int totalPost = GUIUtils.oblistCount(postData);
        int topNPost = GUIUtils.oblistCount(postData, topNum);
        int[] prepFreq = GUIUtils.freqOblistCount(postData);
        int freqUserCount = prepFreq[0];
        int freqPost = prepFreq[1];
        int infreqPost = totalPost - freqPost;
        float topProportion = (float) topNPost / (float) totalPost;
        float freqProportion = (float) freqPost / (float) totalPost;
        this.SummaryLabel.setText(
            "在 " + totalUserCount + "位用户发送的" + totalPost + "条内容中，内容数前 " + topNum + " 的用户贡献了 "
                + topNPost + " 条，占比 " + df2.format(topProportion * 100) + "%; " + freqUserCount
                + "位常客发送 " + freqPost + "条，占比" + df2.format(freqProportion * 100) + "%");
        setTopPieChart(topNPost, totalPost);
        setFreqPieChart(freqPost, infreqPost);
        this.summary.updateAllInfo(totalUserCount, totalPost, topNum, topNPost, topProportion,
            freqUserCount, freqPost, infreqPost, freqProportion);
    }

    public PostOverviewSummary getPostOverviewSummary() {
        return this.summary;
    }

    /**
     * Initializes the controller class. This method is automatically called after the fxml file has
     * been loaded.
     */
    /*
     * 学习笔记 initialize()方法把在MainApp(FirstGUI.java)里初始化的ObservableList<Person>内的Person给注射进了
     * 两个TableColumn，只要在SceneBuilder里给对应的两个列指派这两个TableColumn就能实时更新了。
     * （疑问待确认:明明传过来的是PersonTable,怎么知道是哪个的？怀疑是看到TableColumn往上查找TableView）
     * （初步推测:cellData.getValue()是通过反射找到对应的Person Object，然后调用Person的***Property()
     * 方法，返回一个***Property(这里是simpleStringProperty)，最终分配到GUI中。
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        usernameColumn.setCellValueFactory(cellData -> cellData.getValue().USERNAMEProperty());
        postcountColumn
            .setCellValueFactory(cellData -> cellData.getValue().POSTCOUNTProperty().asObject());
        isfrequentColumn
            .setCellValueFactory(cellData -> cellData.getValue().ISFREQUENTProperty().asObject());
        // temporarily set 5.
        TopNumTextfield.textProperty().addListener((observable, oldValue, newValue) -> {
            setSummaryLabel(mainApp.getPostData(), GUIUtils.tryParse(newValue));
        });

        /*
         * // Clear person details. showPersonDetails(null);
         */

        // Listen for selection changes and show the person details when changed.
        /*
         * 学习笔记 listener记录左边选择了哪一条记录。->是Java的lambda函数
         */
        /*
         * personTable.getSelectionModel().selectedItemProperty().addListener( (observable,
         * oldValue, newValue) -> showPersonDetails(newValue));
         */
    }

}
