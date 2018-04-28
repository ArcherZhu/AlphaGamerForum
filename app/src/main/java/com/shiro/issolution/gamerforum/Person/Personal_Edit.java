package com.shiro.issolution.gamerforum.Person;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shiro.issolution.gamerforum.R;
import com.shiro.issolution.gamerforum.model._User;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class Personal_Edit extends AppCompatActivity {
    EditText e4;
    EditText e2;
    EditText e1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal__edit);
        if(BmobUser.getCurrentUser()==null){
            Toast.makeText(Personal_Edit.this,"尚未登录！",Toast.LENGTH_SHORT).show();
            finish();
        }
        e1 = (EditText) findViewById(R.id.editName);
        e2 = (EditText) findViewById(R.id.editPhone);
        e4 = (EditText) findViewById(R.id.editPassword);
    }

    public void editSave(View view) {
        _User user = BmobUser.getCurrentUser(_User.class);
        String objectId = user.getObjectId();
        String nickname = e1.getText().toString();
        String phone = e2.getText().toString();
        String password = e4.getText().toString();
        if (!nickname.equals("")) {
            user.setNickname(nickname);
        }
        if (!phone.equals("")) {
            user.setMobilePhoneNumber(phone);
        }

        if (!password.equals("")) {
            user.setPassword(password);
        }

        user.update(objectId, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Log.i("bmob", "更新成功");
                    finish();
                } else {
                    Log.i("bmob", "更新失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });

    }
}
