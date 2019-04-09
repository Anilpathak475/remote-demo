package com.servify.remoterconstrol;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

/**
 * Created by anilpathak on 25/09/17.
 */

public class FloatingButtonService extends Service {
    WindowManager.LayoutParams params;
    private WindowManager windowManager;
    private FrameLayout frameLayout;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        createFloatingBackButton();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // to receive any data from activity
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        windowManager.removeView(frameLayout);
    }

    private void createFloatingBackButton() {

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        // LayoutInflater layoutInflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //   View view=layoutInflater.inflate(R.layout.floating_icon, null);
        params = Build.VERSION.SDK_INT >= 26 ? new WindowManager.LayoutParams(
                300,
                300,
                WindowManager.LayoutParams.TYPE_APPLICATION_PANEL,
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT) : new WindowManager.LayoutParams(
                300,
                300,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = 0;


        // windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        frameLayout = new FrameLayout(this);

        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        // Here is the place where you can inject whatever layout you want in the frame layout
        layoutInflater.inflate(R.layout.activity_main, frameLayout, false);
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Clicked", "tynhgbjhytgfedehrfedhtgrfedwfverdce");
            }
        });

        windowManager.addView(frameLayout, params);
    }
}