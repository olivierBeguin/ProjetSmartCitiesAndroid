package com.henallux.smartcities.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.henallux.smartcities.DAO.DoServiceDAO;
import com.henallux.smartcities.R;
import com.henallux.smartcities.model.DoService;
import com.henallux.smartcities.model.DoServicesAdapter;
import com.henallux.smartcities.model.UserApp;
import com.henallux.smartcities.model.UserConnected;

import java.util.ArrayList;

public class MyServicesGivenActivity  extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    private ArrayList<DoService> doServices = null;
    private ListView listView;
    private UserConnected userConnected;

    public static MyServicesGivenActivity newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        MyServicesGivenActivity fragment = new MyServicesGivenActivity();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
        userConnected = new UserConnected();
        String token = userConnected.getToken(getActivity());

        new LoadDoServices().execute(token);
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.activity_my_services_done, container, false);
    }

    private class LoadDoServices extends AsyncTask<String, Void, ArrayList<DoService>>
    {
        private Exception exceptionToBeThrow = null;
        @Override
        protected ArrayList<DoService> doInBackground(String... params)
        {

            try {
                DoServiceDAO doServiceDAO = new DoServiceDAO();
                UserConnected userConnected = new UserConnected();
                UserApp userApp = userConnected.getUserConnected(getActivity());
                String token = userConnected.getToken(getActivity());
                doServices = doServiceDAO.getDoServiceOfUser(token, userApp.getEmail());
            } catch (Exception e) {
                exceptionToBeThrow = e;
            }
            return doServices;
        }

        @Override
        protected void onPostExecute(final ArrayList<DoService> doServices)
        {
            if(doServices == null && exceptionToBeThrow != null)
                Toast.makeText(getActivity(), exceptionToBeThrow.getMessage(), Toast.LENGTH_LONG).show();
            else
            {
                listView = (ListView) getActivity().findViewById(R.id.listview_my_services_done);
                DoServicesAdapter doServicesAdapter = new DoServicesAdapter(getActivity(), doServices);
                listView.setAdapter(doServicesAdapter);
            }
        }
    }


}

