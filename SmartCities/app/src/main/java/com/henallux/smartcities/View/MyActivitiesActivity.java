package com.henallux.smartcities.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toolbar;

import com.henallux.smartcities.R;
import com.henallux.smartcities.model.PagerAdapterMyActivities;

import java.util.List;
import java.util.Vector;


public class MyActivitiesActivity extends FragmentActivity
{
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_my_activity);

        List fragment = new Vector();

        fragment.add(Fragment.instantiate(this, MyServicesGivenActivity.class.getName()));
        fragment.add(Fragment.instantiate(this, MyServicesDoneActivity.class.getName()));

        this.pagerAdapter = new PagerAdapterMyActivities(super.getSupportFragmentManager(), fragment);
        ViewPager pager = (ViewPager) super.findViewById(R.id.my_activities_pager);
        pager.setAdapter(this.pagerAdapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.menu);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        Log.i("Test", "OnCreateOptionsMenu");
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
        Intent intent = new Intent(MyActivitiesActivity.this, ProfilActivity.class);
        startActivity(intent);
    }

    private void goToServicesActivity()
    {
        Intent intent = new Intent(MyActivitiesActivity.this, ServicesActivity.class);
        startActivity(intent);
    }
}
