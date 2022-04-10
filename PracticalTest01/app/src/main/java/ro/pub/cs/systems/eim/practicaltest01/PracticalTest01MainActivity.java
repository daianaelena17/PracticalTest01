package ro.pub.cs.systems.eim.practicaltest01;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01MainActivity extends AppCompatActivity {
    Button button0;
    EditText text0;
    Button button1;
    Button button3;
    EditText text1;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_main);
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

    public class Clickme implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (view.getId() == button0.getId()) {
                String fname = text0.getText().toString();
                int count0 = Integer.parseInt(fname);
                text0.setText(String.valueOf(count0 + 1));
            } else if (view.getId() == button1.getId()) {
                String fname = text1.getText().toString();
                int count1 = Integer.parseInt(fname);
                text1.setText(String.valueOf(count1 + 1));
            } else if (view.getId() == button3.getId()) {
                intent.putExtra("TEXT0", Integer.parseInt(String.valueOf(text0.getText())));
                intent.putExtra("TEXT1", Integer.parseInt(String.valueOf(text1.getText())));
                startActivityForResult(intent, 2005);
            }
        }
    }
}