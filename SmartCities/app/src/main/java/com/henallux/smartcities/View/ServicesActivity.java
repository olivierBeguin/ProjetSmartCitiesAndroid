package com.henallux.smartcities.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.astuetz.PagerSlidingTabStrip;
import com.henallux.smartcities.R;
import com.henallux.smartcities.model.PagerAdapterServices;

public class ServicesActivity extends LayoutActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        ViewPager viewPager = (ViewPager) findViewById(R.id.services_pager);
        viewPager.setAdapter(new PagerAdapterServices(getSupportFragmentManager()));

        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs_services);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);
    }
}
