package com.allentom.youtrack.data.source.local;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;

import com.allentom.youtrack.data.entites.CountryEntity;
import com.allentom.youtrack.data.source.LocalDataSource;
import com.allentom.youtrack.data.source.local.database.AppDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.goldze.mvvmhabit.utils.SPUtils;

public class LocalDataSourceImpl implements LocalDataSource {
    public static final String HOME_COUNTRY_NAME_KEY = "HomeCountry";
    public static final String DEFAULT_HOME_COUNTRY_SLUG = "china";
    private volatile static LocalDataSourceImpl INSTANCE = null;
    private AppDatabase db;

    public static LocalDataSourceImpl getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (LocalDataSourceImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LocalDataSourceImpl(context);
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    private LocalDataSourceImpl(Context context) {
        this.db = AppDatabase.getAppDatabase(context);
    }

    @Override
    public Completable saveAllCountry(CountryEntity... entities) {
        return db.countryDao().insertAll(entities);
    }

    @Override
    public Observable<List<CountryEntity>> getAllCountryList() {
        return db.countryDao().getAllCountryList();
    }

    @Override
    public Completable updateAllCountry(CountryEntity... entities) {
        return db.countryDao()
                .deleteAll()
                .andThen(db.countryDao().insertAll(entities));
    }

    @Override
    @NonNull
    public String getHomeCountryName() {
        String countrySlug = SPUtils.getInstance().getString(HOME_COUNTRY_NAME_KEY);
        if (countrySlug == null || countrySlug.length() == 0) {
            return DEFAULT_HOME_COUNTRY_SLUG;
        }
        return countrySlug;
    }

    @Override
    public void saveHomeCountryName(String name) {
        SPUtils.getInstance().put(HOME_COUNTRY_NAME_KEY, name);
    }

    @Override
    public Observable<List<CountryEntity>> getMyCountries() {
        return db.countryDao().getFollowedCountry();
    }

    @SuppressLint("CheckResult")
    @Override
    public Completable addMyCountries(CountryEntity... entities) {
        List<String> addCountrySlugs = new ArrayList<String>();
        for (CountryEntity entity : entities) {
            addCountrySlugs.add(entity.getSlug());
        }
        return db.countryDao()
                .getCountryByCountrySlugs(addCountrySlugs.toArray(new String[0]))
                .flatMap(existedCountry -> {
                    List<CountryEntity> entityListToAdd = new LinkedList<CountryEntity>(Arrays.asList(entities));
                    for (CountryEntity entity : entityListToAdd) {
                        for (CountryEntity existEntity : existedCountry) {
                            if (existEntity.getSlug().equals(entity.getSlug())) {
                                entityListToAdd.remove(entity);
                            }
                        }
                    }
                    return Observable.just(entityListToAdd);
                })
                .doOnNext(data -> db.countryDao().insertAll(data.toArray(new CountryEntity[0])).blockingAwait())
                .doOnNext(data -> db.countryDao().addFollowedCountry(addCountrySlugs.toArray(new String[0])).blockingAwait())
                .ignoreElements();
    }

    @Override
    public Completable removeMyCountries(CountryEntity... entities) {
        List<String> addCountrySlugs = new ArrayList<String>();
        for (CountryEntity entity : entities) {
            addCountrySlugs.add(entity.getSlug());
        }
        return db.countryDao().removeFollowedCountry(addCountrySlugs.toArray(new String[0]));
    }
}
