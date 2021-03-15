package com.example.cft_demo_final.dependencies;

import com.example.cft_demo_final.data.remote.ApiService;

import retrofit2.Retrofit;

public class ApiServiceProvider {
    private static ApiService apiService;

    private ApiServiceProvider () {}

    private static void initialize () {
        Retrofit retrofit = RetrofitProvider.getRetrofit();
        apiService = retrofit.create(ApiService.class);
    }

    public static ApiService getApiService () {
        if (apiService == null) initialize();

        return apiService;
    }
}
