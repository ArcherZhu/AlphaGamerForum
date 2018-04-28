package com.shiro.issolution.gamerforum.model;

import cn.bmob.v3.BmobObject;

public class PostComment extends BmobObject {
    private String username;
    private String PostId;
    private String CommentId;
    private String CommentContent;
    private Integer ThumbsUp;


    public String getCommentId() {
        return CommentId;
    }

    public void setCommentId(String CommentId) {
        this.CommentId = CommentId;
    }

    public String getCommentContent() {
        return CommentContent;
    }

    public void setCommentContent(String CommentContent) {
        this.CommentContent = CommentContent;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPostId() {
        return PostId;
    }

    public void setPostId(String postId) {
        PostId = postId;
    }

    public Integer getThumbsUp() {
        return ThumbsUp;
    }

    public void setThumbsUp(Integer ThumbsUp) {
        this.ThumbsUp = ThumbsUp;
    }
}
