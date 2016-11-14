package com.henallux.smartcities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;


public class MyActivitiesActivity extends LayoutActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activity);

        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("Tab one");
        tabSpec.setContent(R.id.activity_my_services_done);
        tabSpec.setIndicator("Tab one");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("Tab two");
        tabSpec.setContent(R.id.activity_my_services_given);
        tabSpec.setIndicator("Tab 2");
        tabHost.addTab(tabSpec);
    }
}
