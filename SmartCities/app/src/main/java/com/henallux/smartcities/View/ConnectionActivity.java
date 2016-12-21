package com.henallux.smartcities.view;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private ProgressBar progressBar;
    private UserConnected userConnected;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        Button connectionButton = (Button) findViewById(R.id.connectionButtonConnection);
        connectionButton.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {goToServicesActivity();}
                });
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.getIndeterminateDrawable().setColorFilter(0xFF740024, PorterDuff.Mode.MULTIPLY);
        progressBar.setVisibility(View.GONE);
        userConnected = new UserConnected();

    }

    private void goToServicesActivity()
    {
        String mail, pw;
        mail = String.valueOf(((EditText) findViewById(R.id.editTextMail)).getText());
        pw = String.valueOf(((EditText) findViewById(R.id.editPassword)).getText());
        new LoadUserApp().execute(mail, pw);
    }

    private class LoadUserApp extends AsyncTask<String, Integer, UserApp>
    {
        private Exception exceptionToBeThrow;
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
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
                userConnected.setToken(ConnectionActivity.this, token);
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
                userConnected.setUserConnected(ConnectionActivity.this, userApp);
                Intent intent = new Intent(ConnectionActivity.this, ServicesActivity.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(ConnectionActivity.this, exceptionToBeThrow.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }
}