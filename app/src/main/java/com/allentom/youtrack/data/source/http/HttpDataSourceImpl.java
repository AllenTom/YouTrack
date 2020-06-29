package com.allentom.youtrack.data.source.http;

import com.allentom.youtrack.data.entites.CityEntity;
import com.allentom.youtrack.data.entites.CountryEntity;
import com.allentom.youtrack.data.source.HttpDataSource;
import com.allentom.youtrack.data.source.http.service.COVID19Api;
import com.allentom.youtrack.utils.RetrofitQueryDate;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Takayamaaren
 */
public class HttpDataSourceImpl implements HttpDataSource {
    private COVID19Api apiService;
    private volatile static HttpDataSourceImpl INSTANCE = null;

    public static HttpDataSourceImpl getInstance(COVID19Api apiService) {
        if (INSTANCE == null) {
            synchronized (HttpDataSourceImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpDataSourceImpl(apiService);
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    private HttpDataSourceImpl(COVID19Api apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<SummaryResponse> fetchSummary() {
        return apiService.fetchSummary();
    }

    @Override
    public Observable<List<CityEntity>> fetchAllCountryState(String country, RetrofitQueryDate from, RetrofitQueryDate to) {
        return apiService.fetchAllCountryState(country, from, to);
    }

    @Override
    public Observable<List<CountryEntity>> fetchAllCountries() {
        return apiService.fetchAllCountries();
    }
}
