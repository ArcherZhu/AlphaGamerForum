package com.shiro.issolution.gamerforum.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by Sean on 2018/4/11.
 */

public class Post extends BmobObject {
    private String username;
    private String Type;
    private String PostName;
    private String PostContent;
    private String PlateId;
    private Integer Likes;
    private Integer ThumbsUp;
    private Integer Floors;


    public void setusername(String username) {
        this.username = username;
    }

    public String getusername() {
        return this.username;
    }

    public String getType() {
        return this.Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }


    public String getPostName() {
        return this.PostName;
    }

    public void setPostName(String PostName) {
        this.PostName = PostName;
    }


    public String getPostContent() {
        return PostContent;
    }

    public void setPostContent(String PostContent) {
        this.PostContent = PostContent;
    }

    public String getPlateId() {
        return this.PlateId;
    }

    public void setPlateId(String PlateId) {
        this.PlateId = PlateId;
    }

    public Integer getFloors() {
        return Floors;
    }

    public void setFloors(Integer floors) {
        Floors = floors;
    }



    public PostComment getPostComment() {
        PostComment postComment = new PostComment();
        postComment.setUsername(this.username);
        postComment.setCommentContent(this.getPostContent());
        postComment.setThumbsUp(this.ThumbsUp);
        postComment.setPostId(this.getObjectId());
        postComment.setObjectId("cardhead");
        return postComment;
    }


    public Integer getLikes() {
        return Likes;
    }

    public void setLikes(Integer likes) {
        Likes = likes;
    }

    public void setThumbsUp(Integer thumbsUp) {
        ThumbsUp = thumbsUp;
    }
}
