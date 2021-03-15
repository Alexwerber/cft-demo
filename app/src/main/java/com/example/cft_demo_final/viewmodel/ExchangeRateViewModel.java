package com.example.cft_demo_final.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cft_demo_final.data.entities.DailyExchangeRates;
import com.example.cft_demo_final.data.entities.ExchangeRate;
import com.example.cft_demo_final.dependencies.ApiServiceProvider;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExchangeRateViewModel extends ViewModel {
    private MutableLiveData<ArrayList<ExchangeRate>> data = new MutableLiveData<>();
    private MutableLiveData<CharSequence> error = new MutableLiveData<>();

    public LiveData<ArrayList<ExchangeRate>> getData () { return data; }

    public  LiveData<CharSequence> getError () {return error; }

    public ExchangeRateViewModel () {
        error.setValue(null);
        data.setValue(new ArrayList<>());
        loadData();
    }

    public void loadData () {
        ApiServiceProvider.getApiService()
            .getDailyExchangeRates()
            .enqueue(new Callback<DailyExchangeRates>() {
                @Override
                public void onResponse (Call<DailyExchangeRates> call, Response<DailyExchangeRates> response) {
                    Log.i("loadData", "RESPONSE");
                    error.setValue(null);
                    data.setValue(new ArrayList<ExchangeRate>(response.body().getValute().values()));
                }

                @Override
                public void onFailure (Call<DailyExchangeRates> call, Throwable t) {
                    Log.i("loadData", "FAILURE");
                    error.setValue(t.toString());
                }
            });
    }
}
