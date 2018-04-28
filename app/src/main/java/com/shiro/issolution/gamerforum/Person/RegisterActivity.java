package com.shiro.issolution.gamerforum.Person;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shiro.issolution.gamerforum.R;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
/**
 * Created by Archer on 2018/4/11.
 */

public class RegisterActivity extends AppCompatActivity {
    Button register;
    TextView newEmail;
    TextView newPassword;
    TextView checkPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "c81c70281118ea87f1d0ee934249cda1");
        setContentView(R.layout.yun_register);
        newEmail = (TextView) findViewById(R.id.newEmail);
        newPassword = (TextView) findViewById(R.id.newPassword);
        checkPassword = (TextView) findViewById(R.id.checkPassword);
        register = (Button) findViewById(R.id.myregister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((newPassword.getText().toString()).equals((checkPassword.getText().toString()))) {
                    String username = newEmail.getText().toString();
                    String password = newPassword.getText().toString();
                    Intent intent = new Intent(RegisterActivity.this, com.shiro.issolution.gamerforum.Person.MoreRegisterActivity.class);
                    intent.putExtra("username", username);
                    intent.putExtra("password",password);
                    startActivity(intent);
                }else{
                    Toast.makeText(RegisterActivity.this, "密码不一致！", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}