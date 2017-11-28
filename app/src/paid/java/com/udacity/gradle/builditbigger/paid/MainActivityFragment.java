package com.udacity.gradle.builditbigger.paid;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.ahmedalaa.mylibrary.JokeDisplayActivity;
import com.udacity.gradle.builditbigger.JokeAsyncTask;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.SendInterface;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivityFragment extends Fragment {

    ProgressBar progressBar;
    Button btn;

    public MainActivityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        progressBar = (ProgressBar) root.findViewById(R.id.progress_bar);
        btn = (Button) root.findViewById(R.id.tellJoke);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                new JokeAsyncTask(new SendInterface() {

                    @Override
                    public void onPostExecute(String s) {
                        progressBar.setVisibility(View.GONE);
                        Intent intent = new Intent(getActivity(), JokeDisplayActivity.class);
                        intent.putExtra(JokeDisplayActivity.JOKE_INTENT_KEY, s);
                        startActivity(intent);

                    }
                }, getActivity()).execute();

            }
        });
        return root;
    }

}
