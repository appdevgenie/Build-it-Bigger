package com.udacity.gradle.builditbigger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.appdevgenie.androidlibrary.JokeActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class JokeAsyncTask extends AsyncTask<Void, Void, String> {

    //private static final String JOKE = "joke";

    private MyApi myApi;
    private AsyncRequestComplete asyncRequestComplete;

    public JokeAsyncTask(AsyncRequestComplete asyncRequestComplete) {
        this.asyncRequestComplete = asyncRequestComplete;
    }

    /*@SuppressLint("StaticFieldLeak")
    private Context context;
    @SuppressLint("StaticFieldLeak")
    private ProgressBar progressBar;

    public JokeAsyncTask(Context context, ProgressBar progressBar) {
        this.context = context;
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);
    }*/

    @Override
    protected String doInBackground(Void... params) {

        if (myApi == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });
            myApi = builder.build();
        }

        try {
            return myApi.loadJoke().execute().getData();
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    @Override
    protected void onPostExecute(String joke) {
        super.onPostExecute(joke);

        asyncRequestComplete.requestComplete(joke);

        /*progressBar.setVisibility(View.GONE);

        Intent intent = new Intent(context, JokeActivity.class);
        intent.putExtra(JOKE, joke);
        context.startActivity(intent);*/

    }

    public interface AsyncRequestComplete {
        void requestComplete(String joke);
    }
}
