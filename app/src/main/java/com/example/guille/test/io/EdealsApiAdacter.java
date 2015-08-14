package com.example.guille.test.io;

import retrofit.RestAdapter;


public class EdealsApiAdacter {

    private static EdealsService apiService;

    public static EdealsService getApiService() {

        if(apiService ==null) {
            RestAdapter adapter = new RestAdapter.Builder()
                    .setEndpoint(ApiConstants.URL_BASE)
                    .setLogLevel(RestAdapter.LogLevel.BASIC).build();

            apiService = adapter.create(EdealsService.class);
        }
        return apiService;
    }
}
