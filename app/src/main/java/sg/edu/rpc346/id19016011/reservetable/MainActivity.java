package sg.edu.rpc346.id19016011.reservetable;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name,phone, pax;
    Button reserve, reset;
    DatePicker dp;
    TimePicker tp;
    CheckBox cbox;
    private Object MainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        phone =findViewById(R.id.phonenum);
        pax = findViewById(R.id.pax);
        reserve = findViewById(R.id.reserve);
        reset = findViewById(R.id.reset);
        dp= findViewById(R.id.datePicker);
        tp= findViewById(R.id.timePicker);
        cbox = findViewById(R.id.smokingArea);

        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().trim().length() != 0 && pax.getText().toString().trim().length() != 0 && phone.getText().toString().trim().length() != 0) {
                    String fname = name.getText().toString().trim();
                    String people = pax.getText().toString().trim();
                    String pnum = phone.getText().toString().trim();
                    int year = dp.getYear();
                    int month = dp.getMonth();
                    int day = dp.getDayOfMonth();
                    int hour = tp.getCurrentHour();
                    int minute = tp.getCurrentMinute();
                    String smoking = "";
                    if (cbox.isChecked()) {
                        smoking = cbox.getText().toString().trim();
                    } else {
                        smoking = cbox.getText().toString().trim();
                    }
                    String submitpg = String.format("Name: %s\n Phone: %d\n Size of Group: %d\n Type of Table: %s\n Date: %d/%d/%d\n Time: %d:%d",fname, pnum, people, smoking, day, month, year, hour, minute);
                    Toast confirm = Toast.makeText(MainActivity.this, submitpg, Toast.LENGTH_LONG);
                }
                else {
                    Toast.makeText((Context) MainActivity, "One or more of the fields are empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if(!(hourOfDay >= 10 && hourOfDay<=20)){
                    Toast.makeText(MainActivity.this, "Valid timing to book is only from 10am to 8pm ",Toast.LENGTH_SHORT);
                    updateTime(8,0);
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetAll();
            }
        });

        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private void updateTime(int hour, int min){
        tp.setCurrentHour(hour);
        tp.setCurrentMinute(min);
    }

    private void resetAll(){
        updateTime(7,30);
        dp.updateDate(2020,6,1);
        name.setText("");
        phone.setText("");
        pax.setText("");
        cbox.setChecked(false);
    }
}

