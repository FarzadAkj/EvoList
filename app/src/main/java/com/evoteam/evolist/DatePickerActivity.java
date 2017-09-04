package com.evoteam.evolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

/**
 * Created by programmer on 7/26/2017.
 */

public class DatePickerActivity extends AppCompatActivity {

    DatePicker datePicker ;
    TextView date ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_picker);

        datePicker = (DatePicker) findViewById(R.id.datePickerCalendar);
        date = (TextView) findViewById(R.id.dateWrite);

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int year = datePicker.getYear();
                int month = datePicker.getMonth();
                int day = datePicker.getDayOfMonth();

                switch (month) {
                    case 0:
                        date.setText(String.format("%d %s %d", year, "JANUARY", day));
                        AddToDoTaskActivity.taskDate.setText(date.getText());
                        break;
                    case 1:
                        date.setText(String.format("%d %s %d", year, "FEBRUARY", day));
                        AddToDoTaskActivity.taskDate.setText(date.getText());
                        break;
                    case 2:
                        date.setText(String.format("%d %s %d", year, "MARCH", day));
                        AddToDoTaskActivity.taskDate.setText(date.getText());
                        break;
                    case 3:
                        date.setText(String.format("%d %s %d", year, "APRIL", day));
                        AddToDoTaskActivity.taskDate.setText(date.getText());
                        break;
                    case 4:
                        date.setText(String.format("%d %s %d", year, "MAY", day));

                        break;
                    case 5:
                        date.setText(String.format("%d %s %d", year, "JUNE", day));
                        AddToDoTaskActivity.taskDate.setText(date.getText());
                        break;
                    case 6:
                        date.setText(String.format("%d %s %d", year, "JULLY", day));
                        AddToDoTaskActivity.taskDate.setText(date.getText());
                        break;
                    case 7:
                        date.setText(String.format("%d %s %d", year, "AUGUST", day));
                        AddToDoTaskActivity.taskDate.setText(date.getText());
                        break;
                    case 8:
                        date.setText(String.format("%d %s %d", year, "SEPTEMBER", day));
                        AddToDoTaskActivity.taskDate.setText(date.getText());
                        break;
                    case 9:
                        date.setText(String.format("%d %s %d", year, "OCTOBER", day));
                        AddToDoTaskActivity.taskDate.setText(date.getText());
                        break;
                    case 10:
                        date.setText(String.format("%d %s %d", year, "NOVEMBER", day));
                        AddToDoTaskActivity.taskDate.setText(date.getText());
                        break;
                    case 11:
                        date.setText(String.format("%d %s %d", year, "DECEMBER", day));
                        AddToDoTaskActivity.taskDate.setText(date.getText());
                        break;
                }
            }
        });
    }
}