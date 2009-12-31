package com.blogspot.techyfruit360.nutripro;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 8/3/2019.
 */

public class ViewPageAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragmentlist=new ArrayList<>();
    private final List<String> FragmentListTitles = new ArrayList<>();

    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return fragmentlist.get(position);

    }

    @Override
    public int getCount() {

        return FragmentListTitles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return FragmentListTitles.get(position);
    }

    public void AddFragment(Fragment fragment, String title){
        fragmentlist.add(fragment);
        FragmentListTitles.add(title);
    }

}
