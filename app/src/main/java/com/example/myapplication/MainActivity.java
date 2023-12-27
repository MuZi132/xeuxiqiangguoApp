package com.example.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private UserRepository userRepository;
    private User user;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    private Button buttonRegister;
    private TextView  textViewForgotPassword;
    private CheckBox cb_remember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userRepository = new UserRepository(MainActivity.this);
        editTextUsername = findViewById(R.id.usernameEditText);
        editTextPassword = findViewById(R.id.passwordEditText);
        buttonLogin = findViewById(R.id.loginButton);
        buttonRegister = findViewById(R.id.registerButton);
        textViewForgotPassword =  findViewById(R.id.forgotPasswordTextView);
        cb_remember = findViewById(R.id.cb_remember);

        // 读取保存的用户名和密码
        String savedUsername = SharedPreferencesManager.getSavedUsername(MainActivity.this);
        String savedPassword = SharedPreferencesManager.getSavedPassword(MainActivity.this);
        if (!savedUsername.isEmpty() && !savedPassword.isEmpty()) {
            editTextUsername.setText(savedUsername);
            editTextPassword.setText(savedPassword);
            cb_remember.setChecked(true);
            buttonLogin.setEnabled(true);
        }

        // 添加文本变化监听器
        editTextUsername.addTextChangedListener(textWatcher);
        editTextPassword.addTextChangedListener(textWatcher);
        // 添加登录按钮点击事件监听器
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取用户名和密码
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                //读取user表中的数据
                user = userRepository.getUserByUsername(username);
                //验证用户名密码
                if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    // 保存用户名和密码（如果勾选了“记住密码”复选框）
                    if (cb_remember.isChecked()) {
                        SharedPreferencesManager.saveLoginInfo(MainActivity.this, username, password);
                    }
                    // 保存用户名和密码（如果未勾选“记住密码”复选框）
                    else {
                        SharedPreferencesManager.clearLoginInfo(MainActivity.this);
                    }
                    // 跳转到主界面
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtra("userid",user.getId());
                    startActivity(intent);
                    } else {
                    Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
        buttonRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        textViewForgotPassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // 检查用户名和密码是否非空，如果非空，则激活登录按钮
            String username = editTextUsername.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            buttonLogin.setEnabled(!username.isEmpty() && !password.isEmpty());
        }
    };
}
