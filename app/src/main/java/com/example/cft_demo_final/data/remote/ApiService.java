package com.example.cft_demo_final.data.remote;

import com.example.cft_demo_final.data.entities.DailyExchangeRates;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/daily_json.js")
    Call<DailyExchangeRates> getDailyExchangeRates();
}
