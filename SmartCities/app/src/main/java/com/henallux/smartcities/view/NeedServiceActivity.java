package com.henallux.smartcities.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    private int mPage;

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
        mPage = getArguments().getInt(ARG_PAGE);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("myPref", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("Token", "");
        new LoadCategory().execute(token);

    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_need_service, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnAddService = (Button) getActivity().findViewById(R.id.btn_service);
        btnAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {actionBtnSend();}
        });
    }

    private void actionBtnSend()
    {
        String label = String.valueOf(((EditText) getActivity().findViewById(R.id.libelle_service_edit_text)).getText());
        String description = String.valueOf(((EditText) getActivity().findViewById(R.id.description_service_edit_text)).getText());
        UserApp userApp = UserConnected.getInstance();
        CategoryService categoryService = new CategoryService(((Spinner)getActivity().findViewById(R.id.spinnerNeedService)).getSelectedItemPosition() ,((Spinner)getActivity().findViewById(R.id.spinnerNeedService)).getSelectedItem().toString());
        Service service = new Service(label, description, new Date(), userApp, categoryService);
        new SendService().execute(service);
    }

    private class SendService extends AsyncTask<Service, Void, Void>
    {
        private Exception exception;

        @Override
        protected Void doInBackground(Service... params) {
            try
            {
                ServiceDAO serviceDAO = new ServiceDAO();
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("myPref", Context.MODE_PRIVATE);
                String token = sharedPreferences.getString("Token", "");
                serviceDAO.postServices(token, params[0]);
            }
            catch(Exception e)
            {
                Log.i("Test", e.getMessage());
            }
            return null;
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
            ArrayList<CategoryService> categoryServices = null;
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
        protected void onPostExecute(ArrayList<CategoryService> categoryServices) {
            Spinner spinner = (Spinner) getActivity().findViewById(R.id.spinnerNeedService);
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
}
