package com.henallux.smartcities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ServicesActivity extends LayoutActivity
{

    private Button searchServiceButton, offerServiceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        searchServiceButton = (Button) findViewById(R.id.searchServiceButton);
        searchServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSearchServiceActivity();
            }
        });
        offerServiceButton = (Button) findViewById(R.id.offerServiceButton);
        offerServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToOfferServiceActivity();
            }
        });
    }

    private void goToSearchServiceActivity()
    {
        Intent intent = new Intent(ServicesActivity.this, SearchServiceActivity.class);
        startActivity(intent);
    }

    private void goToOfferServiceActivity()
    {
        Intent intent = new Intent(ServicesActivity.this, OfferServiceActivity.class);
        startActivity(intent);
    }



}
