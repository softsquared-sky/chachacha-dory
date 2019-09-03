package com.example.chachacha_dory.config;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
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

    public void showCustomToast(final String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
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
