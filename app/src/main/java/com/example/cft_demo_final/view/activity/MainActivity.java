package com.example.cft_demo_final.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.example.cft_demo_final.R;
import com.example.cft_demo_final.data.entities.ExchangeRate;
import com.example.cft_demo_final.viewmodel.ExchangeRateViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ExchangeRateViewModel exchangeRateViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exchangeRateViewModel
            = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory())
                .get(ExchangeRateViewModel.class);

        LiveData<CharSequence> error = exchangeRateViewModel.getError();
        LiveData<ArrayList<ExchangeRate>> data = exchangeRateViewModel.getData();

        data.observe(this, exchangeRates -> {
            exchangeRateViewModel.getData();
        });

        error.observe(this, (err) -> {
            if (err != null)
                Toast.makeText(this, "TIMEOUT ERROR", Toast.LENGTH_SHORT).show();
        });
    }
}