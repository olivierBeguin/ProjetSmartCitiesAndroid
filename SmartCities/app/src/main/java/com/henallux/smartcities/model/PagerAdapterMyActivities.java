package com.henallux.smartcities.model;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by olivierbeguin on 15/11/16.
 */

public class PagerAdapterMyActivities extends FragmentPagerAdapter
{
    private final List fragments;
    public  PagerAdapterMyActivities(FragmentManager fragmentPagerAdapter, List fragments)
    {
        super(fragmentPagerAdapter);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position)
    {
        return (Fragment)this.fragments.get(position);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }
}
