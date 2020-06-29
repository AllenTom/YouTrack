package com.allentom.youtrack.ui.search;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.afollestad.materialdialogs.MaterialDialog;
import com.allentom.youtrack.data.entites.CountryEntity;
import com.allentom.youtrack.ui.country.CountryActivity;

import me.goldze.mvvmhabit.base.ItemViewModel;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class SearchCountryItemViewModel extends ItemViewModel<SearchViewModel> {
    public ObservableField<String> region = new ObservableField<>("");
    public ObservableField<String> symbol = new ObservableField<>("");
    private CountryEntity entity;

    public SearchCountryItemViewModel(@NonNull SearchViewModel viewModel, CountryEntity entity) {
        super(viewModel);
        region.set(entity.getCountry());
        symbol.set(entity.getIso2());
        this.entity = entity;
    }

    public void onItemClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putString(CountryActivity.BUNDLE_KEY_COUNTRY_NAME, entity.getSlug());
        viewModel.startActivity(CountryActivity.class, bundle);
    }

    public Boolean onItemLongClick(View view) {
        Log.d("Debug", "onItemLongClick: Hit it");
        MaterialDialog dialog = new MaterialDialog.Builder(view.getContext())
                .title("操作")
                .items("设为主页", "添加到列表")
                .itemsCallback((dialog1, itemView, position, text) -> {
                    if (position == 0) {
                        viewModel.setHomeDisplayCountry(entity.getSlug());
                        ToastUtils.showShort("已将" + entity.getCountry() + "设为主页显示的国家");
                    } else if (position == 1) {
                        viewModel.addToFollowCountry(entity);
                    }
                })
                .show();

        return true;
    }
}
