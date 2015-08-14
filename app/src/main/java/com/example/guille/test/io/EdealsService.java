package com.example.guille.test.io;

import com.example.guille.test.io.model.DealResponse;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface EdealsService {


    /**
     *
     * @param response
     */
    @GET(ApiConstants.URL_DEALS)
    void getEdeals(Callback<List<DealResponse>> response);

    /**
     *
     * @param response
     * @param desc
     */
    @GET(ApiConstants.URL_DEALS)
    void getEdealsByDesc(@Query(ApiConstants.PARAM_DESC) String desc,Callback<List<DealResponse>> response);

}
