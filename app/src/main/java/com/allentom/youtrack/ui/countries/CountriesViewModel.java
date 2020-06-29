package com.allentom.youtrack.ui.countries;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.allentom.youtrack.BR;
import com.allentom.youtrack.R;
import com.allentom.youtrack.data.DataRepository;
import com.allentom.youtrack.data.entites.CountryEntity;
import com.allentom.youtrack.data.source.http.HttpDataSourceImpl;
import com.allentom.youtrack.data.source.http.service.COVID19Api;
import com.allentom.youtrack.data.source.local.LocalDataSourceImpl;
import com.allentom.youtrack.utils.RetrofitClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * @author Takayamaaren
 */
public class CountriesViewModel extends BaseViewModel<DataRepository> {
    public final ItemBinding<CountryItemViewModel> countryItemViewModelItemBinding = ItemBinding.of(BR.viewModel, R.layout.item_country);
    public final ObservableList<CountryItemViewModel> countryItemViewModels = new ObservableArrayList<>();

    public CountriesViewModel(@NonNull Application application) {
        super(application);
        COVID19Api api = RetrofitClient.getInstance().create(COVID19Api.class);
        this.model = DataRepository.getInstance(HttpDataSourceImpl.getInstance(api), LocalDataSourceImpl.getInstance(getApplication()));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        loadData();
    }

    @SuppressLint("CheckResult")
    private void loadData() {
        model.getMyCountries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    countryItemViewModels.clear();
                    for (CountryEntity countryEntity : data) {
                        countryItemViewModels.add(new CountryItemViewModel(this, countryEntity));
                    }
                });
    }
}
