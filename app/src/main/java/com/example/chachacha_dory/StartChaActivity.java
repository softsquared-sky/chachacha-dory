package com.example.chachacha_dory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartChaActivity extends AppCompatActivity {
    Button nextBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_cha);
        nextBtn = (Button)findViewById(R.id.startNextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartChaActivity.this, ChaChaActivity.class);
                startActivity(intent);
            }
        });
    }
}