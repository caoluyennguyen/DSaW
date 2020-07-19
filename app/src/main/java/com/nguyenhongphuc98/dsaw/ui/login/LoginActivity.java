package com.nguyenhongphuc98.dsaw.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nguyenhongphuc98.dsaw.MainActivity;
import com.nguyenhongphuc98.dsaw.R;
import com.nguyenhongphuc98.dsaw.data.DataCenter;
import com.nguyenhongphuc98.dsaw.data.DataManager;

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
                LoginProcess();
            }
        });
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateNewAccount();
            }
        });
    }

    public void LoginProcess()
    {
        if (account.getText().toString().length() != 0 && password.getText().toString().length() != 0) {
            DataManager.Instance().setLoginProcess(this);
            DataManager.Instance().ProcessLogin(account.getText().toString(), password.getText().toString());
            /*if (DataManager.Instance().GetUserDataByEmail(email).getId() != null){
                DataCenter.currentUser = DataManager.Instance().GetUserDataByEmail(email);
                Log.d("LoginActivity","Account: " + DataCenter.currentUser);
                Log.d("LoginActivity","Account: " + email);
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Xin chào", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "Xin kiểm tra lại thông tin đăng nhập!", Toast.LENGTH_LONG).show();
            }*/
        }
        else{
            Toast.makeText(getApplicationContext(), "Xin kiểm tra lại thông tin đăng nhập!", Toast.LENGTH_LONG).show();
        }
    }

    public void LoadUserDataComplete()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        //Log.d("Login activity", "User name" + DataCenter.currentUser.getUsername());
    }

    public void LoginSuccessful()
    {
        DataManager.Instance().GetUserDataByEmail(account.getText().toString());
        Log.e("LoginProcess","Account: " + DataCenter.currentUser.getUsername());
        Toast.makeText(getApplicationContext(), "Xin chào", Toast.LENGTH_LONG).show();
    }

    public void LoginFail()
    {
        Toast.makeText(getApplicationContext(), "Xin kiểm tra lại thông tin đăng nhập!", Toast.LENGTH_LONG).show();

    }

    public void CreateNewAccount()
    {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}
