package com.shiro.issolution.gamerforum.Person;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shiro.issolution.gamerforum.R;
import com.shiro.issolution.gamerforum.model.Feedback;
import com.shiro.issolution.gamerforum.model._User;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Person_Feedback extends AppCompatActivity {

    Button b;
    EditText e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person__feedback);
        b = (Button) findViewById(R.id.button_submit);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _User user = BmobUser.getCurrentUser(_User.class);
                if(user!=null){
                    e = (EditText) findViewById(R.id.feedBack);
                    String text = e.getText().toString();
                    Feedback feedback = new Feedback();
                    feedback.setUsername(user.getUsername());
                    feedback.setFeedBackContent(text);
                    feedback.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if(e==null){
                                Toast.makeText(Person_Feedback.this,"反馈成功！",Toast.LENGTH_SHORT);
                            }else {
                                Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                            }
                        }
                    });
                }
            }
        });
    }
}
