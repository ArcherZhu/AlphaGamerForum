package com.shiro.issolution.gamerforum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.shiro.issolution.gamerforum.model.Post;
import com.shiro.issolution.gamerforum.model._User;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class NewPostActivity extends AppCompatActivity {
    private EditText edit_title, edit_content;
    private String plateId, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpost);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (BmobUser.getCurrentUser() == null) {
            Toast.makeText(NewPostActivity.this, "请先登录后发表新帖！", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            edit_title = (EditText) findViewById(R.id.edit_title);
            edit_content = (EditText) findViewById(R.id.edit_content);

            plateId = getIntent().getStringExtra("plateId");
            username = BmobUser.getCurrentUser(_User.class).getUsername();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.newpostmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.btn_c:
                if (edit_title.getText().toString().equals("")) {
                    Toast.makeText(NewPostActivity.this, "请确认帖子标题！", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (edit_content.getText().toString().equals("")) {
                    Toast.makeText(NewPostActivity.this, "请补充帖子内容！", Toast.LENGTH_SHORT).show();
                    break;
                }
                Post post = new Post();

                post.setusername(username);
                post.setType("P");
                post.setFloors(1);
                post.setPostName(edit_title.getText().toString());
                post.setPostContent(edit_content.getText().toString());
                post.setPlateId(plateId);
                post.setThumbsUp(0);
                post.setLikes(0);

                post.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e == null) {
                            Toast.makeText(NewPostActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent();
                            i.putExtra("s",s);
                            setResult(RESULT_OK,i);
                            finish();
                        } else {
                            Log.i("TAG","W:"+e.getMessage());
                            e.printStackTrace();
                            Toast.makeText(NewPostActivity.this, "发布失败，请稍后继续尝试", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
        }
        return true;
    }
}
