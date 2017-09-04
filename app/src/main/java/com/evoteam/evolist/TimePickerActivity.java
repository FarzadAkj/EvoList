package com.evoteam.evolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

/**
 * Created by programmer on 7/26/2017.
 */

public class TimePickerActivity extends AppCompatActivity {

    TimePicker timePicker ;
    TextView time ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_picker);

        timePicker = (TimePicker) findViewById(R.id.timePickerWatch);
        time = (TextView) findViewById(R.id.timeWrite);

        time.setHint("00 : 00");


        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = timePicker.getHour() ;
                int minute = timePicker.getMinute() ;

                time.setText(String.format("%d : %d" , hour , minute));
                AddToDoTaskActivity.taskTime.setText(time.getText());
            }
        });




    }
}