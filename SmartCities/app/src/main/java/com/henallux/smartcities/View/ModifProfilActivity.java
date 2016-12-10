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
        EditText editText = (EditText) findViewById(R.id.lastnameEditTextModifProfil);
        editText.setText(userApp.getLastName());
        editText = (EditText) findViewById(R.id.firstnameTextEditTextModifProfil);
        editText.setText(userApp.getFirstName());
        editText = (EditText) findViewById(R.id.mailEditTextModifProfil);
        editText.setText(userApp.getEmail());
        editText = (EditText) findViewById(R.id.phoneEditTextModifProfil);
        editText.setText(userApp.getPhoneNumber());
        editText = (EditText) findViewById(R.id.streetEditTextModifProfil);
        editText.setText(userApp.getStreet());
        editText = (EditText) findViewById(R.id.numEditTextModifProfil);
        editText.setText(userApp.getNumber());
        editText = (EditText) findViewById(R.id.postalCodeEditTextModifProfil);
        editText.setText(userApp.getPostalCode());
        editText = (EditText) findViewById(R.id.cityEditTextModifProfil);
        editText.setText(userApp.getCity());
        editText = (EditText) findViewById(R.id.countryTextEditModifProfil);
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
