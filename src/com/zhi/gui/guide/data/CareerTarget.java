package com.zhi.gui.guide.data;

public class CareerTarget {
    private int index;
    private String industry;
    private String percentage;

    public CareerTarget(int index, String industry, String percentage) {
        this.index = index;
        this.industry = industry;
        this.percentage = percentage;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

}
