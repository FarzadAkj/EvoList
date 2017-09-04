package com.evoteam.evolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class SignUpOrInActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton signIn , signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_or_in);

        init();
    }

    private void init() {
        signIn = (ImageButton) findViewById(R.id.signInImageButton);
        signUp = (ImageButton) findViewById(R.id.signUpImageButton);
        signIn.setOnClickListener(this);
        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.signInImageButton:
                intent = new Intent(this, SignInActivity.class);
                startActivity(intent);
                break;
            case R.id.signUpImageButton:
                intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
                break;
        }
    }
}
