package com.shiro.issolution.gamerforum.model;

import cn.bmob.v3.BmobObject;

public class Collection extends BmobObject {
    private String username;
    private String PostId;
    private String PlateId;
    private String ArticleId;

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

    public String getPlateId() {
        return PlateId;
    }

    public void setPlateId(String plateId) {
        PlateId = plateId;
    }

    public String getArticleId() {
        return ArticleId;
    }

    public void setArticleId(String articleId) {
        ArticleId = articleId;
    }
}
