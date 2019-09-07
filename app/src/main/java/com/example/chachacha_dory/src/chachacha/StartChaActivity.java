package com.example.chachacha_dory.src.chachacha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.chachacha_dory.R;

public class StartChaActivity extends AppCompatActivity {
    int mPeopleNum;
    String mMood;
    StringBuilder mKind = new StringBuilder();
    Button mMoodFree, mMoodActive, mMoodDating, mMoodQuiet, mMoodComfort, mMoodCozy, mMoodAnything, mMoodDark, mPeople1, mPeople2, mPeople3, mPeople4, mPeople5, mPeople6;
    ImageView mBar, mChicken, mPizza, mIzakaya, mMeat, mChinese, mFish, mPojangMacha, mKoreanPancake, mPorkLeg, mHope, mFusionMeal;

    boolean mCheckKind[];
    String mKindName[] = {"bar", "치킨", "피자", "이자카야", "고깃집", "중국집", "횟집", "포장마차", "전집", "족발보쌈", "호프집", "퓨전요리"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_cha);

        mMoodFree = findViewById(R.id.moodFree);
        mMoodActive = findViewById(R.id.moodActive);
        mMoodComfort = findViewById(R.id.moodComfort);
        mMoodCozy = findViewById(R.id.moodCozy);
        mMoodDark = findViewById(R.id.moodDark);
        mMoodDating = findViewById(R.id.moodDating);
        mMoodQuiet = findViewById(R.id.moodQuiet);
        mMoodAnything = findViewById(R.id.moodAnything);

        mPeople1 = findViewById(R.id.people1);
        mPeople2 = findViewById(R.id.people2);
        mPeople3 = findViewById(R.id.people3);
        mPeople4 = findViewById(R.id.people4);
        mPeople5 = findViewById(R.id.people5);
        mPeople6 = findViewById(R.id.people6);

