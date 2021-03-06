package com.henallux.smartcities.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.henallux.smartcities.R;
import com.henallux.smartcities.model.UserApp;
import com.henallux.smartcities.model.UserConnected;

public class ProfilActivity extends LayoutActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        loadUser();
        Button modifyButton = (Button) findViewById(R.id.modify_button);
        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToModifProfil();
            }
        });
        Button myActivitiesButton = (Button) findViewById(R.id.myActivitiesButton);
        myActivitiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMyActivityActivity();
            }
        });
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

    private void loadUser()
    {
        UserConnected userConnected = new UserConnected();
        UserApp userApp = userConnected.getUserConnected(ProfilActivity.this);
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
}
