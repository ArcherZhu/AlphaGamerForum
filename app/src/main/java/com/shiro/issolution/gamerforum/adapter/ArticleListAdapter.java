package com.shiro.issolution.gamerforum.adapter;

/**
 * Created by Archer on 2018/4/20.
 */

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.shiro.issolution.gamerforum.R;
import com.shiro.issolution.gamerforum.model.Post;
import com.shiro.issolution.gamerforum.model._User;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ViewHolder> {
    private List<Post> mArticleList;
    private OnCardListener mOnCardListener;
    public ArticleListAdapter(List<Post> mArticleList1){
        this.mArticleList = mArticleList1;
    }
    String nickname = "";

    public void setOnItemClickListener(OnCardListener onCardListener) {
        this.mOnCardListener = onCardListener;
    }
    public interface OnCardListener {
        void cardListener(int position, String id);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_article_item, parent, false);
        final ViewHolder holder = new ArticleListAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Post post = mArticleList.get(position);
        final String id = post.getObjectId();
        // 设置WebView的客户端
            holder.newcontent.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return false;// 返回false
                }
            });
            holder.newcontent.loadUrl(post.getPostContent());
            Log.i("TAG", "-:" + mArticleList.size());
            holder.name.setText(post.getPostName());
            //holder.content.setText(post.getPostContent());
            if (mOnCardListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnCardListener.cardListener(position, id);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mArticleList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageIcon;
        TextView name,content;
        WebView newcontent;

        public ViewHolder(View view) {
            super(view);
            imageIcon = (ImageView) view.findViewById(R.id.cover);
            name = (TextView) view.findViewById(R.id.txt_name);
            content = (TextView) view.findViewById(R.id.txt_content);
            newcontent = (WebView)view.findViewById(R.id.newcontent);
        }
    }
}