        mBar = findViewById(R.id.bar);
        mChicken = findViewById(R.id.chicken);
        mPizza = findViewById(R.id.pizza);
        mIzakaya = findViewById(R.id.izakaya);
        mMeat = findViewById(R.id.meat);
        mChinese = findViewById(R.id.chinese);
        mFish = findViewById(R.id.fish);
        mKoreanPancake = findViewById(R.id.koreanPancake);
        mPorkLeg = findViewById(R.id.porkLegs);
        mHope = findViewById(R.id.hope);
        mFusionMeal = findViewById(R.id.fusionMeal);
        mPojangMacha = findViewById(R.id.pojangMacha);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCheckKind = new boolean[12];
    }

    public void onClickPeople(View v) {
        onClearPeople();
        switch (v.getId()) {
            case R.id.people1:
                mPeopleNum=1;
                mPeople1.setTextColor(Color.WHITE);
                mPeople1.setBackgroundResource(R.drawable.roundback_border);
                break;
            case R.id.people2:
                mPeopleNum =2;
                mPeople2.setTextColor(Color.WHITE);
                mPeople2.setBackgroundResource(R.drawable.roundback_border);
                break;
            case R.id.people3:
                mPeopleNum=3;
                mPeople3.setTextColor(Color.WHITE);
                mPeople3.setBackgroundResource(R.drawable.roundback_border);
                break;
            case R.id.people4:
                mPeopleNum=4;
                mPeople4.setTextColor(Color.WHITE);
                mPeople4.setBackgroundResource(R.drawable.roundback_border);
                break;
            case R.id.people5:
                mPeopleNum=5;
                mPeople5.setTextColor(Color.WHITE);
                mPeople5.setBackgroundResource(R.drawable.roundback_border);
                break;
            case R.id.people6:
                mPeopleNum=6;
                mPeople6.setTextColor(Color.WHITE);
                mPeople6.setBackgroundResource(R.drawable.roundback_border);
                break;
        }
    }

    public void onClickMood(View v) {
        onClearMood();
        switch (v.getId()) {
            case R.id.moodFree:
                mMood = "#자유로운";
                mMoodFree.setBackgroundResource(R.drawable.round_whitegrey);
                mMoodFree.setTextColor(Color.rgb(140, 140, 140));
                break;
            case R.id.moodComfort:
                mMood = "#편안한";
                mMoodComfort.setBackgroundResource(R.drawable.round_whitegrey);
                mMoodComfort.setTextColor(Color.rgb(140, 140, 140));
                break;
            case R.id.moodDark:
                mMood = "#어두운";
                mMoodDark.setBackgroundResource(R.drawable.round_whitegrey);
                mMoodDark.setTextColor(Color.rgb(140, 140, 140));
                break;
            case R.id.moodCozy:
                mMood = "#아늑한";
                mMoodCozy.setBackgroundResource(R.drawable.round_whitegrey);
                mMoodCozy.setTextColor(Color.rgb(140, 140, 140));
                break;
            case R.id.moodActive:
                mMood = "#활기찬";
                mMoodActive.setBackgroundResource(R.drawable.round_whitegrey);
                mMoodActive.setTextColor(Color.rgb(140, 140, 140));
                break;
            case R.id.moodQuiet:
                mMood = "#조용한";
                mMoodQuiet.setBackgroundResource(R.drawable.round_whitegrey);
                mMoodQuiet.setTextColor(Color.rgb(140, 140, 140));
                break;
            case R.id.moodDating:
                mMood = "#데이트하기 좋은";
                mMoodDating.setBackgroundResource(R.drawable.round_whitegrey);
                mMoodDating.setTextColor(Color.rgb(140, 140, 140));
                break;
            case R.id.moodAnything:
                mMood = "상관없음";
                mMoodAnything.setBackgroundResource(R.drawable.round_whitegrey);
                mMoodAnything.setTextColor(Color.rgb(140, 140, 140));
                break;
        }
    }

    public void onClickKind(View v) {
        switch (v.getId()) {
            case R.id.bar:
                if(mCheckKind[0]){
                   mBar.setBackgroundResource(0);
                   mCheckKind[0] = false;
                }else {
                    mBar.setBackgroundResource(R.drawable.roundback_border);
                    mCheckKind[0] = true;
                }
                break;
            case R.id.chicken:
                if(mCheckKind[1]){
                    mChicken.setBackgroundResource(0);
                    mCheckKind[1] = false;
                }else {
                    mChicken.setBackgroundResource(R.drawable.roundback_border);
                    mCheckKind[1] = true;
                }
                break;
            case R.id.pizza:
                if(mCheckKind[2]){
                    mPizza.setBackgroundResource(0);
                    mCheckKind[2] = false;
                }else {
                    mPizza.setBackgroundResource(R.drawable.roundback_border);
                    mCheckKind[2] = true;
                }
                break;
            case R.id.izakaya:
                if(mCheckKind[3]){
                    mIzakaya.setBackgroundResource(0);
                    mCheckKind[3] = false;
                }else {
                    mIzakaya.setBackgroundResource(R.drawable.roundback_border);
                    mCheckKind[3] = true;
                }
                break;
            case R.id.meat:
                if(mCheckKind[4]){
                    mMeat.setBackgroundResource(0);
                    mCheckKind[4] = false;
                }else {
                    mMeat.setBackgroundResource(R.drawable.roundback_border);
                    mCheckKind[4] = true;
                }
                break;
            case R.id.chinese:
                if(mCheckKind[5]){
                    mChinese.setBackgroundResource(0);
                    mCheckKind[5] = false;
                }else {
                    mChinese.setBackgroundResource(R.drawable.roundback_border);
                    mCheckKind[5] = true;
                }
                break;
            case R.id.fish:
                if(mCheckKind[6]){
                    mFish.setBackgroundResource(0);
                    mCheckKind[6] = false;
                }else {
                    mFish.setBackgroundResource(R.drawable.roundback_border);
                    mCheckKind[6] = true;
                }
                break;
            case R.id.pojangMacha:
                if(mCheckKind[7]){
                    mPojangMacha.setBackgroundResource(0);
                    mCheckKind[7] = false;
                }else {
                    mPojangMacha.setBackgroundResource(R.drawable.roundback_border);
                    mCheckKind[7] = true;
                }
                break;
            case R.id.koreanPancake:
                if(mCheckKind[8]){
                    mKoreanPancake.setBackgroundResource(0);
                    mCheckKind[8] = false;
                }else {
                    mKoreanPancake.setBackgroundResource(R.drawable.roundback_border);
                    mCheckKind[8] = true;
                }
                break;
            case R.id.porkLegs:
                if(mCheckKind[9]){
                    mPorkLeg.setBackgroundResource(0);
                    mCheckKind[9] = false;
                }else {
                    mPorkLeg.setBackgroundResource(R.drawable.roundback_border);
                    mCheckKind[9] = true;
                }
                break;
            case R.id.hope:
                if(mCheckKind[10]){
                    mHope.setBackgroundResource(0);
                    mCheckKind[10] = false;
                }else {
                    mHope.setBackgroundResource(R.drawable.roundback_border);
                    mCheckKind[10] = true;
                }
                break;
            case R.id.fusionMeal:
                if(mCheckKind[11]){
                    mFusionMeal.setBackgroundResource(0);
                    mCheckKind[11] = false;
                }else {
                    mFusionMeal.setBackgroundResource(R.drawable.roundback_border);
                    mCheckKind[11] = true;
                }
                break;
        }
    }

    public void onStartChaClick(View v) {
        switch (v.getId()) {
            case R.id.backBtn4:
                onBackPressed();
                break;
            case R.id.startNextBtn:
                for(int i = 0;i<12;i++){
                    if(mCheckKind[i]){
                        mKind.append(mKindName[i]).append(" ");
                    }
                }
                Log.d("결과 종류", String.valueOf(mKind));
                Intent intent = new Intent(StartChaActivity.this, ChaChaActivity.class);
                intent.putExtra("mood", mMood);
                intent.putExtra("people", mPeopleNum);
                intent.putExtra("kind", String.valueOf(mKind));
                startActivity(intent);
                break;
        }
    }

    private void onClearPeople(){
        mPeople1.setBackgroundResource(R.drawable.round_border);
        mPeople2.setBackgroundResource(R.drawable.round_border);
        mPeople3.setBackgroundResource(R.drawable.round_border);
        mPeople4.setBackgroundResource(R.drawable.round_border);
        mPeople5.setBackgroundResource(R.drawable.round_border);
        mPeople6.setBackgroundResource(R.drawable.round_border);

        mPeople1.setTextColor(Color.rgb(140, 140, 140));
        mPeople2.setTextColor(Color.rgb(140, 140, 140));
        mPeople3.setTextColor(Color.rgb(140, 140, 140));
        mPeople4.setTextColor(Color.rgb(140, 140, 140));
        mPeople5.setTextColor(Color.rgb(140, 140, 140));
        mPeople6.setTextColor(Color.rgb(140, 140, 140));
    }

    private void onClearMood(){
        mMoodAnything.setBackgroundResource(R.drawable.btn_round_border);
        mMoodDating.setBackgroundResource(R.drawable.btn_round_border);
        mMoodDark.setBackgroundResource(R.drawable.btn_round_border);
        mMoodQuiet.setBackgroundResource(R.drawable.btn_round_border);
        mMoodCozy.setBackgroundResource(R.drawable.btn_round_border);
        mMoodFree.setBackgroundResource(R.drawable.btn_round_border);
        mMoodComfort.setBackgroundResource(R.drawable.btn_round_border);
        mMoodActive.setBackgroundResource(R.drawable.btn_round_border);
        mMoodAnything.setTextColor(Color.WHITE);
        mMoodDating.setTextColor(Color.WHITE);
        mMoodQuiet.setTextColor(Color.WHITE);
        mMoodDark.setTextColor(Color.WHITE);
        mMoodCozy.setTextColor(Color.WHITE);
        mMoodFree.setTextColor(Color.WHITE);
        mMoodComfort.setTextColor(Color.WHITE);
        mMoodActive.setTextColor(Color.WHITE);
    }
}
