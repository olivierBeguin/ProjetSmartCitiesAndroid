package com.henallux.smartcities.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.henallux.smartcities.DAO.CategoryServiceDAO;
import com.henallux.smartcities.DAO.ServiceDAO;
import com.henallux.smartcities.R;
import com.henallux.smartcities.model.CategoryService;
import com.henallux.smartcities.model.Service;
import com.henallux.smartcities.model.UserApp;
import com.henallux.smartcities.model.UserConnected;

import java.util.ArrayList;
import java.util.Date;

public class NeedServiceActivity extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private UserConnected userConnected;

    public static NeedServiceActivity newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        NeedServiceActivity fragment = new NeedServiceActivity();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int mPage = getArguments().getInt(ARG_PAGE);
        userConnected = new UserConnected();
        String token = userConnected.getToken(getActivity());
        new LoadCategory().execute(token);

    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_need_service, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnAddService = (Button) getActivity().findViewById(R.id.btn_add_service);
        btnAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {actionBtnSend();}
        });
    }

    private void actionBtnSend()
    {
        String label = String.valueOf(((EditText) getActivity().findViewById(R.id.libelle_service_edit_text)).getText());
        String description = String.valueOf(((EditText) getActivity().findViewById(R.id.description_service_edit_text)).getText());
        UserConnected userConnected = new UserConnected();
        UserApp userApp = userConnected.getUserConnected(getActivity());
        CategoryService categoryService = new CategoryService(((Spinner)getActivity().findViewById(R.id.spinnerNeedService)).getSelectedItemPosition() ,((Spinner)getActivity().findViewById(R.id.spinnerNeedService)).getSelectedItem().toString());
        Service service = new Service(label, description, new Date(), userApp, categoryService);
        new SendService().execute(service);
    }

    private class SendService extends AsyncTask<Service, Void, Boolean>
    {
        private Exception exceptionToBeThrow;

        @Override
        protected Boolean doInBackground(Service... params) {
            try
            {
                ServiceDAO serviceDAO = new ServiceDAO();
                String token = userConnected.getToken(getActivity());
                serviceDAO.postServices(token, params[0]);
                return true;
            }
            catch(Exception e)
            {
                exceptionToBeThrow = e;
                return false;
            }

        }

        @Override
        protected void onPostExecute(Boolean success)
        {
            if (!success && exceptionToBeThrow != null)
                Toast.makeText(getActivity(), exceptionToBeThrow.getMessage(), Toast.LENGTH_LONG).show();
            else
            {
                serviceAccepted();
            }
        }
    }

    private class LoadCategory extends AsyncTask<String, Integer, ArrayList<CategoryService>>
    {
        private Exception exceptionToBeThrow;
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }
        @Override
        protected ArrayList<CategoryService> doInBackground(String... params)
        {
            ArrayList<CategoryService> categoryServices = null;
            try
            {
                CategoryServiceDAO categoryServiceDAO = new CategoryServiceDAO();
                categoryServices = categoryServiceDAO.getCategoryService(params[0]);
            }
            catch (Exception e)
            {
                exceptionToBeThrow = e;
            }
            return categoryServices;
        }

        @Override
        protected void onProgressUpdate(Integer... values)
        {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(ArrayList<CategoryService> categoryServices) {
            if(categoryServices == null && exceptionToBeThrow != null)
                Toast.makeText(getActivity(), exceptionToBeThrow.getMessage(), Toast.LENGTH_LONG).show();
            else
            {
                Spinner spinner = (Spinner) getActivity().findViewById(R.id.spinnerNeedService);
                ArrayList<String> category = new ArrayList<>();
                category.add("Selectionnez une catégorie");
                for (CategoryService cat : categoryServices) {
                    category.add(cat.getLabel());
                }
                ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item, category);
                spinner.setAdapter(adapter);
            }
        }
    }

    private void serviceAccepted()
    {
        Toast.makeText(getActivity(), "Service envoyé", Toast.LENGTH_LONG).show();
        EditText labelService = (EditText) getActivity().findViewById(R.id.libelle_service_edit_text);
        labelService.setText("");
        EditText descriptionService = (EditText) getActivity().findViewById(R.id.description_service_edit_text);
        descriptionService.setText("");
        Intent intent = new Intent(getActivity(), ServicesActivity.class);
        startActivity(intent);
    }
}
