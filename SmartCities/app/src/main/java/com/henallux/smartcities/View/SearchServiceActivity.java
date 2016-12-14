package com.henallux.smartcities.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.henallux.smartcities.DAO.CategoryServiceDAO;
import com.henallux.smartcities.DAO.ServiceDAO;
import com.henallux.smartcities.R;
import com.henallux.smartcities.exception.RechercheServiceException;
import com.henallux.smartcities.model.CategoryService;
import com.henallux.smartcities.model.Service;
import com.henallux.smartcities.model.ServicesAdapter;
import com.henallux.smartcities.model.UserConnected;
import com.henallux.smartcities.service.Business;

import java.util.ArrayList;

public class SearchServiceActivity extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    private Button btn_refresh, btn_accept_service;
    private Spinner spinner;
    private ListView listView;

    private UserConnected userConnected;
    private ArrayList<CategoryService> categoryServices = null;
    private ArrayList<Service> services = null, servicesCat;

    public static SearchServiceActivity newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        SearchServiceActivity fragment = new SearchServiceActivity();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
        userConnected = new UserConnected();
        String token = userConnected.getToken(getActivity());
        String email = userConnected.getUserConnected(getActivity()).getEmail();
        new LoadCategory().execute(token);
        new LoadServices().execute(token, email);




    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_searchservice, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_refresh = (Button) getActivity().findViewById(R.id.btn_refresh);
        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {actionBtnRefresh();}
        });
        btn_accept_service = (Button) getActivity().findViewById(R.id.btn_accept_service);
        btn_accept_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionBtnAcceptService();
            }
        });

    }

    private void actionBtnRefresh()
    {
        String token = userConnected.getToken(getActivity());
        String email = userConnected.getUserConnected(getActivity()).getEmail();
        new LoadServices().execute(token, email);
    }

    private void actionBtnAcceptService()
    {
        try
        {
            Service service = Business.rechercheService(listView, services);
            service.setServiceDone(true);
            new AcceptService().execute(service);
        }
        catch (RechercheServiceException e)
        {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private class LoadCategory extends AsyncTask<String, Integer, ArrayList<CategoryService>>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }
        @Override
        protected ArrayList<CategoryService> doInBackground(String... params)
        {
            try
            {
                CategoryServiceDAO categoryServiceDAO = new CategoryServiceDAO();
                categoryServices = categoryServiceDAO.getCategoryService(params[0]);
            }
            catch (Exception e)
            {
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();

            }
            return categoryServices;
        }

        @Override
        protected void onProgressUpdate(Integer... values)
        {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(final ArrayList<CategoryService> categoryServices) {
            spinner = (Spinner) getActivity().findViewById(R.id.spinnerSearchService);
            ArrayList<String> category = new ArrayList<>();
            category.add("Selectionnez une catégorie");
            for (CategoryService cat : categoryServices)
            {
                category.add(cat.getLabel());
            }
            ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item, category);
            spinner.setAdapter(adapter);
        }
    }

    private class LoadServices extends AsyncTask<String, Void, ArrayList<Service>>
    {
        @Override
        protected ArrayList<Service> doInBackground(String... params)
        {

            try
            {
                ServiceDAO serviceDAO = new ServiceDAO();
                services = serviceDAO.getServices(params[0], params[1]);
            }
            catch (Exception e)
            {
                //Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                Log.i("Test", e.getMessage());
            }
            return services;
        }

        @Override
        protected void onPostExecute(final ArrayList<Service> services) {
            spinner = (Spinner) getActivity().findViewById(R.id.spinnerSearchService);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    servicesCat = Business.triServiceCat(services, (String) spinner.getSelectedItem());
                    listView = (ListView) getActivity().findViewById(R.id.listServices);
                    ServicesAdapter servicesAdapter = new ServicesAdapter(getActivity(), servicesCat);
                    listView.setAdapter(servicesAdapter);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            servicesCat = Business.triServiceCat(services, (String) spinner.getSelectedItem());
            listView = (ListView) getActivity().findViewById(R.id.listServices);
            ServicesAdapter servicesAdapter = new ServicesAdapter(getActivity(), servicesCat);
            listView.setAdapter(servicesAdapter);

        }
    }

    private class AcceptService extends AsyncTask<Service, Void, Boolean>
    {
        private Exception exceptionToBeThrow = null;
        @Override
        protected Boolean doInBackground(Service... params)
        {
            try {
                ServiceDAO serviceDAO = new ServiceDAO();
                String token = userConnected.getToken(getActivity());
                serviceDAO.putService(token, params[0]);
                return true;
            }
            catch (Exception e)
            {
                exceptionToBeThrow = e;
                return false;
            }

        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (!success)
                Toast.makeText(getActivity(), exceptionToBeThrow.getMessage(), Toast.LENGTH_LONG).show();
            else
            {
                Toast.makeText(getActivity(), "Service accepté", Toast.LENGTH_LONG).show();
                actionBtnRefresh();
            }
        }
    }

}