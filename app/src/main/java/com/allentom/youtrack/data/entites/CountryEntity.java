package com.allentom.youtrack.data.entites;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * @author Takayamaren
 */
@Entity(tableName = "country")
public class CountryEntity {


    @PrimaryKey(autoGenerate = true)
    private int id;
    /**
     * Country : Poland
     * Slug : poland
     * ISO2 : PL
     */
    @ColumnInfo(name = "country")
    @SerializedName("Country")
    private String country;
    @ColumnInfo(name = "slug")
    @SerializedName("Slug")
    private String slug;
    @ColumnInfo(name = "iso2")
    @SerializedName("ISO2")
    private String iso2;

    @ColumnInfo(name = "follow")
    private Boolean follow = false;

    public void setCountry(String country) {
        this.country = country;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public String getCountry() {
        return country;
    }

    public String getSlug() {
        return slug;
    }

    public String getIso2() {
        return iso2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getFollow() {
        return follow;
    }

    public void setFollow(Boolean follow) {
        this.follow = follow;
    }
}
