package com.henallux.smartcities.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.henallux.smartcities.R;

public class RegisterActivity extends AppCompatActivity
{

    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerButton = (Button) findViewById(R.id.registerButtonRegister);
        registerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {goToServicesActivity();}
        });
    }

    private void goToServicesActivity()
    {
        Intent intent = new Intent(RegisterActivity.this, ServicesActivity.class);
        startActivity(intent);
    }

}
