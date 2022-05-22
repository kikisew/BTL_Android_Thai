package com.example.btl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.btl.adapter.MyViewPagerAdapter;
import com.example.btl.adapter.ViewPagerAdapter;
import com.example.btl.fragment.FragmenInfo;
import com.example.btl.fragment.FragmenSearch;
import com.example.btl.fragment.FragmentHome;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.mHome:
                        viewPager2.setCurrentItem(0,false);
                        break;
                    case R.id.mHistory:
                        viewPager2.setCurrentItem(1,false);
                        break;
                    case R.id.mSearch:
                        viewPager2.setCurrentItem(2,false);
                        break;
                }
//                if (fragment != null)
//                    startFragment(fragment);
                return true;
            }
        });

        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(),getLifecycle());

        FragmentHome fragmentHome = new FragmentHome();
        FragmenSearch fragmenSearch = new FragmenSearch();
        FragmenInfo fragmenInfo = new FragmenInfo();

        myViewPagerAdapter.addFragment(fragmentHome);
        myViewPagerAdapter.addFragment(fragmenSearch);
        myViewPagerAdapter.addFragment(fragmenInfo);

        viewPager2.setAdapter(myViewPagerAdapter);
        viewPager2.setUserInputEnabled(false);
    }
    private void initView() {
        bottomNavigationView = findViewById(R.id.bottom_nav_bar);
        viewPager2 = findViewById(R.id.view_pager2_fragment_container);
    }
}