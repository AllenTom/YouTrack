package com.allentom.youtrack.data.entites;

import com.google.gson.annotations.SerializedName;

/**
 * @author Takayamaaren
 */
public class CountrySummaryEntity implements java.io.Serializable {
    private static final long serialVersionUID = 199421718112981050L;
    @SerializedName("NewRecovered")
    private int newRecovered;
    @SerializedName("NewDeaths")
    private int newDeaths;
    @SerializedName("TotalRecovered")
    private int totalRecovered;
    @SerializedName("TotalConfirmed")
    private int totalConfirmed;
    @SerializedName("Country")
    private String country;
    @SerializedName("CountryCode")
    private String countryCode;
    @SerializedName("Slug")
    private String slug;
    @SerializedName("NewConfirmed")
    private int newConfirmed;
    @SerializedName("TotalDeaths")
    private int totalDeaths;
    @SerializedName("Date")
    private String date;

    public int getNewRecovered() {
        return this.newRecovered;
    }

    public void setNewRecovered(int NewRecovered) {
        this.newRecovered = NewRecovered;
    }

    public int getNewDeaths() {
        return this.newDeaths;
    }

    public void setNewDeaths(int NewDeaths) {
        this.newDeaths = NewDeaths;
    }

    public int getTotalRecovered() {
        return this.totalRecovered;
    }

    public void setTotalRecovered(int TotalRecovered) {
        this.totalRecovered = TotalRecovered;
    }

    public int getTotalConfirmed() {
        return this.totalConfirmed;
    }

    public void setTotalConfirmed(int TotalConfirmed) {
        this.totalConfirmed = TotalConfirmed;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String Country) {
        this.country = Country;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String CountryCode) {
        this.countryCode = CountryCode;
    }

    public String getSlug() {
        return this.slug;
    }

    public void setSlug(String Slug) {
        this.slug = Slug;
    }

    public int getNewConfirmed() {
        return this.newConfirmed;
    }

    public void setNewConfirmed(int NewConfirmed) {
        this.newConfirmed = NewConfirmed;
    }

    public int getTotalDeaths() {
        return this.totalDeaths;
    }

    public void setTotalDeaths(int TotalDeaths) {
        this.totalDeaths = TotalDeaths;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String Date) {
        this.date = Date;
    }
}
