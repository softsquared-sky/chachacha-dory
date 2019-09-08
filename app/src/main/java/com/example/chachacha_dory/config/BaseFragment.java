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

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {
    public ProgressDialog mProgressDialog;

    public void showCustomToast(final String message) {
        TextView tvToastMsg = new TextView(getActivity());
        tvToastMsg.setText(message);
        tvToastMsg.setBackgroundResource(R.color.colorPrimaryDark);
        tvToastMsg.setTextColor(Color.rgb(255, 120, 137));
        tvToastMsg.setTextSize(14);
        tvToastMsg.setPadding(20, 10, 20, 10);
        final Toast toastMsg = Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT);
        toastMsg.setView(tvToastMsg);
        toastMsg.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toastMsg.cancel();
            }
        }, 1000);

    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity());
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
