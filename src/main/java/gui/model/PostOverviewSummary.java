package gui.model;

import java.text.DecimalFormat;

public class PostOverviewSummary {
    private int totalUserCount;
    private int totalPostCount;
    private int topNConfigure;
    private int topNPostCount;
    private float topNProportion;
    
    public void updateAllInfo(int totalUser, int totalPost, int topNConf, int topNPost, float topNProp) {
        this.totalUserCount = totalUser;
        this.totalPostCount = totalPost;
        this.topNConfigure = topNConf;
        this.topNPostCount = topNPost;
        this.topNProportion = topNProp;
    }
    public int getTotalUserCount() {
        return totalUserCount;
    }
    public void setTotalUserCount(int totalUserCount) {
        this.totalUserCount = totalUserCount;
    }
    public int getTotalPostCount() {
        return totalPostCount;
    }
    public void setTotalPostCount(int totalPostCount) {
        this.totalPostCount = totalPostCount;
    }
    public int getTopNConfigure() {
        return topNConfigure;
    }
    public void setTopNConfigure(int topNConfigure) {
        this.topNConfigure = topNConfigure;
    }
    public int getTopNPostCount() {
        return topNPostCount;
    }
    public void setTopNPostCount(int topNPostCount) {
        this.topNPostCount = topNPostCount;
    }
    public float getTopNProportion() {
        return topNProportion;
    }
    public void setTopNProportion(float topNProportion) {
        this.topNProportion = topNProportion;
    }
    public String getTopNProportionAsText() {
        DecimalFormat df2 = new DecimalFormat("###.00");
        return df2.format(this.topNProportion * 100) + "%";
    }

}
