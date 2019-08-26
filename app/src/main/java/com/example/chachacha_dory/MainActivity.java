package com.example.chachacha_dory;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.chachacha_dory.R;
import com.example.chachacha_dory.BaseActivity;
import com.example.chachacha_dory.MainActivityView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends BaseActivity implements MainActivityView {
    private TextView mTvHelloWorld;
    MyPageFragment myPageFragment;
    SearchFragment searchFragment;
    MyChaFragment myChaFragment;
    StartChaFragment startChaFragment;
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
        getSupportFragmentManager().beginTransaction().replace(R.id.contaner, myPageFragment).commit();

        tabs = (TabLayout)findViewById(R.id.tabLayout);

        tabs.addTab(tabs.newTab().setCustomView(createTabView(R.drawable.tab_heart)));
        tabs.addTab(tabs.newTab().setCustomView(createTabView(R.drawable.tab_start)));
        tabs.addTab(tabs.newTab().setCustomView(createTabView(R.drawable.tab_message)));
        tabs.addTab(tabs.newTab().setCustomView(createTabView(R.drawable.tab_mypage)));

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Fragment selected = null;
                switch (position){
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

    private View createTabView(int tabImage) {
        View tabView = LayoutInflater.from(mContext).inflate(R.layout.custom_tab, null);
        ImageView iconImage = (ImageView)tabView.findViewById(R.id.iconImage);
        iconImage.setImageResource(tabImage);
        return tabView;
    }

    private void tryGetTest() {
        showProgressDialog();

        final MainService mainService = new MainService(this);
        mainService.getTest();
    }

    @Override
    public void validateSuccess(String text) {
        hideProgressDialog();
        mTvHelloWorld.setText(text);
    }

    @Override
    public void validateFailure(@Nullable String message) {
        hideProgressDialog();
//        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

    public void customOnClick(View view) {
        switch (view.getId()) {

            default:
                break;
        }
    }
}
