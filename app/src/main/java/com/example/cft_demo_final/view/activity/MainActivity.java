package com.example.cft_demo_final.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.cft_demo_final.R;
import com.example.cft_demo_final.data.entities.ExchangeRate;
import com.example.cft_demo_final.view.adapter.ExchangeRateAdapter;
import com.example.cft_demo_final.viewmodel.ExchangeRateViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ExchangeRateViewModel exchangeRateViewModel;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ExchangeRateAdapter exchangeRateAdapter = new ExchangeRateAdapter(this);
        recyclerView.setAdapter(exchangeRateAdapter);

        exchangeRateViewModel
            = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory())
                .get(ExchangeRateViewModel.class);

        LiveData<CharSequence> error = exchangeRateViewModel.getError();
        LiveData<ArrayList<ExchangeRate>> data = exchangeRateViewModel.getData();

        swipeRefreshLayout.setOnRefreshListener(() -> {
            Log.i("swiperefresh", "REFRESHING");
            exchangeRateViewModel.loadData();
            swipeRefreshLayout.setRefreshing(false);
        });

        data.observe(this, exchangeRates -> {
            exchangeRateAdapter.setData(exchangeRates);
            swipeRefreshLayout.setRefreshing(false);
        });

        error.observe(this, (err) -> {
            swipeRefreshLayout.setRefreshing(false);
            if (err != null)
                Toast.makeText(this, "TIMEOUT ERROR", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refresh:
                Log.i("swiperefresh", "REFRESHING");
                swipeRefreshLayout.setRefreshing(true);
                exchangeRateViewModel.loadData();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}