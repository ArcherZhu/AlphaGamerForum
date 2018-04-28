package com.shiro.issolution.gamerforum.Person;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.shiro.issolution.gamerforum.R;

import cn.bmob.v3.BmobUser;

public class Person_Setting extends AppCompatActivity {

    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person__setting);
        b = (Button)findViewById(R.id.button_quit);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BmobUser.logOut();
                Toast.makeText(Person_Setting.this,"已退出登录！",Toast.LENGTH_SHORT).show();
                finish();

            }
        });

    }
}
