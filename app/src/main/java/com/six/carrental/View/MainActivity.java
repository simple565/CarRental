package com.six.carrental.View;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.six.carrental.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    ViewPager mViewPager;
    List <Fragment> mFragments;
    BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        iniView ();
    }

    private void iniView() {
        mViewPager = findViewById ( R.id.viewPager );
        mViewPager.setAdapter ( new ViewPagerAdaptor ( getSupportFragmentManager () ) );
        mFragments = new ArrayList <> ();
        mFragments.add ( new RentalFragment () );
        mFragments.add ( new AccountFragment () );
        mViewPager.addOnPageChangeListener ( this );
        mViewPager.setCurrentItem ( 0 );
        mBottomNavigationView = findViewById ( R.id.main_navigation );
        mBottomNavigationView.setOnNavigationItemSelectedListener ( new BottomNavigationView.OnNavigationItemSelectedListener () {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId ()) {
                    case R.id.navigation_home:
                        mViewPager.setCurrentItem ( 0 );
                        break;
                    case R.id.navigation_account:
                        mViewPager.setCurrentItem ( 1 );
                        break;
                    default:
                        break;
                }
                return true;
            }
        } );


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        mBottomNavigationView.getMenu ().getItem ( position ).setChecked ( true );
        //mBottomNavigationView.setSelectedItemId ( position );该方法无效

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class ViewPagerAdaptor extends FragmentPagerAdapter {

        public ViewPagerAdaptor(FragmentManager fm) {
            super ( fm );
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get ( position );
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume ();
    }
}
