package com.shiro.issolution.gamerforum.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by Archer on 2018/4/20.
 */

public class Feedback extends BmobObject{
    private String username;
    private String FeedbackContent;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFeedbackContent() {
        return FeedbackContent;
    }

    public void setFeedBackContent(String feedBackContent) {
        this.FeedbackContent = feedBackContent;
    }
}
