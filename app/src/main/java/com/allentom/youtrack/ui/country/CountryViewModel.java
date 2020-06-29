package com.allentom.youtrack.ui.country;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.allentom.youtrack.BR;
import com.allentom.youtrack.R;
import com.allentom.youtrack.data.DataRepository;
import com.allentom.youtrack.data.entites.CountrySummaryEntity;
import com.allentom.youtrack.data.entites.ProvinceEntity;
import com.allentom.youtrack.data.source.http.HttpDataSourceImpl;
import com.allentom.youtrack.data.source.http.service.COVID19Api;
import com.allentom.youtrack.data.source.local.LocalDataSourceImpl;
import com.allentom.youtrack.ui.main.ProvinceItemViewModel;
import com.allentom.youtrack.utils.RetrofitClient;
import com.allentom.youtrack.utils.RetrofitQueryDate;

import org.joda.time.DateTime;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * @author Takayamaaren
 */
public class CountryViewModel extends BaseViewModel<DataRepository> {
    private String countryQueryName;
    public ObservableField<String> country = new ObservableField<>("unknown");
    public ObservableField<String> newConfirmed = new ObservableField<>("0");
    public ObservableField<String> newRecovered = new ObservableField<>("0");
    public ObservableField<String> newDeaths = new ObservableField<>("0");
    public ObservableField<String> totalDeaths = new ObservableField<>("0");
    public ObservableField<String> totalRecovered = new ObservableField<>("0");
    public ObservableField<String> totalConfirmed = new ObservableField<>("0");

    public final ObservableList<CountryProvinceItemViewModel> provinceItemViewModels = new ObservableArrayList<>();
    public final ItemBinding<CountryProvinceItemViewModel> provinceItemViewModelItemBinding = ItemBinding.of(BR.viewModel, R.layout.item_country_province);


    public CountryViewModel(@NonNull Application application) {
        super(application);
        COVID19Api api = RetrofitClient.getInstance().create(COVID19Api.class);
        this.model = DataRepository.getInstance(HttpDataSourceImpl.getInstance(api), LocalDataSourceImpl.getInstance(getApplication()));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        loadCountryData();
        loadProvince();
    }

    @SuppressLint("CheckResult")
    private void loadCountryData() {
        model.fetchSummary()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    for (CountrySummaryEntity countrySummaryEntity : response.getCountrySummaryEntities()) {
                        if (countrySummaryEntity.getSlug().equals(this.countryQueryName)) {
                            country.set(countrySummaryEntity.getCountry());
                            newConfirmed.set("" + countrySummaryEntity.getNewConfirmed());
                            newDeaths.set("" + countrySummaryEntity.getNewDeaths());
                            newRecovered.set("" + countrySummaryEntity.getNewRecovered());
                            totalConfirmed.set("" + countrySummaryEntity.getTotalConfirmed());
                            totalRecovered.set("" + countrySummaryEntity.getTotalRecovered());
                            totalDeaths.set("" + countrySummaryEntity.getTotalDeaths());

                        }
                    }
                }, Throwable::printStackTrace);
    }

    @SuppressLint("CheckResult")
    private void loadProvince(){
        DateTime startTime = DateTime.now().minusDays(2).withTimeAtStartOfDay();
        DateTime endTime = DateTime.now().minusDays(1).withTimeAtStartOfDay();
        model.fetchAllCountryState(this.countryQueryName,new RetrofitQueryDate(startTime),new RetrofitQueryDate(endTime))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cityEntities -> {
                    this.provinceItemViewModels.clear();
                    List<ProvinceEntity> provinceEntities = ProvinceEntity.fromCityList(cityEntities);
                    for (ProvinceEntity entity : provinceEntities) {
                        this.provinceItemViewModels.add(new CountryProvinceItemViewModel(this, entity));
                    }
                },Throwable::printStackTrace);
    }
    public void setCountryQueryName(String countryQueryName) {
        this.countryQueryName = countryQueryName;
    }
}
