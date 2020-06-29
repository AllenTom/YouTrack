package com.allentom.youtrack.ui.country;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.allentom.youtrack.BR;
import com.allentom.youtrack.R;
import com.allentom.youtrack.databinding.ActivityCountryBinding;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * @author Takayamaaren
 */
public class CountryActivity extends BaseActivity<ActivityCountryBinding, CountryViewModel> {
    public static final String BUNDLE_KEY_COUNTRY_NAME = "country-name";

    @Override
    public CountryViewModel initViewModel() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return super.initViewModel();
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        viewModel.setCountryQueryName(intent.getStringExtra(BUNDLE_KEY_COUNTRY_NAME));
        super.initData();
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_country;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}