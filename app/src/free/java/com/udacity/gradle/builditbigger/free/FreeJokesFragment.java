package com.udacity.gradle.builditbigger;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.hp.myapplication.backend.myApi.MyApi;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.jokes.Joker;
import com.example.mylibrary.*;
import java.io.IOException;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


public class FreeJokesFragment extends Fragment {

    Button b;
    public FreeJokesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // getActivity().setContentView(R.layout.fragment_main);
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        b = (Button) getActivity().findViewById(R.id.tell);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new EndpointsAsyncTask(getActivity()).execute();
            }
        });
    }

    public static class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
           Context context;

        public EndpointsAsyncTask(Context context){
            this.context=context;
        }

        @Override
        protected String doInBackground(Void... params) {
            MyApi myApiService = null;
            if(myApiService == null) {
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                myApiService = builder.build();
            }
            try {
                return myApiService.getJoke().execute().getData();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Intent intent = new Intent(context, jokesactivity.class);
            intent.putExtra("JOKE", result);
            intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
