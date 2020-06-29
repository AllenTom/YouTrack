package com.allentom.youtrack.ui.main;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.allentom.youtrack.BR;
import com.allentom.youtrack.R;
import com.allentom.youtrack.data.DataRepository;
import com.allentom.youtrack.data.entites.CountrySummaryEntity;
import com.allentom.youtrack.data.entites.CityEntity;
import com.allentom.youtrack.data.entites.ProvinceEntity;
import com.allentom.youtrack.data.source.http.HttpDataSourceImpl;
import com.allentom.youtrack.data.source.http.service.COVID19Api;
import com.allentom.youtrack.data.source.local.LocalDataSourceImpl;
import com.allentom.youtrack.message.MessageTypes;
import com.allentom.youtrack.utils.RetrofitClient;
import com.allentom.youtrack.utils.RetrofitQueryDate;

import org.joda.time.DateTime;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.Messenger;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * @author Takayamaaren
 */
public class MainViewModel extends BaseViewModel<DataRepository> {
    public ObservableField<String> newConfirm = new ObservableField<>("0");
    public ObservableField<String> confirm = new ObservableField<>("0");
    public ObservableField<String> newRecovered = new ObservableField<>("0");
    public ObservableField<String> recovered = new ObservableField<>("0");
    public ObservableField<String> newDeaths = new ObservableField<>("0");
    public ObservableField<String> deaths = new ObservableField<>("0");
    public ObservableField<String> country = new ObservableField<>("");

    public final ObservableList<ProvinceItemViewModel> provinceItemViewModels = new ObservableArrayList<>();
    public final ItemBinding<ProvinceItemViewModel> provinceItemViewModelItemBinding = ItemBinding.of(BR.viewModel, R.layout.item_state);

    @Override
    public void onCreate() {
        super.onCreate();
        Messenger.getDefault().register(this,MessageTypes.HOME_DISPLAY_COUNTRY_UPDATE, this::loadData);
        COVID19Api api = RetrofitClient.getInstance().create(COVID19Api.class);
        this.model = DataRepository.getInstance(HttpDataSourceImpl.getInstance(api), LocalDataSourceImpl.getInstance(getApplication()));
        loadData();
    }

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @SuppressLint("CheckResult")
    private void loadData() {
        model.fetchSummary()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            Toast.makeText(getApplication(), "获取成功", Toast.LENGTH_SHORT).show();
                            Log.d("Fetch Summary", "size = " + response.getCountrySummaryEntities().size());
                            List<CountrySummaryEntity> countrySummaryEntities = response.getCountrySummaryEntities();
                            for (CountrySummaryEntity countrySummaryEntity : countrySummaryEntities) {
                                if (model.getHomeCountryName().equals(countrySummaryEntity.getSlug())) {
                                    this.confirm.set("" + countrySummaryEntity.getTotalConfirmed());
                                    this.newConfirm.set("" + countrySummaryEntity.getNewConfirmed());
                                    this.newRecovered.set("" + countrySummaryEntity.getNewRecovered());
                                    this.recovered.set("" + countrySummaryEntity.getTotalRecovered());
                                    this.deaths.set("" + countrySummaryEntity.getTotalDeaths());
                                    this.newDeaths.set("" + countrySummaryEntity.getNewDeaths());
                                    this.country.set("" + countrySummaryEntity.getCountry());
                                }
                            }
                        },
                        Throwable::printStackTrace
                );
        DateTime startTime = DateTime.now().minusDays(2).withTimeAtStartOfDay();
        DateTime endTime = DateTime.now().minusDays(1).withTimeAtStartOfDay();
        Log.d("Fetch Data", "Start  = " + startTime.toString() + "   endTime = " + endTime.toString());
        model.fetchAllCountryState(model.getHomeCountryName(), new RetrofitQueryDate(startTime), new RetrofitQueryDate(endTime))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            this.provinceItemViewModels.clear();
                            List<ProvinceEntity> provinceEntities = ProvinceEntity.fromCityList(response);
                            for (ProvinceEntity entity : provinceEntities) {
                                this.provinceItemViewModels.add(new ProvinceItemViewModel(this, entity));
                            }
                        },
                        Throwable::printStackTrace
                );
    }

    public BindingCommand onHandleRefresh = new BindingCommand(this::loadData);
}
