package com.allentom.youtrack.data.source;

import com.allentom.youtrack.data.entites.CityEntity;
import com.allentom.youtrack.data.entites.CountryEntity;
import com.allentom.youtrack.data.source.http.SummaryResponse;
import com.allentom.youtrack.utils.RetrofitQueryDate;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Takayamaaren
 */
public interface HttpDataSource {
    /**
     * get world case summary
     * @return summary response
     */
    Observable<SummaryResponse> fetchSummary();

    /**
     * get all province of country data
     *
     * @param country country name
     * @param from    when start
     * @param to      when end
     * @return all province data
     */
    Observable<List<CityEntity>> fetchAllCountryState(String country, RetrofitQueryDate from, RetrofitQueryDate to);

    /**
     * get all country
     * <p>
     * path = [/countries]
     * @return all country
     */
    Observable<List<CountryEntity>> fetchAllCountries();
}
