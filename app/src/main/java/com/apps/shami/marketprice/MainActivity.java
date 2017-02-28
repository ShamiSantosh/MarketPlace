package com.apps.shami.marketprice;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import com.astuetz.PagerSlidingTabStrip;

public class MainActivity extends ActionBarActivity implements FragmentSearch.OnFragmentInteractionListener, FragmentWatchlist.OnFragmentInteractionListener, FragmentSettings.OnFragmentInteractionListener
{
    private ViewPager pager;
    private PagerSlidingTabStrip tabs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentSearch fragment = new FragmentSearch();

        if(savedInstanceState != null)
        {
            Bundle arg1 = new Bundle();
            arg1.putString("product_key", getIntent().getExtras().getString("product_key"));
            fragment.setArguments(arg1);

            Bundle arg2 = new Bundle();
            arg2.putString("market_key", getIntent().getExtras().getString("market_key"));
            fragment.setArguments(arg2);
        }

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new TabPagerAdapter(getSupportFragmentManager()));

        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(pager);

    }

    @Override
    public void onBackPressed() {

        moveTaskToBack(true);
        this.finish();
        System.exit(0);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

class TabPagerAdapter extends FragmentPagerAdapter {

    private String[] titles = {"Search", "Watchlist", "Settings" };

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        Fragment fragment = null;
        if(i==0){
            fragment = new FragmentSearch();
        }
        if(i==1){
            fragment = new FragmentWatchlist();
        }
        if(i==2){
            fragment = new FragmentSettings();
        }

        return fragment;
    }

    @Override
    public int getCount()
    {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return titles[position];
    }


}
