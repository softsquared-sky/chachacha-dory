package com.example.chachacha_dory.src.chachacha;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.chachacha_dory.R;
import com.example.chachacha_dory.config.BaseActivity;

public class ChaChaActivity extends BaseActivity {
    private ListView mListview;
    private ChaListAdapter mAdapter;
    ImageView mBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chacha);

        mListview = findViewById(R.id.chachaList);
        mBackBtn = findViewById(R.id.chachaBackBtn);

        mAdapter = new ChaListAdapter();
        mListview.setAdapter(mAdapter);

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}