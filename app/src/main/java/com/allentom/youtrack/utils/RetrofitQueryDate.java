package com.allentom.youtrack.utils;

import androidx.annotation.NonNull;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * @author Format requets date
 */
public class RetrofitQueryDate {
    private Date date;

    public RetrofitQueryDate(Date date) {
        this.date = date;
    }

    public RetrofitQueryDate(DateTime dateTime) {
        this.date = dateTime.toDate();
    }

    @NonNull
    @Override
    public String toString() {
        return new DateTime(this.date).toString("yyyy-MM-dd'T'HH:mm:ss'Z'");
    }
}
