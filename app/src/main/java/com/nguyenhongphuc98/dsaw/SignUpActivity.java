package com.nguyenhongphuc98.dsaw;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nguyenhongphuc98.dsaw.ui.medical_report.CreateQuestionDialog;

public class SignUpActivity extends AppCompatActivity {
    EditText user_name;
    EditText password;
    EditText phone_number;
    EditText identity;
    EditText birthday;
    Button btnSignUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_fragment);

        InitComponent();
        InitEvent();
    }

    void InitComponent()
    {
        user_name = findViewById(R.id.user_name);
        password = findViewById(R.id.user_password);
        phone_number = findViewById(R.id.user_phone);
        identity = findViewById(R.id.user_identity);
        birthday = findViewById(R.id.user_birthday);
        btnSignUp = findViewById(R.id.create_account);
    }

    void InitEvent()
    {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAccountSuccessfully();
                Toast.makeText(getApplicationContext(), "Tạo tài khoản thành công", Toast.LENGTH_LONG).show();
            }
        });
    }

    void CreateAccountSuccessfully()
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
