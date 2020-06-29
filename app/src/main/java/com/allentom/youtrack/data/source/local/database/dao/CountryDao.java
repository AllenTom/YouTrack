package com.allentom.youtrack.data.source.local.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.allentom.youtrack.data.entites.CountryEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;

@Dao
public interface CountryDao {
    @Insert
    Completable insertAll(CountryEntity... entities);

    @Query("select * from country")
    Observable<List<CountryEntity>> getAllCountryList();

    @Query("delete from country")
    Completable deleteAll();

    @Query("select * from country where follow = 1")
    Observable<List<CountryEntity>> getFollowedCountry();

    @Query("update country set follow = 1 where country.slug in (:countrySlugs)")
    Completable addFollowedCountry(String... countrySlugs);

    @Query("update country set follow = 0 where country.slug in (:countrySlugs)")
    Completable removeFollowedCountry(String... countrySlugs);

    @Query("select * from country where slug in (:countrySlugs)")
    Observable<List<CountryEntity>> getCountryByCountrySlugs(String... countrySlugs);
}
