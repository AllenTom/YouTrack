package com.allentom.youtrack.ui.country;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.allentom.youtrack.data.entites.ProvinceEntity;
import com.allentom.youtrack.ui.main.MainViewModel;

import me.goldze.mvvmhabit.base.ItemViewModel;

/**
 * @author Takayamaaren
 */
public class CountryProvinceItemViewModel extends ItemViewModel<CountryViewModel> {
    public ObservableField<String> confirm = new ObservableField<>("0");
    public ObservableField<String> deaths = new ObservableField<>("0");
    public ObservableField<String> recovered = new ObservableField<>("0");
    public ObservableField<String> active = new ObservableField<>("0");
    public ObservableField<String> province = new ObservableField<>("Unknown");

    public CountryProvinceItemViewModel(@NonNull CountryViewModel viewModel, ProvinceEntity entity) {
        super(viewModel);
        confirm.set("" + entity.getConfirm());
        deaths.set("" + entity.getDeaths());
        recovered.set("" + entity.getRecovered());
        active.set("" + entity.getActive());
        province.set("" + entity.getName());
    }
}
