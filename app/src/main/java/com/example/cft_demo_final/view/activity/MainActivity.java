package com.example.cft_demo_final.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cft_demo_final.R;
import com.example.cft_demo_final.data.entities.DailyExchangeRates;
import com.example.cft_demo_final.dependencies.ApiServiceProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiServiceProvider.getApiService()
            .getDailyExchangeRates()
            .enqueue(new Callback<DailyExchangeRates>() {
                @Override
                public void onResponse (Call<DailyExchangeRates> call, Response<DailyExchangeRates> response) {

                }

                @Override
                public void onFailure (Call<DailyExchangeRates> call, Throwable t) {

                }
            });
    }
}