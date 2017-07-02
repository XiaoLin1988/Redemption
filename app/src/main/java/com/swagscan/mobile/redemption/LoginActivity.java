package com.swagscan.mobile.redemption;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;

/**
 * Created by emerald on 7/1/2017.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editEmail;
    private EditText editPassword;

    private TextView txtCreate;
    private AppCompatButton btnLogin;

    protected void onCreate(Bundle savedBndle) {
        super.onCreate(savedBndle);
        setContentView(R.layout.activity_login);

        initViewVariables();
    }

    private void initViewVariables() {
        editEmail = (EditText)findViewById(R.id.edit_login_email);
        editPassword = (EditText)findViewById(R.id.edit_login_password);

        txtCreate = (TextView)findViewById(R.id.txt_login_create);
        txtCreate.setOnClickListener(this);

        btnLogin = (AppCompatButton) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_login_create:

                break;
            case R.id.btn_login:

                break;
        }
    }
}
