package com.shiro.issolution.gamerforum.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.shiro.issolution.gamerforum.R;
import com.shiro.issolution.gamerforum.model.ArticleComment;

import org.w3c.dom.Comment;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private List<ArticleComment> mArticleCommentList;

    public ArticleAdapter(List<ArticleComment> mArticleCommentList) {
        this.mArticleCommentList = mArticleCommentList;
    }

    String nickname = "";

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_post_item, parent, false);
        final ViewHolder holder = new ArticleAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ArticleComment articleComment = mArticleCommentList.get(position);
        holder.nickname.setText(articleComment.getUsername());
        holder.content.setText(articleComment.getCommentContent());
        holder.likes = articleComment.getThumbsUp();
        holder.num_like.setText(String.valueOf(holder.likes));
        holder.ObjectId = articleComment.getObjectId();

        holder.btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!holder.tag) {

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArticleCommentList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView nickname, content, num_like;
        ImageButton btn_like;
        String ObjectId;
        int likes;
        boolean tag = false;

        public ViewHolder(View view) {
            super(view);
            avatar = (ImageView) view.findViewById(R.id.img_avatar);
            nickname = (TextView) view.findViewById(R.id.txt_nickname);
            content = (TextView) view.findViewById(R.id.txt_content);
            num_like = (TextView) view.findViewById(R.id.num_like);
            btn_like = (ImageButton) view.findViewById(R.id.btn_like);
        }
    }
}
