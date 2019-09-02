package com.example.chachacha_dory;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends BaseActivity {
//    private TextView mTvHelloWorld;
    MyPageFragment myPageFragment;
    SearchFragment searchFragment;
    MyChaFragment myChaFragment;
    StartChaFragment startChaFragment;
    MyReviewFragment mMyReviewFragment;
    Context mContext;
    TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();
        myPageFragment = new MyPageFragment();
        searchFragment = new SearchFragment();
        myChaFragment = new MyChaFragment();
        startChaFragment = new StartChaFragment();
        mMyReviewFragment = new MyReviewFragment();
//        getSupportFragmentManager().beginTransaction().replace(R.id.contaner, myPageFragment).commit();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.contaner, myPageFragment).commit();

        tabs = findViewById(R.id.tabLayout);

        tabs.addTab(tabs.newTab().setCustomView(createTabView(R.drawable.tab_heart)));
        tabs.addTab(tabs.newTab().setCustomView(createTabView(R.drawable.tab_start)));
        tabs.addTab(tabs.newTab().setCustomView(createTabView(R.drawable.tab_message)));
        tabs.addTab(tabs.newTab().setCustomView(createTabView(R.drawable.tab_mypage)));

        tabs.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Fragment selected = null;
                switch (position) {
                    case 0:
                        selected = myChaFragment;
                        break;
                    case 1:
                        selected = startChaFragment;
                        Intent intent = new Intent(MainActivity.this, StartChaActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        selected = searchFragment;
                        break;
                    case 3:
                        selected = myPageFragment;
                        break;
                    default:
                        break;
                }
                MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.contaner, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contaner, fragment).commit();
    }

    private View createTabView(int tabImage) {
        View tabView = LayoutInflater.from(mContext).inflate(R.layout.custom_tab, null);
        ImageView iconImage = tabView.findViewById(R.id.iconImage);
        iconImage.setImageResource(tabImage);
        return tabView;
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
