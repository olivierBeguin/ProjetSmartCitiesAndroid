package com.henallux.smartcities.view;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import com.astuetz.PagerSlidingTabStrip;
import com.henallux.smartcities.R;
import com.henallux.smartcities.model.PagerAdapterMyActivities;

public class MyActivitiesActivity extends LayoutActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activity);
        ViewPager viewPager = (ViewPager) findViewById(R.id.my_activities_pager);
        viewPager.setAdapter(new PagerAdapterMyActivities(getSupportFragmentManager()));

        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);
    }
}
