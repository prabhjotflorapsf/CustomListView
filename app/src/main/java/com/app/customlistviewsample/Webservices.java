package com.app.customlistviewsample;

import com.app.customlistviewsample.model.Repository;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Webservices {

    @GET("/repositories")
    Call<List<Repository>> getRepositories();
}
