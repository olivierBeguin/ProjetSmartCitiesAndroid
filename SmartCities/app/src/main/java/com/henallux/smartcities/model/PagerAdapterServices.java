package com.henallux.smartcities.model;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.henallux.smartcities.view.NeedServiceActivity;
import com.henallux.smartcities.view.SearchServiceActivity;

/**
 * Created by olivierbeguin on 28/11/16.
 */

public class PagerAdapterServices extends FragmentPagerAdapter {
    private final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[]{"Effectuer un service", "Besoin d'un service"};

    public PagerAdapterServices(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1)
            return NeedServiceActivity.newInstance(position);
        return SearchServiceActivity.newInstance(position);

    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];

    }
}
