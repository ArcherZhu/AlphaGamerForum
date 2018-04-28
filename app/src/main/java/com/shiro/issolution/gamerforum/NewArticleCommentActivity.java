package com.shiro.issolution.gamerforum;

/**
 * Created by Archer on 2018/4/20.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.shiro.issolution.gamerforum.model.ArticleComment;
import com.shiro.issolution.gamerforum.model.PostComment;
import com.shiro.issolution.gamerforum.model._User;

import org.w3c.dom.Comment;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class NewArticleCommentActivity extends AppCompatActivity {
    private EditText edit_comment;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(BmobUser.getCurrentUser()==null){
            Toast.makeText(NewArticleCommentActivity.this,"您还未登录，请登录后再执行此操作~",Toast.LENGTH_SHORT).show();
            finish();
        }else {
            edit_comment = (EditText) findViewById(R.id.edit_comment);
            id = getIntent().getStringExtra("id");
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.newpostmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.btn_c:
                if(edit_comment.getText().toString().equals("")){
                    Toast.makeText(NewArticleCommentActivity.this,"您尚未有任何评论~",Toast.LENGTH_SHORT).show();
                    break;
                }
                ArticleComment articleComment = new ArticleComment();
                articleComment.setArticleId(id);
                articleComment.setThumbsUp(new Integer(0));
                articleComment.setCommentContent(edit_comment.getText().toString());
                articleComment.setUsername(BmobUser.getCurrentUser(_User.class).getUsername());
                articleComment.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if(e==null){
                            Toast.makeText(NewArticleCommentActivity.this,"发表评论成功",Toast.LENGTH_SHORT).show();
                            Intent i = new Intent();
                            i.putExtra("objectId",s);
                            i.putExtra("comment",edit_comment.getText().toString());
                            setResult(RESULT_OK,i);
                            finish();
                        }else{
                            Log.i("TAG","e.getMessage():"+e.getMessage());
                            Toast.makeText(NewArticleCommentActivity.this,"发表评论失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
        }
        return true;
    }
}
