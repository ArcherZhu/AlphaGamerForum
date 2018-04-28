package com.shiro.issolution.gamerforum.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shiro.issolution.gamerforum.R;
import com.shiro.issolution.gamerforum.model.Post;
import com.shiro.issolution.gamerforum.model.PostComment;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private List<PostComment> mPostCommentList;

    public PostAdapter(List<PostComment> mPostCommentList) {
        this.mPostCommentList = mPostCommentList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_post_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final PostComment postComment = mPostCommentList.get(position);
        holder.nickname.setText(postComment.getUsername());
        holder.content.setText(postComment.getCommentContent());
        holder.likes = postComment.getThumbsUp();
        holder.num_like.setText(String.valueOf(holder.likes));
        holder.ObjectId = postComment.getObjectId();

        holder.btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!holder.tag){

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPostCommentList.size();
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
