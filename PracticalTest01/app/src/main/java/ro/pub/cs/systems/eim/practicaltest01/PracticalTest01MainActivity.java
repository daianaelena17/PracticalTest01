package ro.pub.cs.systems.eim.practicaltest01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;

public class PracticalTest01MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_main);

        int count0 = 0;
        final Button button = findViewById(R.id.button0);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                count0++;
                String fname = ((EditText)findViewById(R.id.text0)).getText().toString();
                
                // Code here executes on main thread after user presses button
            }
        });
    }


}