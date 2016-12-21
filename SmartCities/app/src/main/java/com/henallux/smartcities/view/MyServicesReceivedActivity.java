package com.henallux.smartcities.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.henallux.smartcities.DAO.DoServiceDAO;
import com.henallux.smartcities.R;
import com.henallux.smartcities.exception.RechercheServiceException;
import com.henallux.smartcities.model.DoService;
import com.henallux.smartcities.model.ServicesReceivedAdapter;
import com.henallux.smartcities.model.UserApp;
import com.henallux.smartcities.model.UserConnected;
import com.henallux.smartcities.service.Business;

import java.io.Serializable;
import java.util.ArrayList;


public class MyServicesReceivedActivity extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    private UserConnected userConnected;
    private ArrayList<DoService> doServicesReceived;
    private ListView listView;
    private Button btn_add_comment;

    public static MyServicesReceivedActivity newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        MyServicesReceivedActivity fragment = new MyServicesReceivedActivity();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
        userConnected = new UserConnected();
        String token = userConnected.getToken(getActivity());
        new LoadDoServiceReceived().execute(token);
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my_services_received, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_add_comment = (Button) getActivity().findViewById(R.id.btn_add_comment);
        btn_add_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionBtnAddComment();
            }
        });
    }

    private class LoadDoServiceReceived extends AsyncTask<String, Void, ArrayList<DoService>>
    {
        private Exception exceptionToBeThrow;

        @Override
        protected ArrayList<DoService> doInBackground(String... params)
        {
            try
            {
                DoServiceDAO doServiceDAO = new DoServiceDAO();
                UserApp userApp = userConnected.getUserConnected(getActivity());
                doServicesReceived = doServiceDAO.getDoServicesReceived(params[0], userApp.getEmail());
            }
            catch (Exception e)
            {
                exceptionToBeThrow = e;
            }
            return doServicesReceived;
        }

        @Override
        protected void onPostExecute(ArrayList<DoService> doServices)
        {
            if(doServices == null && exceptionToBeThrow != null)
                Toast.makeText(getActivity(), exceptionToBeThrow.getMessage(), Toast.LENGTH_LONG).show();
            else
            {
                listView = (ListView) getActivity().findViewById(R.id.list_services_received);
                ServicesReceivedAdapter servicesReceivedAdapter = new ServicesReceivedAdapter(getActivity(), doServices);
                listView.setAdapter(servicesReceivedAdapter);
            }
        }
    }

    private void actionBtnAddComment()
    {
        try
        {
            if(!doServicesReceived.isEmpty())
            {
                DoService doServiceToAddComment = Business.rechercheDoService(listView, doServicesReceived);
                if (doServiceToAddComment != null) {
                    Intent intent = new Intent(getActivity(), CommentActivity.class);
                    intent.putExtra("doService", doServiceToAddComment);
                    startActivity(intent);
                }
            }
            else
                throw new RechercheServiceException("Liste vide");
        } catch (RechercheServiceException e)
        {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}