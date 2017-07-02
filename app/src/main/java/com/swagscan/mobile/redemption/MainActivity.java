package com.swagscan.mobile.redemption;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.swagscan.mobile.redemption.Adapter.FragmentPagerAdapter;
import com.swagscan.mobile.redemption.Fragment.BadgeFragment;
import com.swagscan.mobile.redemption.Fragment.ShopFragment;
import com.swagscan.mobile.redemption.Fragment.HistoryFragment;
import com.swagscan.mobile.redemption.Fragment.NewsFragment;
import com.swagscan.mobile.redemption.Fragment.QrFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private BadgeFragment badgeFragment;
    private QrFragment qrFragment;
    private ShopFragment cartFragment;
    private HistoryFragment historyFragment;
    private NewsFragment newsFragment;

    private ArrayList<Fragment> fragments;

    private ViewPager pager;
    private FragmentPagerAdapter pagerAdapter;

    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragments();
        initView();
    }

    private void initFragments() {
        fragments = new ArrayList<>();

        badgeFragment = new BadgeFragment();
        qrFragment = new QrFragment();
        cartFragment = new ShopFragment();
        historyFragment = new HistoryFragment();
        newsFragment = new NewsFragment();

        fragments.add(badgeFragment);
        fragments.add(qrFragment);
        fragments.add(cartFragment);
        fragments.add(historyFragment);
        fragments.add(newsFragment);
    }

    private void initView() {
        navigation = (BottomNavigationView)findViewById(R.id.main_navigation);
        //navigation.setOnNavigationItemSelectedListener(this);

        pager = (ViewPager) findViewById(R.id.main_container);
        pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager(), fragments);
        pager.setAdapter(pagerAdapter);
        pager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
