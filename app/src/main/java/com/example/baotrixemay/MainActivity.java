package com.example.baotrixemay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.baotrixemay.fragment.GiaoDienChinh.ChatFragment;
import com.example.baotrixemay.fragment.GiaoDienChinh.HomeFragment;
import com.example.baotrixemay.fragment.GiaoDienChinh.ViewpagerApdapter;
import com.example.lib.Repository.Methods;
import com.example.lib.Repository.RetrofitClient;
import com.example.lib.model.Loaixe;
import com.example.lib.model.test;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager= findViewById(R.id.view_pager);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        ViewpagerApdapter viewpagerApdapter = new ViewpagerApdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewpagerApdapter);
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.menu_tab1).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.menu_tab2).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.menu_tab3).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.menu_tab4).setChecked(true);
                        break;
                    case 4:
                        bottomNavigationView.getMenu().findItem(R.id.menu_tab5).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_tab1:
                        mViewPager.setCurrentItem(0);
                        HomeFragment homeFragment = (HomeFragment) mViewPager.getAdapter().instantiateItem(mViewPager,0);
                        break;
                    case R.id.menu_tab2:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.menu_tab3:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.menu_tab4:
                        mViewPager.setCurrentItem(3);
                        break;
                    case R.id.menu_tab5:
                        mViewPager.setCurrentItem(4);
                        ChatFragment chatFragment = (ChatFragment) mViewPager.getAdapter().instantiateItem(mViewPager,4);
                        chatFragment.reloadData();
                        break;
                }
                return false;
            }
        });
    }
}