package com.example.micaela.worldcupapi;

import android.util.Base64;

import com.squareup.okhttp.OkHttpClient;

import java.util.List;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Micaela on 16/02/2015.
 */
public class WorldCupService {

    final static String WORLD_CUP_API_URL = "http://worldcup.sfg.io/matches";
    final static String PROJECTS_ENDPOINT = "/country";

    public interface ApiInterface {
        @GET(PROJECTS_ENDPOINT)
        void getTeams(@Query("fifa_code") String code,
                        Callback<List<Match>> callback);
    }

    public WorldCupService() {
    }

    public ApiInterface generateServiceInterface() {
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(WORLD_CUP_API_URL)
                .setClient(new OkClient(new OkHttpClient()));
        RestAdapter restAdapter = builder.build();
        return restAdapter.create(ApiInterface.class);
    }
}
