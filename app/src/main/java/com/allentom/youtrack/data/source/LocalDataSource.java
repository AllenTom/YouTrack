package com.allentom.youtrack.data.source;

import com.allentom.youtrack.data.entites.CountryEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface LocalDataSource {
    Completable saveAllCountry(CountryEntity... entities);

    Observable<List<CountryEntity>> getAllCountryList();

    Completable updateAllCountry(CountryEntity... entities);

    String getHomeCountryName();

    void saveHomeCountryName(String name);

    Observable<List<CountryEntity>> getMyCountries();

    Completable addMyCountries(CountryEntity... entities);
    Completable removeMyCountries(CountryEntity... entities);
}
