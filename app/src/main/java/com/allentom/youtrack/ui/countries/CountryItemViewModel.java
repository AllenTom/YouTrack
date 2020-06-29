package com.allentom.youtrack.ui.countries;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.allentom.youtrack.data.entites.CountryEntity;
import com.allentom.youtrack.ui.country.CountryActivity;

import me.goldze.mvvmhabit.base.ItemViewModel;

/**
 * @author Takayamaarenl
 */
public class CountryItemViewModel extends ItemViewModel<CountriesViewModel> {
    private CountryEntity entity;
    public ObservableField<String> country = new ObservableField<>("");
    public ObservableField<String> code = new ObservableField<>("");

    public CountryItemViewModel(@NonNull CountriesViewModel viewModel, CountryEntity entity) {
        super(viewModel);
        this.entity = entity;
        country.set(entity.getCountry());
        code.set(entity.getIso2());
    }

    public void onItemClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putString(CountryActivity.BUNDLE_KEY_COUNTRY_NAME, entity.getSlug());
        viewModel.startActivity(CountryActivity.class, bundle);
    }

}
