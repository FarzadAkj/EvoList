package com.evoteam.evolist;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, TextWatcher {

    EditText firstName, lastName, city, emailAddress, username, password, confirmPassword;
    CheckBox agreementCheckBox;
    Button signUpButton;

    User currentUser = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
        setDefaultsOfTheLayout();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Sign Up");
        }

    }

    private void init() {
        firstName = (EditText) findViewById(R.id.firstNameEditText);
        lastName = (EditText) findViewById(R.id.lastNameEditText);
        city = (EditText) findViewById(R.id.cityEditText);
        emailAddress = (EditText) findViewById(R.id.emailEditText);
        username = (EditText) findViewById(R.id.choosingUsernameEditText);
        password = (EditText) findViewById(R.id.choosingPasswordEditText);
        password.addTextChangedListener(this);
        confirmPassword = (EditText) findViewById(R.id.confirmPasswordEditText);
        confirmPassword.addTextChangedListener(this);
        agreementCheckBox = (CheckBox) findViewById(R.id.licenseAgreementCheckBox);
        agreementCheckBox.setChecked(false);
        agreementCheckBox.setOnCheckedChangeListener(this);
        signUpButton = (Button) findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(this);
    }

    private void setDefaultsOfTheLayout() {
        agreementCheckBox.setChecked(false);
        signUpButton.setAlpha(0.2f);

    }

    @Override
    public void onClick(View v) {
        getDataAndSetUserProfile();
        if(v.getAlpha() == 1.0){
            if(confirmPassword.getCurrentTextColor() == Color.parseColor("GREEN")) {

                if (correctData()) {
                    if (HttpConnectionManager.isOnline(SignUpActivity.this)) {
                        String signUpValues = getSignUpString();

                        myTask sendSignUp = new myTask(SignUpActivity.this);
                        Log.d("***", signUpValues);
                        sendSignUp.execute(signUpValues);

//                        SystemClock.sleep(1000);


                    } else {
                        Toast.makeText(this, "لطفا دسترسی خود را به اینترنت چک کنید.", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(this, "لطفا مطمئن شوید که فیلدهای ضروری را درست پر کرده اید.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }else{
                Toast.makeText(this, "رمز کاربری خود را تایید کنید.", Toast.LENGTH_SHORT).show();
            }

        }else{
            Animation shake = AnimationUtils.loadAnimation(SignUpActivity.this, R.anim.shake);
            agreementCheckBox.startAnimation(shake);
            Toast.makeText(this, "not next level!", Toast.LENGTH_SHORT).show();
            return;
        }
    }


    private boolean correctData() {
        if(!currentUser.getFirstName().equals(" ")  && !currentUser.getFamilyName().equals(" ")
                && !currentUser.getEmailAddress().equals(" ") && !currentUser.getUsername().equals(" ")
                && !currentUser.getPassword().equals(" "))
            return true;
        return false;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            signUpButton.setAlpha(1.0f);
        }else{
            signUpButton.setAlpha(0.2f);
        }
    }

    public void getDataAndSetUserProfile() {
        try {
            currentUser.setFirstName(firstName.getText().toString());
            currentUser.setFamilyName(lastName.getText().toString());
            currentUser.setCityName(city.getText().toString());
            currentUser.setEmailAddress(emailAddress.getText().toString());
            currentUser.setUsername(username.getText().toString());
            currentUser.setLicenseAgreement(agreementCheckBox.isChecked());
            if (password.getText().toString().equals(confirmPassword.getText().toString()))
                currentUser.setPassword(password.getText().toString());
            else
                currentUser.setPassword(" ");
        }catch (Exception e){
            Toast.makeText(this, "لطفا تمام فیلدهای ضروری را پر کنید.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        if(confirmPassword.getText().toString().equals(password.getText().toString()))
            confirmPassword.setTextColor(Color.parseColor("GREEN"));
        else
            confirmPassword.setTextColor(Color.parseColor("RED"));


    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private String getSignUpString() {
        String total = "";
        total += "first" + "=" + currentUser.getFirstName() + "&";
        total += "last" + "=" + currentUser.getFamilyName() + "&";
        total += "email" + "=" + currentUser.getEmailAddress() + "&";
        total += "city" + "=" + currentUser.getCityName() + "&";
        total += "username" + "=" + currentUser.getUsername() + "&";
        total += "password" + "=" + currentUser.getPassword();

        return total;
    }

    private void whatToDo(String response) {

        if (response.equals("no change")){
            Toast.makeText(this, "دوباره تلاش کنید.", Toast.LENGTH_SHORT).show();
        }else if (response.equals("user registered")){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }else if (response.equals("Username already exists")){
            Toast.makeText(this, "نام کاربری قبلا استفاده شده است.", Toast.LENGTH_SHORT).show();
            username.requestFocus();
        }else if(response.equals("Email already exists")) {
            Toast.makeText(this, "این ایمیل قبلا ثبت شده است.", Toast.LENGTH_SHORT).show();
            emailAddress.requestFocus();
        }else{
            Toast.makeText(this, "خطا در اتصال با سرور...", Toast.LENGTH_SHORT).show();
        }
    }

    private class myTask extends AsyncTask<String, String, String>{

        private ProgressDialog authProgressDialog;

        private myTask(Activity appActivity) {
            authProgressDialog = new ProgressDialog(appActivity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            authProgressDialog.setMessage("در حال برقراری ارتباط با سرور ...");
            authProgressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            String res;
            res = HttpConnectionManager.postSignUp(params[0]);
            Log.d("***response", res + " ");
            return res;
        }

        @Override
        protected void onPostExecute(String s) {
            authProgressDialog.cancel();
            super.onPostExecute(s);
            whatToDo(String.valueOf(s));
        }
    }

}


