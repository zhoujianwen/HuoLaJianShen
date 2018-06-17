package cn.zhoujianwen.huolajianshen.bean;

/**
 * Created by zhoujianwen.cn on 2018-05-28.
 */
public class SlidingDeckModel {


    private String slidingName;
    private String slidingTime;
    private double aloneCC;
    private double gram;
    private double totalCC;

    public String getSlidingTime() {
        return slidingTime;
    }

    public void setSlidingTime(String slidingTime) {
        this.slidingTime = slidingTime;
    }

    public String getSlidingName() {
        return slidingName;
    }

    public double getAloneCC() {
        return aloneCC;
    }

    public void setAloneCC(double aloneCC) {
        this.aloneCC = aloneCC;
    }

    public double getGram() {
        return gram;
    }

    public void setGram(double gram) {
        this.gram = gram;
    }

    public double getTotalCC() {
        return totalCC;
    }

    public void setTotalCC(double totalCC) {
        this.totalCC = totalCC;
    }

    public void setSlidingName(String slidingName) {
        this.slidingName = slidingName;
    }


}
