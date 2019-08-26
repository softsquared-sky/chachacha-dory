package com.example.chachacha_dory;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class ChaChaActivity extends Activity {
    private ListView mListview;
    private ChaListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chacha);

        mListview = (ListView)findViewById(R.id.chachaList);
        mAdapter = new ChaListAdapter();
        mListview.setAdapter(mAdapter);

        mAdapter.addCha("교촌치킨", "#활기찬, #자유로운", "시끌벅적한 분위기의 치킨집, 치킨은 교촌치킨이지!", R.drawable.cha_background);
    }
}
