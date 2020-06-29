package com.allentom.youtrack.ui.search;

import android.os.Bundle;

import com.allentom.youtrack.BR;
import com.allentom.youtrack.databinding.ActivitySearchBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;

import android.view.MenuItem;
import android.view.View;

import com.allentom.youtrack.R;

import me.goldze.mvvmhabit.base.BaseActivity;

public class SearchActivity extends BaseActivity<ActivitySearchBinding, SearchViewModel> {

    @Override
    public SearchViewModel initViewModel() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Search Region");
        return super.initViewModel();
    }


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_search;
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