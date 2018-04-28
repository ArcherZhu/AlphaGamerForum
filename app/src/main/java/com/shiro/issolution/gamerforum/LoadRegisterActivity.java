package com.shiro.issolution.gamerforum;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shiro.issolution.gamerforum.model._User;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoadRegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_login, btn_register;
    private EditText user, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yun_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("登录");
        setSupportActionBar(toolbar);


        btn_login = (Button) findViewById(R.id.B1);
        btn_register = (Button) findViewById(R.id.B2);
        user = (EditText) findViewById(R.id.E1);
        pass = (EditText) findViewById(R.id.E2);

        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.B1:
                Login(view);
                break;
            case R.id.B2:
                Intent intent_1 = new Intent(LoadRegisterActivity.this,com.shiro.issolution.gamerforum.Person.RegisterActivity.class);
                startActivity(intent_1);
                break;
        }
    }


    public void Login(final View view) {
        final ProgressDialog progressDialog = new ProgressDialog(this);

        progressDialog.setTitle("Cloud Player");
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        _User bu = new _User();
        bu.setUsername(user.getText().toString());
        bu.setPassword(pass.getText().toString());
        bu.login(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                if (e == null) {
                    Toast.makeText(LoadRegisterActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(LoadRegisterActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });
    }
}

