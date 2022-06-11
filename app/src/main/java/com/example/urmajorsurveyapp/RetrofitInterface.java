package com.example.urmajorsurveyapp;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {
    @POST("/create")
    Call<Void> executeSubmit (@Body Map<String, Object> map);
}
