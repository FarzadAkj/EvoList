package com.evoteam.evolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class AddToDoTaskActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

    static EditText taskName, taskDate, taskTime, taskDescription;
    CheckBox taskImportance;
    Button  submit;
    ImageButton DatePickerImageButton, TimePickerImageButton;
    Spinner taskDay;

    Task currentTask = new Task();

    String day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do_task);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Add a Task");
        }

        init();
        makingReadyTheSpinner();
    }

    private void init() {
        taskName = (EditText) findViewById(R.id.taskNameEditText);
        taskDay = (Spinner) findViewById(R.id.spinnerAddTask);
        taskDay.setOnItemSelectedListener(this);
        taskDate = (EditText) findViewById(R.id.taskDateEditText);
        taskTime = (EditText) findViewById(R.id.taskTimeEditText);
        taskDescription = (EditText) findViewById(R.id.taskDescriptionEditText);

        taskImportance = (CheckBox) findViewById(R.id.taskImportanceCheckBoxInTaskActivity);

        DatePickerImageButton = (ImageButton) findViewById(R.id.DatePicker);
        TimePickerImageButton = (ImageButton) findViewById(R.id.TimePicker);
        submit = (Button) findViewById(R.id.taskSubmitButton);

        TimePickerImageButton.setOnClickListener(this);
        DatePickerImageButton.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    public void makingReadyTheSpinner() {
        String[] days = getResources().getStringArray(R.array.days);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, days);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        taskDay.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.taskSubmitButton:
                makingReadyTheTask();
                if (isCorrectTask()){
                    MainActivity.dbManager.addTask(currentTask);
                    intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(this, "Your Task has been added." + MainActivity.dbManager.getDataBaseSize(), Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(this, "لطفا فیلدهای کارتان را کامل وارد کنید.", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.DatePicker:
                intent = new Intent(this, DatePickerActivity.class);
                startActivity(intent);
                break ;
            case R.id.TimePicker:
                intent = new Intent(this, TimePickerActivity.class);
                startActivity(intent);
                break;
        }
    }

    private boolean isCorrectTask() {
        if(!currentTask.getName().equals(" ") && !currentTask.getDay().equals(" ")
                && !currentTask.getDate().equals(" ") && !currentTask.getTime().equals(" "))
            return true;
        return false;
    }

    private void makingReadyTheTask() {

        currentTask = new Task(taskName.getText().toString(),
                day,
                taskDate.getText().toString(),
                taskTime.getText().toString(),
                taskDescription.getText().toString(),
                taskImportance.isChecked()
                );

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        day = (String)parent.getSelectedItem();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent){

    }

}

