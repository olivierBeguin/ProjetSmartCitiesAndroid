package com.henallux.smartcities.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.henallux.smartcities.DAO.UserAppDAO;
import com.henallux.smartcities.R;
import com.henallux.smartcities.model.UserApp;

public class ConnectionActivity extends AppCompatActivity
{

    private Button connectionButton;
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

    }

    private void goToServicesActivity()
    {
        new LoadUserApp().execute();
        Intent intent = new Intent(ConnectionActivity.this, ServicesActivity.class);
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
                String token = userAppDAO.getUserWithMailandPw("juma", "augu12");
                Log.i("Test", token.toString());
            }
            catch (Exception e)
            {
                Log.i("Test", e.getMessage());
            }
            return userApp;
        }

        @Override
        protected void onPostExecute(UserApp userApp) {
            //Log.i("Test", userApp.toString());
        }
    }

}