package com.shiro.issolution.gamerforum.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shiro.issolution.gamerforum.R;
import com.shiro.issolution.gamerforum.model.Post;

import java.util.List;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.ViewHolder> {
    private List<Post> mPostList;
    private OnCardListener mOnCardListener;

    public PostListAdapter(List<Post> postList) {
        mPostList = postList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_postlist_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Post post = mPostList.get(position);
        final String id = post.getObjectId();
        final String title = post.getPostName();
        if (mOnCardListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnCardListener.cardListener(position, id, title);
                }
            });
        }
        holder.postTitle.setText(post.getPostName());
        holder.nickname.setText(post.getusername());
        holder.textTime.setText(post.getCreatedAt());
        holder.textCommentNum.setText(post.getFloors().toString());

    }

    @Override
    public int getItemCount() {
        return mPostList.size();
    }

    public void setOnItemClickListener(OnCardListener onCardListener) {
        this.mOnCardListener = onCardListener;
    }

    public interface OnCardListener {
        void cardListener(int position, String id, String title);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView postTitle, nickname, textCommentNum, textTime;

        public ViewHolder(View view) {
            super(view);
            postTitle = (TextView) view.findViewById(R.id.txt_post_title);
            nickname = (TextView) view.findViewById(R.id.txt_nickname);
            textCommentNum = (TextView) view.findViewById(R.id.text_comment_num);
            textTime = (TextView) view.findViewById(R.id.txt_time);
        }
    }
}

