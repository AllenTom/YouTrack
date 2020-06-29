package com.allentom.youtrack.ui.main;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.allentom.youtrack.data.entites.CityEntity;
import com.allentom.youtrack.data.entites.ProvinceEntity;

import me.goldze.mvvmhabit.base.ItemViewModel;

/**
 * @author Takayamaaren
 */
public class ProvinceItemViewModel extends ItemViewModel<MainViewModel> {
    public ObservableField<String> confirm = new ObservableField<>("0");
    public ObservableField<String> deaths = new ObservableField<>("0");
    public ObservableField<String> recovered = new ObservableField<>("0");
    public ObservableField<String> active = new ObservableField<>("0");
    public ObservableField<String> province = new ObservableField<>("Unknown");

    public ProvinceItemViewModel(@NonNull MainViewModel viewModel, ProvinceEntity entity) {
        super(viewModel);
        confirm.set("" + entity.getConfirm());
        deaths.set("" + entity.getDeaths());
        recovered.set("" + entity.getRecovered());
        active.set("" + entity.getActive());
        province.set("" + entity.getName());
    }
}
