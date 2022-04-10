package ro.pub.cs.systems.eim.practicaltest01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PracticalTest01SecondaryActivity extends AppCompatActivity {

    Button button00;
    EditText text00;
    Button button01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_secondary);
        text00 = findViewById(R.id.text00);
        button00 = findViewById(R.id.button00);
        button01 = findViewById(R.id.button01);
        Clickme clickme = new Clickme();
        button00.setOnClickListener(clickme);
        button01.setOnClickListener(clickme);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            text00.setText(String.valueOf(extras.getInt("TEXT0") + extras.getInt("TEXT1")));
        }
    }

    public class Clickme implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (view.getId() == button00.getId()) {
                Intent intentToParent = new Intent();
                setResult(RESULT_OK, intentToParent);
                finish();
            } else if (view.getId() == button01.getId()) {
                Intent intentToParent = new Intent();
                setResult(RESULT_CANCELED, intentToParent);
                finish();
            }
        }
    }
}