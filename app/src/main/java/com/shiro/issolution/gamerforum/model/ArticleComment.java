package com.shiro.issolution.gamerforum.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by Archer on 2018/4/20.
 */

public class ArticleComment extends BmobObject{
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;

    public String getCommentId() {
        return CommentId;
    }

    public void setCommentId(String commentId) {
        CommentId = commentId;
    }

    private String CommentId;

    public String getCommentContent() {
        return CommentContent;
    }

    public void setCommentContent(String commentContent) {
        CommentContent = commentContent;
    }

    private String CommentContent;

    public String getArticleId() {
        return ArticleId;
    }

    public void setArticleId(String articleId) {
        ArticleId = articleId;
    }

    private String ArticleId;

    public Integer getThumbsUp() {
        return ThumbsUp;
    }

    public void setThumbsUp(Integer thumbsUp) {
        ThumbsUp = thumbsUp;
    }

    private Integer ThumbsUp;

}
