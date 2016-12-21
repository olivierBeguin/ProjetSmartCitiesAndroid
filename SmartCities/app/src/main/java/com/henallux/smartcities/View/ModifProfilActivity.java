package com.henallux.smartcities.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.henallux.smartcities.DAO.UserAppDAO;
import com.henallux.smartcities.R;
import com.henallux.smartcities.model.UserApp;
import com.henallux.smartcities.model.UserConnected;
import com.henallux.smartcities.service.Business;

public class ModifProfilActivity extends AppCompatActivity
{
    private UserConnected userConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_modif_profil);
        userConnected = new UserConnected();
        setModifyContent();
        Button btnSave = (Button) findViewById(R.id.save_button);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getModifyContent();
            }
        });
    }

    private void setModifyContent()
    {
        UserApp userApp = userConnected.getUserConnected(ModifProfilActivity.this);
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
        UserApp userModif = new UserApp(String.valueOf(((EditText) findViewById(R.id.firstnameTextEditTextModifProfil)).getText()), String.valueOf(((EditText) findViewById(R.id.lastnameEditTextModifProfil)).getText()), null ,String.valueOf(((EditText) findViewById(R.id.mailEditTextModifProfil)).getText()), String.valueOf(((EditText) findViewById(R.id.phoneEditTextModifProfil)).getText()), String.valueOf(((EditText) findViewById(R.id.streetEditTextModifProfil)).getText()), String.valueOf(((EditText) findViewById(R.id.cityEditTextModifProfil)).getText()), String.valueOf(((EditText) findViewById(R.id.countryTextEditModifProfil)).getText()), null, null, String.valueOf(((EditText) findViewById(R.id.postalCodeEditTextModifProfil)).getText()), String.valueOf(((EditText) findViewById(R.id.numEditTextModifProfil)).getText()));
        //Check s'ils sont !=
        UserApp userApp = userConnected.getUserConnected(ModifProfilActivity.this);
        boolean isChanged = Business.compareTwoUserAndAddDifference(userApp, userModif);
        //Verifier les données
        if (isChanged)
        {
//                Business.verifyEntry(userApp, userApp.getPassword(), ModifProfilActivity.this);
            new ModifProfil().execute(userApp);
        }

    }

    private class ModifProfil extends AsyncTask<UserApp, Void, UserApp>
    {
        private Exception exceptionToBeThrow = null;
        @Override
        protected UserApp doInBackground(UserApp... params)
        {
            UserApp userApp = null;
            try {
                UserAppDAO userAppDAO = new UserAppDAO();
                String token = userConnected.getToken(ModifProfilActivity.this);
                userAppDAO.updateUser(token, params[0]);
                userApp = params[0];
            }
            catch (Exception e)
            {
                exceptionToBeThrow = e;
            }
            return userApp;
        }

        @Override
        protected void onPostExecute(UserApp userApp) {
            if (userApp == null && exceptionToBeThrow != null)
                Toast.makeText(ModifProfilActivity.this, exceptionToBeThrow.getMessage(), Toast.LENGTH_LONG).show();
            else
            {
                userConnected.setUserConnected(ModifProfilActivity.this, userApp);
                Intent intent = new Intent(ModifProfilActivity.this, ProfilActivity.class);
                startActivity(intent);
            }
        }
    }


}
