package com.corazon98.dsaw.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.corazon98.dsaw.R;
import com.corazon98.dsaw.data.DataManager;

public class ResetPasswordActivity extends AppCompatActivity {
    EditText account;
    Button btnReset;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_reset_password);

        InitComponent();
        InitEvent();
    }

    void InitComponent()
    {
        account = findViewById(R.id.resetAccount);
        btnReset = findViewById(R.id.btnReset);
    }

    public void InitEvent()
    {
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DataManager.Instance().sendMailResetPassword(account.getText().toString()).isSuccessful()){
                    Toast. makeText(ResetPasswordActivity.this,"Your email is not correct!", Toast. LENGTH_SHORT).show();
                }
                else {
                    OnResetPasswordSuccessful();
                    Toast. makeText(ResetPasswordActivity.this,"Please check your email!", Toast. LENGTH_SHORT).show();
                }
            }
        });
    }

    void OnResetPasswordSuccessful()
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
