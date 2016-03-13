package com.example.admin.retrofittest;

import com.example.admin.retrofittest.entities.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by admin on 3/11/2016.
 */
public interface DuckService {
    @GET("?format=json")
    Call<Result> listCharacters(@Query("q") String q);
//      http://api.duckduckgo.com/?format=json&q=simpsons characters

    //  @GET("/{q}/?format=json")
    //    Call <Result> listCharacters2(@Path("q") String q);
//      http://api.duckduckgo.com/simpsons characters/?format=json
}