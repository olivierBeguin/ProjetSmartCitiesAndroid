package com.henallux.smartcities.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.henallux.smartcities.R;
import com.henallux.smartcities.model.UserApp;
import com.henallux.smartcities.model.UserConnected;

public class ModifProfilActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_profil);
        setModifyContent();
        getModifyContent();
    }

    private void setModifyContent()
    {
        UserApp userApp = UserConnected.getInstance();
        EditText editText = (EditText) findViewById(R.id.lastnameEditTextProfil);
        editText.setText(userApp.getLastName());
        editText = (EditText) findViewById(R.id.firstnameTextEditTextProfil);
        editText.setText(userApp.getFirstName());
        editText = (EditText) findViewById(R.id.mailEditTextProfil);
        editText.setText(userApp.getEmail());
        editText = (EditText) findViewById(R.id.phoneEditTextProfil);
        editText.setText(userApp.getPhoneNumber());
        editText = (EditText) findViewById(R.id.streetEditTextProfil);
        editText.setText(userApp.getStreet());
        editText = (EditText) findViewById(R.id.numEditTextProfil);
        editText.setText(userApp.getNumber());
        editText = (EditText) findViewById(R.id.postalCodeEditTextProfil);
        editText.setText(userApp.getPostalCode());
        editText = (EditText) findViewById(R.id.cityEditTextProfil);
        editText.setText(userApp.getCity());
        editText = (EditText) findViewById(R.id.countryTextEditProfil);
        editText.setText(userApp.getCountry());
    }

    private void getModifyContent()
    {
        //Recréer un objet user.
        //Check s'ils sont !=
        //Verifier les données
        //Si oui effectuer changement BD
        //Si non ne rien faire
    }
}
