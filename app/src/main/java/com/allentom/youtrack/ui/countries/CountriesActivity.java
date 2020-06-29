package com.allentom.youtrack.ui.countries;

import android.os.Bundle;

import com.allentom.youtrack.BR;
import com.allentom.youtrack.databinding.ActivityCountriesBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;

import com.allentom.youtrack.R;

import me.goldze.mvvmhabit.base.BaseActivity;

public class CountriesActivity extends BaseActivity<ActivityCountriesBinding, CountriesViewModel> {

    @Override
    public CountriesViewModel initViewModel() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Regions");
        return super.initViewModel();
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_countries;
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