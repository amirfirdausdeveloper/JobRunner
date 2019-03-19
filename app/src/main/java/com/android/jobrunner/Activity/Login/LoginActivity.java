package com.android.jobrunner.Activity.Login;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.content.res.AppCompatResources;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.jobrunner.Activity.Register.RegistrationActivity;
import com.android.jobrunner.Class.TypeFaceClass;
import com.android.jobrunner.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView_register,textView_forget_password,textView_email,textView_password,textView_signInGoogle;
    EditText editText_email,editText_password;
    Button button_login;
    boolean status_email = false;
    LinearLayout linearLayout_google;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }


    private void AllLinear(){
        linearLayout_google = findViewById(R.id.linear_google);
    }

    private void AllButton() {
        button_login = findViewById(R.id.button_login);
        TypeFaceClass.setTypeFaceButton(button_login,getApplicationContext());
        button_login.setOnClickListener(this);
    }

    private void AllEditText() {
        editText_email = findViewById(R.id.editText_email);
        TypeFaceClass.setTypeFaceEditText(editText_email,getApplicationContext());
        editText_password = findViewById(R.id.editText_password);
        TypeFaceClass.setTypeFaceEditText(editText_password,getApplicationContext());

        editText_email.addTextChangedListener(new TextWatcher() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){

                }else{
                    isEmailValid(s.toString());
                }

            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void afterTextChanged(Editable s) {
            }
        });

        editText_password.addTextChangedListener(new TextWatcher() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() <= 5){
                    Drawable custom = getResources().getDrawable(R.drawable.custom_edit_text_error);
                    editText_password.setBackground(custom);
                    editText_password.setError("Please enter password more than 5");
                }else{
                    Drawable custom = getResources().getDrawable(R.drawable.custom_edit_text_white);
                    editText_password.setBackground(custom);
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void AllTextView() {
        textView_register = findViewById(R.id.textView_register);
        TypeFaceClass.setTypeFaceTextView(textView_register,getApplicationContext());
        textView_forget_password = findViewById(R.id.textView_forget_password);
        TypeFaceClass.setTypeFaceTextView(textView_forget_password,getApplicationContext());
        textView_email = findViewById(R.id.textView_email);
        TypeFaceClass.setTypeFaceTextView(textView_email,getApplicationContext());
        textView_password = findViewById(R.id.textView_password);
        TypeFaceClass.setTypeFaceTextView(textView_password,getApplicationContext());
        textView_signInGoogle = findViewById(R.id.textView_signInGoogle);
        TypeFaceClass.setTypeFaceTextView(textView_signInGoogle,getApplicationContext());
        textView_register.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AllTextView();
        AllEditText();
        AllButton();
        AllLinear();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView_register:
                Intent next = new Intent(getApplicationContext(),RegistrationActivity.class);
                startActivity(next);
                break;

            case R.id.button_login:
                if(editText_email.getText().toString().equals("") || status_email == false){
                    Drawable custom = getResources().getDrawable(R.drawable.custom_edit_text_error);
                    editText_email.setBackground(custom);
                    editText_email.setError("Please enter valid email");
                }else if(editText_password.getText().equals("") || editText_password.length() <=5){
                    Drawable custom = getResources().getDrawable(R.drawable.custom_edit_text_error);
                    editText_password.setBackground(custom);
                    editText_password.setError("Please enter password more than 5");
                }else{
                    // function login
                }
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void isEmailValid(String email){
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if(matcher.matches()){
            editText_email.setBackground(AppCompatResources.getDrawable(getApplicationContext(), R.drawable.custom_edit_text_white));
            status_email = true;
        } else{
            editText_email.setBackground(AppCompatResources.getDrawable(getApplicationContext(), R.drawable.custom_edit_text_error));
            editText_email.setError("Please enter valid email");
            status_email = false;
        }
    }
}
