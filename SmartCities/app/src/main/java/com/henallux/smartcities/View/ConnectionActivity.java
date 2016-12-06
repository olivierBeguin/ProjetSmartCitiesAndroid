package com.henallux.smartcities.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.henallux.smartcities.DAO.UserAppDAO;
import com.henallux.smartcities.R;
import com.henallux.smartcities.model.UserApp;
import com.henallux.smartcities.model.UserConnected;

public class ConnectionActivity extends AppCompatActivity
{

    private Button connectionButton;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        connectionButton = (Button) findViewById(R.id.connectionButtonConnection);
        connectionButton.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {goToServicesActivity();}
                });
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

    }

    private void goToServicesActivity()
    {
        String mail, pw;
        mail = String.valueOf(((EditText) findViewById(R.id.editTextMail)).getText());
        pw = String.valueOf(((EditText) findViewById(R.id.editPassword)).getText());
        new LoadUserApp().execute(mail, pw);
    }

    private void setTokenInSharedPreferences(String token)
    {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.myPref), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Token", token);
        editor.commit();
    }

    private class LoadUserApp extends AsyncTask<String, Integer, UserApp>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }
        @Override
        protected UserApp doInBackground(String... params)
        {
            UserApp userApp = null;
            String token;
            try {
                UserAppDAO userAppDAO = new UserAppDAO();
                Log.i("Test", params[0] + " " + params[1]);
                token = userAppDAO.getUserWithMailandPw(params[0], params[1]);
                setTokenInSharedPreferences(token);
                userApp = userAppDAO.getUser(token, params[0]);
                return userApp;
            }
            catch (Exception e)
            {
                Toast.makeText(ConnectionActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
            return userApp;
        }

        @Override
        protected void onProgressUpdate(Integer... values)
        {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(UserApp userApp) {
            progressBar.setVisibility(View.GONE);
            if (userApp != null)
            {
                UserConnected.setInstance(userApp);
                Intent intent = new Intent(ConnectionActivity.this, ServicesActivity.class);
                startActivity(intent);
            }
        }
    }
}