package ro.pub.cs.systems.eim.practicaltest01;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01MainActivity extends AppCompatActivity {
    private static final int PRAG = 2;
    Button button0;
    EditText text0;
    Button button1;
    Button button3;
    EditText text1;
    Intent intent;
    boolean not_started;
    Intent intent2;
    Listener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_main);
        not_started = true;
        button0 = findViewById(R.id.button0);
        text0 = findViewById(R.id.text0);
        button1 = findViewById(R.id.button1);
        button3 = findViewById(R.id.button3);
        text1 = findViewById(R.id.text1);

        Clickme clickme = new Clickme();
        button0.setOnClickListener(clickme);
        button1.setOnClickListener(clickme);
        button3.setOnClickListener(clickme);
        intent = new Intent(this, PracticalTest01SecondaryActivity.class);

        intent2 = new Intent();
        ComponentName componentName = new ComponentName(getApplicationContext(), PracticalTest01Service.class);
        intent2.setComponent(componentName);

        listener = new Listener();
        IntentFilter intfil = new IntentFilter();
        intfil.addAction("DATA_ACTION");
        intfil.addAction("GEOMETRIC_ACTION");
        intfil.addAction("ARITHMETIC_ACTION");
        registerReceiver(listener, intfil);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("TEXT0", Integer.parseInt(String.valueOf(text0.getText())));
        outState.putInt("TEXT1", Integer.parseInt(String.valueOf(text1.getText())));

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        text0.setText(String.valueOf(savedInstanceState.getInt("TEXT0", 0)));
        text1.setText(String.valueOf(savedInstanceState.getInt("TEXT1", 0)));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 2005:
                if (resultCode == Activity.RESULT_OK) {
                    Toast toast = Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (resultCode == Activity.RESULT_CANCELED) {
                    Toast toast = Toast.makeText(getApplicationContext(), "CANCEL", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(intent2);
    }

    public class Clickme implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            int count0 = Integer.parseInt(text0.getText().toString());
            int count1 = Integer.parseInt(text1.getText().toString());
            if (count0 + count1 >= PRAG && not_started) {
                intent2.putExtra("TEXT0", Integer.parseInt(String.valueOf(text0.getText())));
                intent2.putExtra("TEXT1", Integer.parseInt(String.valueOf(text1.getText())));
                startService(intent2);
                not_started = false;
            }
            if (view.getId() == button0.getId()) {
                text0.setText(String.valueOf(count0 + 1));
            } else if (view.getId() == button1.getId()) {
                text1.setText(String.valueOf(count1 + 1));
            } else if (view.getId() == button3.getId()) {
                intent.putExtra("TEXT0", Integer.parseInt(String.valueOf(text0.getText())));
                intent.putExtra("TEXT1", Integer.parseInt(String.valueOf(text1.getText())));
                startActivityForResult(intent, 2005);
            }
        }
    }

    public static class Listener extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.compareTo("DATA_ACTION") == 0) {
                String extra = intent.getStringExtra("Date");
                Log.d("MyBroadcastReceiver", "Received from service MY_DATA: " + extra + "\n");
            } else if (action.compareTo("GEOMETRIC_ACTION") == 0) {
                double extra = intent.getDoubleExtra("GEOM", 0.0);
                Log.d("MyBroadcastReceiver", "Received from service MY_DATA: " + extra + "\n");
            } else if (action.compareTo("ARITHMETIC_ACTION") == 0) {
                float extra = intent.getFloatExtra("ARITM", 0.0F);
                Log.d("MyBroadcastReceiver", "Received from service MY_DATA: " + extra + "\n");
            }
        }
    }
}