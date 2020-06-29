package com.allentom.youtrack.data.entites;

import com.google.gson.annotations.SerializedName;

/**
 * @author Takayamaaren
 */
public class CityEntity {

    /**
     * CityCode :
     * Recovered : 68
     * Active : 346
     * Country : China
     * Deaths : 11
     * Lon : 127.76
     * City :
     * Confirmed : 425
     * CountryCode : CN
     * Province : Heilongjiang
     * Lat : 47.86
     * Date : 2020-02-15T00:00:00Z
     */
    @SerializedName("CityCode")
    private String cityCode;
    @SerializedName("Recovered")
    private int recovered;
    @SerializedName("Active")
    private int active;
    @SerializedName("Country")
    private String country;
    @SerializedName("Deaths")
    private int deaths;
    @SerializedName("Lon")
    private String lon;
    @SerializedName("City")
    private String city;
    @SerializedName("Confirmed")
    private int confirmed;
    @SerializedName("CountryCode")
    private String countryCode;
    @SerializedName("Province")
    private String province;
    @SerializedName("Lat")
    private String lat;
    @SerializedName("Date")
    private String date;

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCityCode() {
        return cityCode;
    }

    public int getRecovered() {
        return recovered;
    }

    public int getActive() {
        return active;
    }

    public String getCountry() {
        return country;
    }

    public int getDeaths() {
        return deaths;
    }

    public String getLon() {
        return lon;
    }

    public String getCity() {
        return city;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getProvince() {
        return province;
    }

    public String getLat() {
        return lat;
    }

    public String getDate() {
        return date;
    }
}
