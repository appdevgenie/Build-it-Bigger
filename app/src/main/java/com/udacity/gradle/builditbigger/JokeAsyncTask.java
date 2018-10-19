package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class JokeAsyncTask extends AsyncTask<Void, Void, String> {

    private MyApi myApi;
    private AsyncRequestComplete asyncRequestComplete;

    public JokeAsyncTask(AsyncRequestComplete asyncRequestComplete) {
        this.asyncRequestComplete = asyncRequestComplete;
    }

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
    }

    public interface AsyncRequestComplete {
        void requestComplete(String joke);
    }
}
