package com.allentom.youtrack.data;

import android.util.Log;

import androidx.annotation.NonNull;

import com.allentom.youtrack.data.entites.CityEntity;
import com.allentom.youtrack.data.entites.CountryEntity;
import com.allentom.youtrack.data.source.HttpDataSource;
import com.allentom.youtrack.data.source.LocalDataSource;
import com.allentom.youtrack.data.source.http.SummaryResponse;
import com.allentom.youtrack.utils.RetrofitQueryDate;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import me.goldze.mvvmhabit.base.BaseModel;

/**
 * @author Takayamaaren
 */
public class DataRepository extends BaseModel implements HttpDataSource, LocalDataSource {
    private volatile static DataRepository INSTANCE = null;
    private final HttpDataSource mHttpDataSource;
    private final LocalDataSource mLocalDataSource;

    private DataRepository(@NonNull HttpDataSource httpDataSource, @NonNull LocalDataSource localDataSource) {
        this.mHttpDataSource = httpDataSource;
        this.mLocalDataSource = localDataSource;
    }

    public static DataRepository getInstance(HttpDataSource httpDataSource, LocalDataSource localDataSource) {
        if (INSTANCE == null) {
            synchronized (DataRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DataRepository(httpDataSource, localDataSource);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Observable<SummaryResponse> fetchSummary() {
        return mHttpDataSource.fetchSummary();
    }

    @Override
    public Observable<List<CityEntity>> fetchAllCountryState(String country, RetrofitQueryDate from, RetrofitQueryDate to) {
        return mHttpDataSource.fetchAllCountryState(country, from, to);
    }

    @Override
    public Observable<List<CountryEntity>> fetchAllCountries() {
        return mHttpDataSource.fetchAllCountries();
    }

    @Override
    public Completable saveAllCountry(CountryEntity... entities) {
        return mLocalDataSource.saveAllCountry(entities);
    }

    @Override
    public Observable<List<CountryEntity>> getAllCountryList() {
        return mLocalDataSource.getAllCountryList();
    }

    public Observable<List<CountryEntity>> getCountries() {
        return mHttpDataSource.fetchAllCountries();
    }

    @Override
    public Completable updateAllCountry(CountryEntity... entities) {
        return mLocalDataSource.updateAllCountry(entities);
    }

    @Override
    public String getHomeCountryName() {
        return mLocalDataSource.getHomeCountryName();
    }

    @Override
    public void saveHomeCountryName(String name) {
        mLocalDataSource.saveHomeCountryName(name);
    }

    @Override
    public Observable<List<CountryEntity>> getMyCountries() {
        return mLocalDataSource.getMyCountries();
    }

    @Override
    public Completable addMyCountries(CountryEntity... entities) {
        return mLocalDataSource.addMyCountries(entities);
    }

    @Override
    public Completable removeMyCountries(CountryEntity... entities) {
        return mLocalDataSource.removeMyCountries(entities);
    }
}
