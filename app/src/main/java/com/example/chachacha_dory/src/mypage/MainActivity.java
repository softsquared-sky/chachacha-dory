package com.example.chachacha_dory.src.mypage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.chachacha_dory.R;
import com.example.chachacha_dory.config.BaseActivity;
import com.example.chachacha_dory.src.map.MapFragment;
import com.example.chachacha_dory.src.mychachacha.MyChaFragment;
import com.example.chachacha_dory.src.chachacha.StartChaActivity;
import com.example.chachacha_dory.src.search.SearchFragment;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends BaseActivity {
    MyPageFragment mMyPageFragment;
    SearchFragment mSearchFragment;
    MyChaFragment mMyChaFragment;
    MapFragment mMapFragment;
    MyPageReviewFragment mMyPageReviewFragment;
    Context mContext;
    TabLayout mTabs;
    Fragment mSelected;
    private BackPressCloseHandler mBackPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        int what = intent.getIntExtra("what", 4);

        mContext = getApplicationContext();
        mMapFragment = new MapFragment();
        mMyPageFragment = new MyPageFragment();
        mSearchFragment = new SearchFragment();
        mMyChaFragment = new MyChaFragment();
        mMyPageReviewFragment = new MyPageReviewFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, mMyPageFragment).commit();

        mTabs = findViewById(R.id.tabLayout);

        mTabs.addTab(mTabs.newTab().setIcon(R.drawable.tab_map));
        mTabs.addTab(mTabs.newTab().setIcon(R.drawable.tab_heart));
        mTabs.addTab(mTabs.newTab().setIcon(R.drawable.tab_start));
        mTabs.addTab(mTabs.newTab().setIcon(R.drawable.tab_message));
        mTabs.addTab(mTabs.newTab().setIcon(R.drawable.tab_mypage));

        switch (what){
            case 1:
                mTabs.getTabAt(1).setIcon(R.drawable.tab_heart_select);
                mSelected = mMyChaFragment;
                break;
            case 3:
                mTabs.getTabAt(3).setIcon(R.drawable.tab_search_selected);
                mSelected = mSearchFragment;
                break;
            case 4:
                mTabs.getTabAt(4).setIcon(R.drawable.tab_mypage_selected);
                mSelected = mMyPageFragment;
                break;
        }

        mTabs.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mTabs.getTabAt(4).setIcon(R.drawable.tab_mypage);
                mTabs.getTabAt(1).setIcon(R.drawable.tab_heart);
                mTabs.getTabAt(3).setIcon(R.drawable.tab_message);
                int position = tab.getPosition();
                switch (position) {
                    case 0:
                        mSelected = mMapFragment;
                        mTabs.getTabAt(0).setIcon(R.drawable.tab_map_selected);
                        break;
                    case 1:
                        mSelected = mMyChaFragment;
                        mTabs.getTabAt(1).setIcon(R.drawable.tab_heart_select);
                        break;
                    case 2:
                        Intent intent = new Intent(MainActivity.this, StartChaActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        mSelected = mSearchFragment;
                        mTabs.getTabAt(3).setIcon(R.drawable.tab_search_selected);
                        break;
                    case 4:
                        mSelected = mMyPageFragment;
                        mTabs.getTabAt(4).setIcon(R.drawable.tab_mypage_selected);
                        break;
                    default:
                        break;
                }
                MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container, mSelected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position) {
                    case 0:
                        mTabs.getTabAt(0).setIcon(R.drawable.tab_map);
                        break;
                    case 1:
                        mTabs.getTabAt(1).setIcon(R.drawable.tab_heart);
                        break;
                    case 2:
                        break;
                    case 3:
                        mTabs.getTabAt(3).setIcon(R.drawable.tab_message);
                        break;
                    case 4:
                        mTabs.getTabAt(4).setIcon(R.drawable.tab_mypage);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position) {
                    case 2:
                        Intent intent = new Intent(MainActivity.this, StartChaActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container, mMyPageFragment).commit();
                        break;
                }
            }
        });

        mBackPressCloseHandler = new BackPressCloseHandler(this);
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment).commit();
    }

    public void hideKeyboard(EditText et)
    {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        mBackPressCloseHandler.onBackPressed();
    }
}
