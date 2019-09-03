package com.example.chachacha_dory.src.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.chachacha_dory.R;

public class BeforeSignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_sign_up);
    }

    public void onClickImageSignUp(View v){
        switch (v.getId()){
            case R.id.beforeSignUpBackBtn:
                onBackPressed();
                break;
            case R.id.signUpBoss:
            case R.id.signUpGuest:
                Intent intent = new Intent(BeforeSignUpActivity.this, SignUpActivity.class);
                startActivity(intent);
                break;
        }
    }
}
