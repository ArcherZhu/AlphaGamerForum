package com.shiro.issolution.gamerforum;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.shiro.issolution.gamerforum.adapter.PostAdapter;
import com.shiro.issolution.gamerforum.model.Post;
import com.shiro.issolution.gamerforum.model.PostComment;
import com.shiro.issolution.gamerforum.model._User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

public class PostActivity extends AppCompatActivity {
    private List<PostComment> mPostList = new ArrayList<PostComment>();
    private PostAdapter postAdapter;
    private RecyclerView commentList;
    private FloatingActionButton fab;
    private TextView title;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        commentList = (RecyclerView) findViewById(R.id.recyclerPost);
        title = (TextView) findViewById(R.id.txt_title);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        commentList.setLayoutManager(linearLayoutManager);
        Intent i = getIntent();
        id = i.getStringExtra("id");
        title.setText(i.getStringExtra("title"));

        fab = (FloatingActionButton) findViewById(R.id.btn_new_comment);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PostActivity.this, NewPostCommentActivity.class);
                i.putExtra("id", id);
                startActivityForResult(i, 1);
            }
        });
        postInitialize(id);

    }

    public void postInitialize(final String id) {
        BmobQuery<Post> query = new BmobQuery<Post>();
        query.addWhereEqualTo("ObjectId", id);
        query.getObject(id, new QueryListener<Post>() {
            @Override
            public void done(Post post, BmobException e) {
                if(e==null){
                    PostComment postComment = post.getPostComment();
                    mPostList.add(postComment);
                    commentInitialize(id);
                }else{
                    Toast.makeText(PostActivity.this, "I got the problem.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void commentInitialize(String id) {
        BmobQuery<PostComment> query = new BmobQuery<PostComment>();
        query.addWhereEqualTo("PostId", id);
        query.findObjects(new FindListener<PostComment>() {
            @Override
            public void done(List<PostComment> list, BmobException e) {
                if (e == null) {
                    for (PostComment p : list) {
                        mPostList.add(p);
                    }
                    echoName();
                }
            }
        });
    }

    public void echoName() {
        BmobQuery<_User> query = new BmobQuery<_User>();
        query.addQueryKeys("nickname,username");
        List<String> UserName = new ArrayList<String>();
        String username;
        for (int i = 0; i < mPostList.size(); i++) {
            username = mPostList.get(i).getUsername();
            if (!UserName.contains(username))
                UserName.add(username);
        }
        query.addWhereContainedIn("username", UserName);
        query.findObjects(new FindListener<_User>() {
            @Override
            public void done(List<_User> list, BmobException e) {
                if (e == null) {
                    HashMap<String, String> nameHash = new HashMap<String, String>();
                    for (int i = 0; i < list.size(); i++) {
                        nameHash.put(list.get(i).getUsername(), list.get(i).getNickname());
                    }
                    for (int i = 0; i < mPostList.size(); i++) {
                        mPostList.get(i).setUsername(nameHash.get(mPostList.get(i).getUsername()));
                    }
                    postAdapter = new PostAdapter(mPostList);
                    commentList.setAdapter(postAdapter);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.se_star, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.btn_collection:
                /*
                在这里写收藏的逻辑,该帖子的id是 id
                 */
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int i, int j, Intent data) {
        switch (i) {
            case 1:
                if (RESULT_OK == j) {
                    PostComment p = new PostComment();
                    p.setObjectId(data.getStringExtra("objectId"));
                    p.setUsername(BmobUser.getCurrentUser(_User.class).getNickname());
                    p.setPostId(id);
                    p.setThumbsUp(0);
                    p.setCommentContent(data.getStringExtra("comment"));
                    mPostList.add(p);
                    postAdapter.notifyDataSetChanged();
                }
                break;
            default:
                break;
        }
    }
}
