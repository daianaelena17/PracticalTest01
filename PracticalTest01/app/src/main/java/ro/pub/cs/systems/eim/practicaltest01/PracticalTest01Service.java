package ro.pub.cs.systems.eim.practicaltest01;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class PracticalTest01Service extends Service {


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Started Service", "onCreate() method was invoked");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("Started Service", "onBind() method was invoked");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("Started Service", "onUnbind() method was invoked");
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d("Started Service", "onRebind() method was invoked");
    }

    @Override
    public void onDestroy() {
        Log.d("Started Service", "onDestroy() method was invoked");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle extras = intent.getExtras();
        Log.d("Started Service", "Thread.run() was invoked, PID: " + android.os.Process.myPid() + " TID: " + android.os.Process.myTid());
        if (extras != null) {
            Date currentTime = Calendar.getInstance().getTime();
            int first = extras.getInt("TEXT0");
            int second = extras.getInt("TEXT1");

            float aritm = ((float) first + (float) second) / 2;
            double geom = Math.pow(first * second, 0.5);
            Thread dedicatedThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.d("Started Service", "Thread.run() was invoked, PID: " + android.os.Process.myPid() + " TID: " + android.os.Process.myTid());
                    while (true) {
                        Random r = new Random();
                        int generator = r.nextInt();
                        Intent intent = new Intent();
                        switch (generator % 3) {
                            case 0:
                                intent.setAction("DATA_ACTION");
                                intent.putExtra("Date", currentTime.toString());
                                break;
                            case 1:
                                intent.setAction("GEOMETRIC_ACTION");
                                intent.putExtra("GEOM", geom);
                                break;
                            case 2:
                                intent.setAction("ARITHMETIC_ACTION");
                                intent.putExtra("ARITM", aritm);
                                break;
                        }
                        sendBroadcast(intent);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            dedicatedThread.start();
        }
        return START_REDELIVER_INTENT;
    }
}