package com.henallux.smartcities.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.henallux.smartcities.DAO.UserAppDAO;
import com.henallux.smartcities.R;
import com.henallux.smartcities.model.UserApp;

public class ProfilActivity extends LayoutActivity
{

    private Button myCommentButton, modifyButton, myActivitiesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        new LoadUserApp().execute();
        myCommentButton = (Button) findViewById(R.id.myCommentsButton);
        myCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToComment();
            }
        });
        modifyButton = (Button) findViewById(R.id.modify_button);
        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToModifProfil();
            }
        });
        myActivitiesButton = (Button) findViewById(R.id.myActivitiesButton);
        myActivitiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMyActivityActivity();
            }
        });
    }

    private void goToComment()
    {
        Intent intent = new Intent(ProfilActivity.this, SeeCommentActivity.class);
        startActivity(intent);
    }

    private void goToModifProfil()
    {
        Intent intent = new Intent(ProfilActivity.this, ModifProfilActivity.class);
        startActivity(intent);
    }

    private void goToMyActivityActivity()
    {
        Intent intent = new Intent(ProfilActivity.this, MyActivitiesActivity.class);
        startActivity(intent);
    }

    private class LoadUserApp extends AsyncTask<String, Void, UserApp>
    {
        @Override
        protected UserApp doInBackground(String... params)
        {
            UserApp userApp = null;
            try {
                UserAppDAO userAppDAO = new UserAppDAO();
                //userApp = userAppDAO.getUserWithMailandPw("", "");

            }
            catch (Exception e)
            {
                Log.i("Test", e.getMessage());
            }
            return userApp;
        }

        @Override
        protected void onPostExecute(UserApp userApp) {
            EditText editText = (EditText) findViewById(R.id.lastnameEditTextProfil);
            editText.setText(userApp.getLastname());
            editText = (EditText) findViewById(R.id.firstnameTextEditTextProfil);
            editText.setText(userApp.getFirstname());
            editText = (EditText) findViewById(R.id.mailEditTextProfil);
            editText.setText(userApp.getMailAdress());
            editText = (EditText) findViewById(R.id.phoneEditTextProfil);
            editText.setText(userApp.getPhoneNumber());
            editText = (EditText) findViewById(R.id.streetEditTextProfil);
            editText.setText(userApp.getStreet());
            editText = (EditText) findViewById(R.id.numEditTextProfil);
            editText.setText(userApp.getHouseNumber());
            editText = (EditText) findViewById(R.id.postalCodeEditTextProfil);
            editText.setText(userApp.getPostalCode());
            editText = (EditText) findViewById(R.id.cityEditTextProfil);
            editText.setText(userApp.getCity());
            editText = (EditText) findViewById(R.id.countryTextEditProfil);
            editText.setText(userApp.getCountry());
        }
    }
}
