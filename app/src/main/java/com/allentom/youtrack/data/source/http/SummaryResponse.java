package com.allentom.youtrack.data.source.http;

import com.allentom.youtrack.data.entites.CountrySummaryEntity;
import com.allentom.youtrack.data.entites.GlobalSummaryEntity;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * @author Takayamaaren
 */
public class SummaryResponse {
    @SerializedName("Global")
    private GlobalSummaryEntity global;

    @SerializedName("Countries")
    private List<CountrySummaryEntity> countrySummaryEntities;

    @SerializedName("Date")
    private Date date;

    public GlobalSummaryEntity getGlobal() {
        return global;
    }

    public void setGlobal(GlobalSummaryEntity global) {
        this.global = global;
    }

    public List<CountrySummaryEntity> getCountrySummaryEntities() {
        return countrySummaryEntities;
    }

    public void setCountrySummaryEntities(List<CountrySummaryEntity> countrySummaryEntities) {
        this.countrySummaryEntities = countrySummaryEntities;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
