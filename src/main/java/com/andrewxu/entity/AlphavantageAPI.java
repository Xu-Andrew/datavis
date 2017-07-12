package com.andrewxu.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Created by chrysaora on 6/27/17.
 */
public class AlphavantageAPI {

    @SerializedName("Meta Data")
    private MetaData metaData;

    @SerializedName("Time Series (Daily)")
    private Map<String, Map<String, String>> timeSeries;

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public Map<String, Map<String, String>> getTimeSeries() {
        return timeSeries;
    }

    public void setTimeSeries(Map<String, Map<String, String>> timeSeries) {
        this.timeSeries = timeSeries;
    }

    @Override
    public String toString() {
        return "ClassPojo [metaData=" + metaData + ", timeSeries=" + timeSeries + "]";
    }
}
