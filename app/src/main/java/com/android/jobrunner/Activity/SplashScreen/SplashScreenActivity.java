package com.android.jobrunner.Activity.SplashScreen;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.jobrunner.Class.TypeFaceClass;
import com.android.jobrunner.Network.MyReceiver;
import com.android.jobrunner.R;

public class SplashScreenActivity extends AppCompatActivity {

    private BroadcastReceiver MyReceiver = null;
    TextView textView_copyRight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        textView_copyRight = findViewById(R.id.textView_copyRight); TypeFaceClass.setTypeFaceTextView(textView_copyRight,getApplicationContext());
        MyReceiver = new MyReceiver();
        broadcastIntent();
    }

    public void broadcastIntent() {
        registerReceiver(MyReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(MyReceiver);
    }

    @Override
    public void onBackPressed() {}
}
