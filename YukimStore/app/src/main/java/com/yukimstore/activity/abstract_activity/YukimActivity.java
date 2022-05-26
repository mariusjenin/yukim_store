package com.yukimstore.activity.abstract_activity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.yukimstore.YukimApp;
import com.yukimstore.R;

public abstract class YukimActivity extends AppCompatActivity {
    private ProgressDialog progDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        YukimApp.setCurrentAct(this);
    }

    public void showProgress(){
        progDialog = ProgressDialog.show( this, null, null, false, false );
        progDialog.getWindow().setBackgroundDrawable( new ColorDrawable( Color.TRANSPARENT));
        progDialog.setContentView(R.layout.progress_bar );
    }

    public void hideProgress(){
        progDialog.dismiss();
    }
}
