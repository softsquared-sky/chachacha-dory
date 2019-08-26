package com.example.chachacha_dory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class MyBarActivity extends AppCompatActivity {
    private ListView mListView;
    private BarListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bar);

        mListView = (ListView)findViewById(R.id.mybarListView);
        mAdapter = new BarListAdapter();
        mListView.setAdapter(mAdapter);
        mAdapter.addBar("교촌치킨", "#활기찬, #자유로운", "시끌벅적한 분위기의 치킨집, 치킨은 교촌치킨이지!", R.drawable.cha_background);

    }
}
