package com.example.chachacha_dory.config;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chachacha_dory.R;

import java.io.InputStream;
import java.net.URL;

import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {
    public ProgressDialog mProgressDialog;

    public Drawable LoadImageFromWebOperations(String url) {
        try {
            URL url1 = new URL(url);
            InputStream is = (InputStream) url1.getContent();
            // InputStream에서 Drawable 작성
            Drawable drawable = Drawable.createFromStream(is, "");
            return drawable;
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return null;
    }

    @SuppressLint("ResourceAsColor")
    public void showCustomToast(final String message) {
        TextView tvToastMsg = new TextView(this);
        tvToastMsg.setText(message);
        tvToastMsg.setBackgroundResource(R.color.colorPrimaryDark);
        tvToastMsg.setTextColor(R.color.colorAccent);
        tvToastMsg.setTextSize(16);
        tvToastMsg.setPadding(20, 10, 20, 10);
        final Toast toastMsg = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        toastMsg.setView(tvToastMsg);
        toastMsg.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toastMsg.cancel();
            }
        }, 1000);
    }

    public void hideKeyboard(EditText et)
    {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgressDialog();
    }
}
