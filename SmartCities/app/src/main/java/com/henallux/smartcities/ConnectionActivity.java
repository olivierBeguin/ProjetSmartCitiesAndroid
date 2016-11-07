package com.henallux.smartcities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

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
        Intent intent = new Intent(ConnectionActivity.this, ServicesActivity.class);
        startActivity(intent);
    }

}