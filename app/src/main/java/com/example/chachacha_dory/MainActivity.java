package com.example.chachacha_dory;

import android.os.Bundle;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    ChaChaFragment chaChaFragment;
    MyPageFragment myPageFragment;
    SearchFragment searchFragment;
    MyChaFragment myChaFragment;
    StartChaFragment startChaFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chaChaFragment = new ChaChaFragment();
        myPageFragment = new MyPageFragment();
        searchFragment = new SearchFragment();
        myChaFragment = new MyChaFragment();
        startChaFragment = new StartChaFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contaner, myPageFragment).commit();

        TabLayout tabs = findViewById(R.id.tabLayout);
        tabs.addTab(tabs.newTab().setText("하트"));
        tabs.addTab(tabs.newTab().setText("차차차"));
        tabs.addTab(tabs.newTab().setText("검색"));
        tabs.addTab(tabs.newTab().setText("마이페이지"));

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
