package com.nguyenhongphuc98.dsaw;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText account;
    EditText password;
    TextView get_password;
    Button login;
    TextView sign_up;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);

        InitComponent();
        InitEvent();
    }

    void InitComponent()
    {
        account = findViewById(R.id.account);
        password = findViewById(R.id.password);
        get_password = findViewById(R.id.get_password);
        login = findViewById(R.id.login);
        sign_up = findViewById(R.id.sign_up);
    }

    void InitEvent()
    {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadUserDataComplete();
            }
        });
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateNewAccount();
            }
        });
    }

    public void LoadUserDataComplete()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        Toast.makeText(getApplicationContext(), "Xin chào", Toast.LENGTH_LONG).show();
    }

    public void CreateNewAccount()
    {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}
