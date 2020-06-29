package com.allentom.youtrack.data.source.http.service;

import com.allentom.youtrack.data.entites.CityEntity;
import com.allentom.youtrack.data.entites.CountryEntity;
import com.allentom.youtrack.data.source.http.SummaryResponse;
import com.allentom.youtrack.utils.RetrofitQueryDate;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author Takayamaaren
 */
public interface COVID19Api {
    /**
     * fetch global summary from api
     *
     * @return response
     */
    @GET("/summary")
    Observable<SummaryResponse> fetchSummary();

    /**
     * get all province of country data
     *
     * @param country country name
     * @param from    when start
     * @param to      when end
     * @return all province data
     */
    @GET("/country/{country}")
    Observable<List<CityEntity>> fetchAllCountryState(
            @Path("country") String country,
            @Query("from") RetrofitQueryDate from,
            @Query("to") RetrofitQueryDate to
    );

    /**
     * get all country
     * <p>
     * path = [/countries]
     * @return all country
     */
    @GET("/countries")
    Observable<List<CountryEntity>> fetchAllCountries();
}
