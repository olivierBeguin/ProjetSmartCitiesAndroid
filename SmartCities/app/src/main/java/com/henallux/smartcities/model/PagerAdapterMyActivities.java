package com.henallux.smartcities.model;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.henallux.smartcities.R;
import com.henallux.smartcities.view.MyServicesDoneActivity;
import com.henallux.smartcities.view.MyServicesGivenActivity;

import java.util.List;

/**
 * Created by olivierbeguin on 15/11/16.
 */

public class PagerAdapterMyActivities extends FragmentPagerAdapter {
    private final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] {"Service effectué", "Service reçu"};

    public PagerAdapterMyActivities(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1)
            return MyServicesDoneActivity.newInstance(position);
        return MyServicesGivenActivity.newInstance(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];

    }
}
