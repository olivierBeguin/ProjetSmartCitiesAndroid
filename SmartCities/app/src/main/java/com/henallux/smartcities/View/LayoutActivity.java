package com.henallux.smartcities.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.henallux.smartcities.R;
import com.henallux.smartcities.model.UserConnected;

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
            case R.id.disconnect:
                disconnect();
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

    private void disconnect()
    {
        Intent intent = new Intent(LayoutActivity.this, MainActivity.class);
        UserConnected userConnected = new UserConnected();
        userConnected.resetToken(LayoutActivity.this);
        userConnected.resetUserConnected(LayoutActivity.this);
        startActivity(intent);
    }
}
