package com.shiro.issolution.gamerforum.Person;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.shiro.issolution.gamerforum.PostActivity;
import com.shiro.issolution.gamerforum.R;
import com.shiro.issolution.gamerforum.model.Post;
import com.shiro.issolution.gamerforum.model._User;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class Personal_Topic extends AppCompatActivity {

    private ArrayList<String> postTitles = new ArrayList<>();
    private ArrayList<Post> posts = new ArrayList<>();
    private ListView listView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal__topic);

        if(BmobUser.getCurrentUser()==null){
            Toast.makeText(Personal_Topic.this,"尚未登录！",Toast.LENGTH_SHORT).show();
            finish();
        }else {
            listView1 = (ListView) findViewById(R.id.listView1);
            BmobQuery<Post> query = new BmobQuery<Post>();
            _User user = BmobUser.getCurrentUser(_User.class);
            query.addWhereEqualTo("username", user.getUsername());
            query.findObjects(new FindListener<Post>() {
                @Override
                public void done(List<Post> list, BmobException e) {
                    if (e == null) {
                        for (Post post : list) {
                            postTitles.add(post.getPostName());
                            posts.add(post);
                        }
                        ArrayAdapter<String> a = new ArrayAdapter<String>(Personal_Topic.this, R.layout.support_simple_spinner_dropdown_item, postTitles.toArray(new String[postTitles.size()]));
                        listView1.setAdapter(a);
                    } else {
                        Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                    }
                }
            });
            listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String postId = posts.get(i).getObjectId();
                    Intent intent = new Intent(Personal_Topic.this, PostActivity.class);
                    intent.putExtra("id", postId);
                    startActivity(intent);
                }
            });
        }
    }

}
