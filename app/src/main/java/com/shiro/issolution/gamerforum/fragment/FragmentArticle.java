package com.shiro.issolution.gamerforum.fragment;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import com.shiro.issolution.gamerforum.ArticleActivity;
import com.shiro.issolution.gamerforum.R;
import com.shiro.issolution.gamerforum.adapter.ArticleListAdapter;
import com.shiro.issolution.gamerforum.adapter.PostListAdapter;
import com.shiro.issolution.gamerforum.model.Post;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


public class FragmentArticle extends Fragment{
    private RecyclerView articleList;
    private List<Post> mArticleList;
    private ArticleListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_article, container, false);
        articleList = view.findViewById(R.id.list_article);
        LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
        articleList.setLayoutManager(manager);
        viewInitialize();
        return view;
    }
    public void viewInitialize(){
        BmobQuery<Post> query = new BmobQuery<Post>();
        query.addWhereEqualTo("Type", "A");
        query.findObjects(new FindListener<Post>() {
            @Override
            public void done(List<Post> list, BmobException e) {
                if(e==null){
                    mArticleList = list;
                    adapter = new ArticleListAdapter(mArticleList);
                    //用这个实现点击时调用
                    adapter.setOnItemClickListener(new ArticleListAdapter.OnCardListener() {
                        @Override
                        public void cardListener(int position, String id) {
                            Intent i = new Intent("com.shiro.issolution.gamerForum.ACTION_ARTICLE_LIST");
                            i.putExtra("id", id);
                            //i.putExtra("title",title);
                            startActivityForResult(i,1);
                        }
                    });
                    articleList.setAdapter(adapter);
                }
            }
        });
    }
}
