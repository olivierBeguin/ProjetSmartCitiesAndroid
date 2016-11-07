package com.henallux.smartcities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class LayoutActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.services:
                goToServicesActivity();
                return true;
            case R.id.profil:
                goToProfilActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void goToProfilActivity()
    {
        Intent intent = new Intent(LayoutActivity.this, ProfilActivity.class);
        startActivity(intent);
    }

    private void goToServicesActivity()
    {
        Intent intent = new Intent(LayoutActivity.this, ServicesActivity.class);
        startActivity(intent);
    }
}
