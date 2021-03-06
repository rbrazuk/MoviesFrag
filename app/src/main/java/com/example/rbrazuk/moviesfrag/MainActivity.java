package com.example.rbrazuk.moviesfrag;

import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    FragmentPagerAdapter mFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager)findViewById(R.id.vp_movies);
        mFragmentPagerAdapter = new MoviesPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mFragmentPagerAdapter);



    }

    public static class MoviesPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_PAGES = 2;

        private static final String[] TITLES = new String[] {"Movies","Watchlist"};

        public MoviesPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return MoviesFragment.newInstance(0,"Movies");
                case 1:
                    return WatchListFragment.newInstance(1,"WatchList");
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position].toUpperCase();
        }
    }
}
