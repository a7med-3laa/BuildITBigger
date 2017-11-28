package com.udacity.gradle.builditbigger;

import android.content.Context;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class JokeAsyncTask extends android.os.AsyncTask<String, Void, String> {
    MyApi myApi;
    private SendInterface sendInterface;
    private Context context;

    public JokeAsyncTask(SendInterface sendInterface, Context context) {
        this.sendInterface = sendInterface;
        this.context = context;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        sendInterface.onPostExecute(s);
    }

    @Override
    protected String doInBackground(String... strings) {
        if (myApi == null) {  // Only do this once
            MyApi.Builder builder = new
                    MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // ­ 10.0.2.2 is localhost's IP address in Android emulator
                    // ­ turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?>
                                                       abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApi = builder.build();
        }
        try {
            return myApi.sayHi().execute().getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
