package com.evoteam.evolist;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton signInImageButton;
    EditText usernameEditText, passwordEditText;
    TextView wrongInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Sign In");
        }
        init();
    }

    private void init() {
        //imageButton
        signInImageButton = (ImageButton) findViewById(R.id.signInImageButtonInSignInActivity);
        signInImageButton.setOnClickListener(this);
        //edit texts
        usernameEditText = (EditText) findViewById(R.id.userNameEditText);
        passwordEditText = (EditText) findViewById(R.id.passWordEditText);
        //text view
        wrongInformation = (TextView) findViewById(R.id.wrongUserOrPassTextView);
        wrongInformation.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        if(username.equals("")){
            usernameEditText.requestFocus();
            Animation shake = AnimationUtils.loadAnimation(SignInActivity.this, R.anim.shake);
            usernameEditText.startAnimation(shake);
        }else if(password.equals("")){
            passwordEditText.requestFocus();
            Animation shake = AnimationUtils.loadAnimation(SignInActivity.this, R.anim.shake);
            passwordEditText.startAnimation(shake);
        }else{
            if (HttpConnectionManager.isOnline(SignInActivity.this)) {
                myTask signInTask = new myTask(this);
                signInTask.execute(username, password);
            }else{
                Toast.makeText(this, "لطفا دسترسی خود را به اینترنت چک کنید.", Toast.LENGTH_SHORT).show();
            }

        }

    }

    public void whatToDo(String s){
        if(s.equals("false")){
            wrongInformation.setVisibility(View.VISIBLE);
        }else if(s.equals("true")){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("check","check");
            startActivity(intent);
        }else{
            Toast.makeText(this, "خطا در برقراری ارتباط با سرور...", Toast.LENGTH_SHORT).show();
        }
    }

    private class myTask extends AsyncTask<String, String, String> {

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
            res = HttpConnectionManager.postSignIn(params[0], params[1]);
            return res;
        }

        @Override
        protected void onPostExecute(String s) {
            authProgressDialog.cancel();
            super.onPostExecute(s);
            Log.d("***res", s);
            whatToDo(String.valueOf((s)));
        }
    }
}

