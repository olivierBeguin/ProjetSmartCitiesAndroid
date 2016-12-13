package com.henallux.smartcities.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.henallux.smartcities.R;

public class MyServicesDoneActivity extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    public static MyServicesDoneActivity newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        MyServicesDoneActivity fragment = new MyServicesDoneActivity();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my_services_given, container, false);
        return view;
    }
}