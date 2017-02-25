package com.udacity.gradle.builditbigger;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.hp.myapplication.backend.myApi.MyApi;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.udacity.gradle.jokes.Joker;
import com.example.mylibrary.*;
import java.io.IOException;




public class MainActivityFragment extends Fragment {

    Button b;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setContentView(R.layout.fragment_main);
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        b=(Button)getActivity().findViewById(R.id.tell);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Joker joker=new Joker();
                String s=joker.getJoke();
                Intent intent=new Intent(getContext(),jokesactivity.class);
                intent.putExtra("JOKE",s);
                getActivity().startActivity(intent);
               // Toast.makeText(getActivity(),s,Toast.LENGTH_LONG).show();
            }
        });

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }

    @SuppressLint("NewApi")
    class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
        private MyApi myApiService = null;
        private Context context;

        @Override
        protected String doInBackground(Pair<Context, String>... params) {
            if(myApiService == null) {
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // options for running against local devappserver
                        // - 10.0.2.2 is localhost's IP address in Android emulator
                        // - turn off compression when running against local devappserver
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                // end options for devappserver

                myApiService = builder.build();
            }

            context = params[0].first;
            String name = params[0].second;

            try {
                return myApiService.sayHi(name).execute().getData();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Intent intent = new Intent(getActivity(), jokesactivity.class);
         //   intent.putExtra(DisplayJokeActivity.JOKE_KEY, result);
            startActivity(intent);


//        Toast.makeText(context, result, Toast.LENGTH_LONG).show();


        }
    }
}
