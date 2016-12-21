package com.henallux.smartcities.view;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.henallux.smartcities.DAO.UserAppDAO;
import com.henallux.smartcities.R;
import com.henallux.smartcities.exception.FormException;
import com.henallux.smartcities.model.UserApp;
import com.henallux.smartcities.model.UserConnected;
import com.henallux.smartcities.service.Business;

import java.util.Date;

public class RegisterActivity extends AppCompatActivity
{

    private ProgressBar progressBar;
    private UserConnected userConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button registerButton = (Button) findViewById(R.id.registerButtonRegister);
        registerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {registerUser();}
        });
        userConnected = new UserConnected();
        progressBar = (ProgressBar) findViewById(R.id.progressBar_register);
        progressBar.getIndeterminateDrawable().setColorFilter(0xFF740024, PorterDuff.Mode.MULTIPLY);
        progressBar.setVisibility(View.GONE);
        Spinner spinner = (Spinner) findViewById(R.id.spinnerCategory);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.category_array, R.layout.support_simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


    }

    private void registerUser()
    {
        String firstname, lastname, password, confirmPassword, mailAdress, phoneNumber, street, city, country, postalCode, houseNumber, category;
        lastname = String.valueOf(((EditText) findViewById(R.id.lastnameEditTextRegister)).getText());
        firstname = String.valueOf(((EditText) findViewById(R.id.firstnameTextEditTextRegister)).getText());
        password = String.valueOf(((EditText) findViewById(R.id.passwordEditTextRegister)).getText());
        confirmPassword = String.valueOf(((EditText) findViewById(R.id.confirmPasswordEditTextRegister)).getText());
        mailAdress = String.valueOf(((EditText) findViewById(R.id.mailEditTextRegister)).getText());
        phoneNumber = String.valueOf(((EditText) findViewById(R.id.phoneEditTextRegister)).getText());
        street = String.valueOf(((EditText) findViewById(R.id.streetEditTextRegister)).getText());
        city = String.valueOf(((EditText) findViewById(R.id.cityEditTextRegister)).getText());
        country = String.valueOf(((EditText) findViewById(R.id.countryTextEditRegister)).getText());
        postalCode = String.valueOf(((EditText) findViewById(R.id.postalCodeEditTextRegister)).getText());
        houseNumber = String.valueOf(((EditText) findViewById(R.id.numEditTextRegister)).getText());
        category = String.valueOf(((Spinner) findViewById(R.id.spinnerCategory)).getSelectedItem());
        UserApp user = new UserApp(firstname, lastname, password, mailAdress, phoneNumber,street, city, country, category , new Date(), postalCode, houseNumber);
        try
        {
            //Log.i("Test", "lastname : " + lastname + "\n firstname :  " + firstname + "\n password :  " + password + "\n confirmPassword : " + confirmPassword + "\n mailAdress : " + mailAdress + "\n phoneNumber : " + phoneNumber + "\n street :  " + street + "\n city : " + city + "\n country : " + country + "\n postalCode : " + postalCode + "\n houseNumber :  " + houseNumber);
            Business.verifyEntry(user, confirmPassword, this);
            new SetUserApp().execute(user);
            new LoadUserApp().execute(mailAdress, password);

        }
        catch(FormException e)
        {
            Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void goToServicesActivity()
    {
        Intent intent = new Intent(RegisterActivity.this, ServicesActivity.class);
        startActivity(intent);
    }

    private class SetUserApp extends AsyncTask<UserApp, Void, UserApp>
    {
        Exception exceptionToBeThrow;
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }
        @Override
        protected UserApp doInBackground(UserApp... params)
        {


            try {
                UserAppDAO userAppDAO = new UserAppDAO();
                UserApp userApp = params[0];
                userAppDAO.registerUser(userApp);

            }
            catch (Exception e)
            {
                exceptionToBeThrow = e;
            }
            return params[0];
        }

        @Override
        protected void onPostExecute(UserApp userApp) {
            UserConnected userConnected = new UserConnected();
            userConnected.setUserConnected(RegisterActivity.this, userApp);
        }
    }


    private class LoadUserApp extends AsyncTask<String, Integer, UserApp>
    {
        private Exception exceptionToBeThrow;
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }
        @Override
        protected UserApp doInBackground(String... params)
        {
            UserApp userApp = new UserApp();
            String token;
            try
            {
                UserAppDAO userAppDAO = new UserAppDAO();
                token = userAppDAO.getUserWithMailandPw(params[0], params[1]);
                userConnected.setToken(RegisterActivity.this, token);
                userApp = userAppDAO.getUser(token, params[0]);
                return userApp;
            }
            catch (Exception e)
            {
                exceptionToBeThrow = e;
                return userApp;
            }

        }

        @Override
        protected void onProgressUpdate(Integer... values)
        {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(UserApp userApp) {
            progressBar.setVisibility(View.GONE);
            if (userApp != null && exceptionToBeThrow == null)
            {
                goToServicesActivity();
            }
            else
            {
                Toast.makeText(RegisterActivity.this, exceptionToBeThrow.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

}
