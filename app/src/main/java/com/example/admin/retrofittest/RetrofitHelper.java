package com.example.admin.retrofittest;

import android.util.Log;

import com.example.admin.retrofittest.entities.RelatedTopic;
import com.example.admin.retrofittest.entities.Result;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by admin on 3/11/2016.
 */
public class RetrofitHelper {
    public static void main(String[] args) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.duckduckgo.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DuckService duckService = retrofit.create(DuckService.class);

        Call<Result> listCall = duckService.listCharacters("simpsons characters");

        Result results = null;

        try {
            results = listCall.execute().body();
        } catch (Exception e) {
            // Log.e(TAG, "Error: " + e.toString());
        }

        for (RelatedTopic relatedTopic : results.getRelatedTopics()) {
            System.out.println(relatedTopic.getText());
        }
        //       return results;
    }

//    public interface DuckService {
//        @GET("?format=json")
//        Call<Result> listCharacters(@Query("q") String q);
////      http://api.duckduckgo.com/?format=json&q=simpsons characters
//
//        // @GET("/{q}/?format=json")
//        // //Call <Result> listCharacters2(@Path("q") String q);
////      http://api.duckduckgo.com/simpsons characters/?format=json
//    }
}