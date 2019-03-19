package com.android.jobrunner.Network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;
import com.android.jobrunner.Activity.IntroductionScreen.IntroductionActivity;
import es.dmoral.toasty.Toasty;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String status = NetworkUtil.getConnectivityStatusString(context);
        if(status.isEmpty()) {
            status="No Internet Connection";
            Toasty.error(context, status, Toast.LENGTH_SHORT, true).show();
        }
        if(status.equals("No internet is available")){
            Toasty.error(context, status, Toast.LENGTH_SHORT, true).show();
        } else{
            Toasty.success(context, status, Toast.LENGTH_SHORT, true).show();
            next(context);
        }
    }


    public void next(final Context context){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent next = new Intent(context.getApplicationContext(), IntroductionActivity.class);
                context.startActivity(next);
            }
        }, 3500);
    }
}