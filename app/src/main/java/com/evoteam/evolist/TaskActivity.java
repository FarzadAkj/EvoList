package com.evoteam.evolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class TaskActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher, CompoundButton.OnCheckedChangeListener {

    EditText taskName, taskDay, taskDate, taskTime, taskDescription;
    CheckBox taskImportance;
    Button taskDateButton, submit;
    Bundle task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        init();
        getTheTask();
        changeListener();
        Toast.makeText(this, String.valueOf(false == false), Toast.LENGTH_SHORT).show();
    }

    private void init() {
        taskName = (EditText) findViewById(R.id.taskNameEditTextInTaskActivity);
        taskDay = (EditText) findViewById(R.id.taskDayEditTextInTaskActivity);
        taskDate = (EditText) findViewById(R.id.taskDateEditTextInTaskActivity);
        taskTime = (EditText) findViewById(R.id.taskTimeEditTextInTaskActivity);
        taskDescription = (EditText) findViewById(R.id.taskDescriptionEditTextInTaskActivity);

        taskImportance = (CheckBox) findViewById(R.id.taskImportanceCheckBoxInTaskActivity);

        taskDateButton = (Button) findViewById(R.id.taskDateButtonInTaskActivity);
        submit = (Button) findViewById(R.id.taskSubmitButtonInTaskActivity);
        submit.setOnClickListener(this);
        taskDateButton.setOnClickListener(this);

        task = new Bundle();

        taskName.addTextChangedListener(this);
        taskDay.addTextChangedListener(this);
        taskDate.addTextChangedListener(this);
        taskTime.addTextChangedListener(this);
        taskDescription.addTextChangedListener(this);
        taskImportance.setOnCheckedChangeListener(this);
    }

    private void getTheTask() {
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            if(extras.containsKey("task")){
                task = extras.getBundle("task");
                setTaskInActivity(task);
            }
        }
    }

    private void setTaskInActivity(Bundle taskBundle) {
        taskName.setText(taskBundle.getString("name"));
        taskDay.setText(taskBundle.getString("day"));
        taskDate.setText(taskBundle.getString("date"));
        taskTime.setText(taskBundle.getString("time"));
        taskDescription.setText(taskBundle.getString("description"));
        taskImportance.setChecked(taskBundle.getBoolean("importance"));
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        changeListener();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        changeListener();
    }

    @Override
    public void afterTextChanged(Editable s) {
        changeListener();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        changeListener();
    }

    private void changeListener() {

        if(        !taskName.getText().toString().equals(task.getString("name"))
                || !taskDay.getText().toString().equals(task.getString("day"))
                || !taskDate.getText().toString().equals(task.getString("date"))
                || !taskTime.getText().toString().equals(task.getString("time"))
                || !taskDescription.getText().toString().equals(task.getString("description"))
                || taskImportance.isChecked() != task.getBoolean("importance")
                ){
            submit.setAlpha(1.0f);
        }else{
            submit.setAlpha(0.2f);
        }
    }
}
