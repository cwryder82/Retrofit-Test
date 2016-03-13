package com.example.admin.retrofittest;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.admin.retrofittest.RetrofitHelper;
import com.example.admin.retrofittest.entities.RelatedTopic;
import com.example.admin.retrofittest.entities.Result;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 3/11/2016.
 */
public class TestAsync extends AsyncTask<Void, Void, Result> {

    TextView tv;

    public TestAsync () {

    }

    public TestAsync (TextView v) {
        this();
        tv = v;
    }

    protected void onPreExecute () {

    }

    @Override
    protected Result doInBackground (Void...arg0) {
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

        return results;
    }

    @Override
    protected void onPostExecute (Result result) {
        ArrayList<String> data = new ArrayList<>();
        for (RelatedTopic rt: result.getRelatedTopics()) {
            data.add(rt.getText());
            tv.append(rt.getText());
            tv.append("\n\n");
            Log.v("onPostExecute", rt.getText());
        }

    }
}
