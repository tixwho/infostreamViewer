package gui.model;

import java.text.DecimalFormat;

public class PostOverviewSummary {
    private int totalUserCount;
    private int totalPostCount;
    private int topNConfigure;
    private int topNPostCount;
    private float topNProportion;
    private int freqUserCount;
    private int freqPostCount;
    private int infreqPostCount;
    private float freqPostProportion;

    public void updateAllInfo(int totalUser, int totalPost, int topNConf, int topNPost,
        float topNProp,int freqUser, int freqPost, int infreqPost, float freqProp) {
        this.totalUserCount = totalUser;
        this.totalPostCount = totalPost;
        this.topNConfigure = topNConf;
        this.topNPostCount = topNPost;
        this.topNProportion = topNProp;
        this.freqUserCount = freqUser;
        this.freqPostCount = freqPost;
        this.infreqPostCount = infreqPost;
        this.freqPostProportion = freqProp;

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

    public int getFreqUserCount() {
        return freqUserCount;
    }

    public void setFreqUserCount(int freqUserCount) {
        this.freqUserCount = freqUserCount;
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

    public int getFreqPostCount() {
        return freqPostCount;
    }

    public void setFreqPostCount(int freqPostCount) {
        this.freqPostCount = freqPostCount;
    }

    public int getInfreqPostCount() {
        return infreqPostCount;
    }

    public void setInfreqPostCount(int infreqPostCount) {
        this.infreqPostCount = infreqPostCount;
    }

    public float getFreqPostProportion() {
        return freqPostProportion;
    }

    public void setFreqPostProportion(float infreqPostProportion) {
        this.freqPostProportion = infreqPostProportion;
    }
    
    public String getFreqProportionAsText() {
        DecimalFormat df2 = new DecimalFormat("###.00");
        return df2.format(this.freqPostProportion * 100) + "%";
    }

}
