package com.allentom.youtrack.ui.search;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.allentom.youtrack.BR;
import com.allentom.youtrack.R;
import com.allentom.youtrack.data.DataRepository;
import com.allentom.youtrack.data.entites.CountryEntity;
import com.allentom.youtrack.data.source.http.HttpDataSourceImpl;
import com.allentom.youtrack.data.source.http.service.COVID19Api;
import com.allentom.youtrack.data.source.local.LocalDataSourceImpl;
import com.allentom.youtrack.message.MessageTypes;
import com.allentom.youtrack.utils.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.bus.Messenger;
import me.goldze.mvvmhabit.utils.ToastUtils;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * @author Takayamaaren
 */
public class SearchViewModel extends BaseViewModel<DataRepository> {
    public final ObservableList<SearchCountryItemViewModel> searchCountryItemViewModels = new ObservableArrayList<>();
    public final ObservableField<String> searchKey = new ObservableField<>("");
    public final ItemBinding<SearchCountryItemViewModel> searchCountryItemViewModelItemBinding = ItemBinding.of(BR.viewModel, R.layout.item_search_country);
    private final List<CountryEntity> countryEntityList = new ArrayList<>();

    private void onFetchCountryListSuccess(List<CountryEntity> entities) {
        this.countryEntityList.clear();
        this.countryEntityList.addAll(entities);
        for (CountryEntity countryEntity : entities) {
            searchCountryItemViewModels.add(new SearchCountryItemViewModel(this, countryEntity));
        }
    }

//    @SuppressLint("CheckResult")
//    private void updateCountryInDatabase(List<CountryEntity> entities) {
//        this.model.updateAllCountry(entities.toArray(new CountryEntity[0]))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(() -> {
//                    Log.d("Data", "refresh local country list database done !");
//                }, Throwable::printStackTrace);
//    }
//
//    @SuppressLint("CheckResult")
//    private void fetchCountryListWithNetwork() {
//        model.fetchAllCountries()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(countryEntities -> {
//                    Log.d("Data", "Fetch data from network done !");
//                    updateCountryInDatabase(countryEntities);
//                    onFetchCountryListSuccess(countryEntities);
//                }, Throwable::printStackTrace);
//    }

    @SuppressLint("CheckResult")
    private void loadAllCountries() {
        searchCountryItemViewModels.clear();
        model.getCountries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onFetchCountryListSuccess, Throwable::printStackTrace);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        loadAllCountries();
    }

    public SearchViewModel(@NonNull Application application) {
        super(application);
        COVID19Api api = RetrofitClient.getInstance().create(COVID19Api.class);
        this.model = DataRepository.getInstance(HttpDataSourceImpl.getInstance(api), LocalDataSourceImpl.getInstance(getApplication()));
    }

    public void onSearchInputChange(CharSequence s, int start, int before, int count) {
        Log.d("Search Key input change", s.toString());
    }

    public void onSearchClick(View view) {
        searchCountryItemViewModels.clear();
        String key = searchKey.get();
        if (key == null) {
            return;
        }
        for (CountryEntity countryEntity : countryEntityList) {
            if (
                    countryEntity.getCountry().contains(key) ||
                            countryEntity.getCountry().toLowerCase().contains(key) ||
                            countryEntity.getIso2().contains(key) ||
                            countryEntity.getIso2().toLowerCase().contains(key)
            ) {
                searchCountryItemViewModels.add(new SearchCountryItemViewModel(this, countryEntity));
            }
        }
    }

    public void setHomeDisplayCountry(String name) {
        model.saveHomeCountryName(name);
        Messenger.getDefault().sendNoMsg(MessageTypes.HOME_DISPLAY_COUNTRY_UPDATE);
    }

    @SuppressLint("CheckResult")
    public void addToFollowCountry(CountryEntity entity) {
        model.addMyCountries(entity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((disposable) -> ToastUtils.showShort("已添加" + entity.getCountry()))
                .subscribe();
    }
}
