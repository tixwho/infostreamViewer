package writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import gui.model.DailyPost;
import gui.model.PostOverviewSummary;
import javafx.collections.ObservableList;

public class XMLwriter {
    private Document document;
    private PostOverviewSummary summary;
    private ObservableList<DailyPost> postData;
    
    public XMLwriter (PostOverviewSummary summary, ObservableList<DailyPost> postData) {
        this.summary = summary;
        this.postData = postData;
        
    }
    
    public int writeAnXML() {
        int statusCode = 0;
        prepareAnXML();
        
            OutputFormat format = OutputFormat.createPrettyPrint();
            File f = new File("summary.xml");
            XMLWriter writer;
            try {
                writer = new XMLWriter(new FileOutputStream(f), format);
                writer.setEscapeText(false);
                writer.write(document);
                writer.close();
            } catch (UnsupportedEncodingException | FileNotFoundException e) {
                e.printStackTrace();
                statusCode = -1;
                return statusCode;
                
            } catch (IOException e) {
                e.printStackTrace();
                statusCode = -1;
                return statusCode;
            }


            return 1;
        

        
    }
    
    private void prepareAnXML() {
        document = DocumentHelper.createDocument();
        //set up overview part;
        Element root = document.addElement("Root");
        Element overview = root.addElement("Overview");
        Element totalUserCount = overview.addElement("TotalUserCount");
        totalUserCount.setText(String.valueOf(summary.getTotalUserCount()));
        Element totalPostCount = overview.addElement("TotalPostCount");
        totalPostCount.setText(String.valueOf(summary.getTotalPostCount()));
        Element topNPost = overview.addElement("TopNPostInfo");
        topNPost.addAttribute("topN", String.valueOf(summary.getTopNConfigure()));
        Element topNPostCount = topNPost.addElement("TopNPostCount");
        topNPostCount.setText(String.valueOf(summary.getTopNPostCount()));
        Element topNPostProportion = topNPost.addElement("TopNPostProportion");
        topNPostProportion.setText(summary.getTopNProportionAsText());
        //set up counting part;
        Element usercount = root.addElement("DetailedUserCount");
        Iterator<DailyPost> postCountIter = postData.iterator();
        while (postCountIter.hasNext()){
            DailyPost thisPost = postCountIter.next();
            Element userpost = usercount.addElement("UserPost");
            Element username = userpost.addElement("Username");
            username.setText(thisPost.getUSERNAME());
            Element postcount = userpost.addElement("PostCount");
            postcount.setText(String.valueOf(thisPost.getPOSTCOUNT()));
        }
        
    }

}
