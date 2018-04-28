package com.shiro.issolution.gamerforum;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.shiro.issolution.gamerforum.adapter.PostListAdapter;
import com.shiro.issolution.gamerforum.model.Post;

import com.shiro.issolution.gamerforum.model._User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

public class PostListActivity extends AppCompatActivity implements View.OnClickListener {
    private String plateId, plateName;

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FloatingActionButton fab;

    private LinearLayoutManager linearLayoutManager;
    private List<Post> mPostList = new ArrayList<Post>();

    private PostListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postlist);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent i = getIntent();
        plateId = i.getStringExtra("plateId");
        plateName = i.getStringExtra("plateName");
        getSupportActionBar().setTitle(plateName);

        recyclerView = (RecyclerView) findViewById(R.id.list_post);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        fab = (FloatingActionButton) findViewById(R.id.btn_new_post);
        fab.setOnClickListener(this);
        cardInitialize(plateId);
    }
    public void refresh(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mPostList.clear();
                cardInitialize(plateId);
            }
        }).start();
    }

    public void cardInitialize(String plateId) {
        BmobQuery<Post> query = new BmobQuery<Post>();
        query.addWhereEqualTo("PlateId", plateId);
        query.addWhereEqualTo("Type", "P");
        query.order("-createAt");
        query.findObjects(new FindListener<Post>() {
            @Override
            public void done(List<Post> posts, BmobException e) {
                if (e == null) {
                    mPostList = posts;
                    echoNickName();
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

    public void echoNickName() {
        BmobQuery<_User> query = new BmobQuery<_User>();
        List<String> listUsername = new ArrayList<String>();
        String username;
        for (int i = 0; i < mPostList.size(); i++) {
            username = mPostList.get(i).getusername();
            if (!listUsername.contains(username)) {
                listUsername.add(username);
            }
        }
        query.addWhereContainedIn("username", listUsername);
        query.addQueryKeys("nickname,username");
        query.findObjects(new FindListener<_User>() {
            @Override
            public void done(List<_User> list, BmobException e) {
                if (e == null) {
                    HashMap<String, String> nameHash = new HashMap<String, String>();
                    for (int i = 0; i < list.size(); i++) {
                        nameHash.put(list.get(i).getUsername(), list.get(i).getNickname());
                    }
                    for (int i = 0; i < mPostList.size(); i++) {
                        mPostList.get(i).setusername(nameHash.get(mPostList.get(i).getusername()));
                    }

                    adapter = new PostListAdapter(mPostList);

                    //用这个实现点击时调用
                    adapter.setOnItemClickListener(new PostListAdapter.OnCardListener() {
                        @Override
                        public void cardListener(int position, String id, String title) {
                            Intent i = new Intent(PostListActivity.this, PostActivity.class);
                            i.putExtra("id", id);
                            i.putExtra("title",title);
                            startActivityForResult(i,1);
                        }
                    });
                    recyclerView.setAdapter(adapter);
                    if(swipeRefreshLayout.isRefreshing())
                        swipeRefreshLayout.setRefreshing(false);
                } else {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_new_post:
                Intent i = new Intent(PostListActivity.this, NewPostActivity.class);
                i.putExtra("plateId", plateId);
                startActivityForResult(i, 1);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
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
                    String s = data.getStringExtra("s");
                    BmobQuery<Post> query = new BmobQuery<>();
                    query.getObject(s, new QueryListener<Post>() {
                        @Override
                        public void done(Post post, BmobException e) {
                            if(e==null) {
                                post.setusername(BmobUser.getCurrentUser(_User.class).getNickname());
                                mPostList.add(post);
                                adapter.notifyDataSetChanged();
                            }
                        }
                    });

                }
                break;
            default:
                break;
        }
    }
}
