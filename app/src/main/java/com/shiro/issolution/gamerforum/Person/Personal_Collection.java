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
import com.shiro.issolution.gamerforum.model.Collection;
import com.shiro.issolution.gamerforum.model.Post;
import com.shiro.issolution.gamerforum.model._User;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class Personal_Collection extends AppCompatActivity {
    String res;
    private ArrayList<String> collectionIds = new ArrayList<>();
    private ArrayList<String> collectionTitles = new ArrayList<>();
    private ArrayList<Collection> collections = new ArrayList<>();
    private ListView listView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal__collection);

        listView1 = (ListView) findViewById(R.id.listView1);
        BmobQuery<Collection> query = new BmobQuery<Collection>();
        _User user = BmobUser.getCurrentUser(_User.class);
        if(user==null){
            Toast.makeText(Personal_Collection.this,"尚未登录！",Toast.LENGTH_SHORT).show();
            finish();
        }
        query.addWhereEqualTo("username", user.getUsername());
        query.findObjects(new FindListener<Collection>() {
            @Override
            public void done(List<Collection> list, BmobException e) {
                if (e == null) {
                    for (Collection collection : list) {
                        collectionIds.add(collection.getPostId());
                    }
                    findPostName(collectionIds);
                } else {
                    Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String postId = collections.get(i).getPostId();
                Intent intent = new Intent(Personal_Collection.this, PostActivity.class);
                intent.putExtra("id", postId);
                startActivity(intent);
            }
        });
    }

    public String findPostName(List<String> collections) {
        BmobQuery<Post> query = new BmobQuery<Post>();
        query.addWhereContainedIn("PostId", collections);
        query.findObjects(new FindListener<Post>() {
            @Override
            public void done(List<Post> list, BmobException e) {
                if (e == null) {
                    for (Post post : list) {
                        collectionTitles.add(post.getPostName());
                    }
                    ArrayAdapter<String> a = new ArrayAdapter<String>(Personal_Collection.this, R.layout.support_simple_spinner_dropdown_item, collectionTitles.toArray(new String[collectionTitles.size()]));
                    listView1.setAdapter(a);
                } else {
                    Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
        return res;
    }
}
