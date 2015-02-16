package com.example.micaela.worldcupapi;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;

/**
 * A placeholder fragment containing a simple view.
 */
public class WorldCupFragment extends ListFragment {

    TextView mTextViewCountryCode;
     MatchAdapter mAdapter;
    final static String LOG_TAG = WorldCupFragment.class.getSimpleName();

    WorldCupService.ApiInterface mWorldCupInterface;


    public WorldCupFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        wireUpViews(rootView);
        prepareButton(rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        prepareListView();
    }

    private void prepareListView() {
        List<Match> matches = new ArrayList<>();
        mAdapter = new MatchAdapter(getActivity(), matches);
        setListAdapter(mAdapter);
    }

    private void wireUpViews(View rootView) {
        mTextViewCountryCode = (TextView) rootView.findViewById(R.id.edit_text_country_code);
    }

    private void prepareButton(View rootView) {
        Button buttonGetTeams = (Button) rootView.findViewById(R.id.button_get_teams);
        buttonGetTeams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = mTextViewCountryCode.getText().toString();
                mWorldCupInterface.getTeams(code, new retrofit.Callback<List<Match>>() {

                    @Override
                    public void success(List<Match> matches, retrofit.client.Response response) {
                        if (response.getStatus() == 200) {
                            mAdapter.clear();
                            mAdapter.addAll(matches);
                            mAdapter.notifyDataSetChanged();
                        } else {
                            Log.e(LOG_TAG, "Project retrieval status problem: " + response.getReason());
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.w(LOG_TAG, "ERROR: downloading " + error.getBody());
                    }
                });
            }
        });
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        WorldCupService worldCupService = new WorldCupService();
        mWorldCupInterface = worldCupService.generateServiceInterface();
    }
}
