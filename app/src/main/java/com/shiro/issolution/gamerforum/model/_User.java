package com.shiro.issolution.gamerforum.model;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

public class _User extends BmobUser {
    //private String objectId;
    private String nickname;
    //private String mobilePhoneNumber;
    // private String email;

    private BmobFile headimage;
    public void setHeadimage(BmobFile headimage) {
        this.headimage = headimage;
    }

    public BmobFile getImage() {
        return headimage;
    }

    /*
    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }
    public String getMobilePhoneNumber(){
        return mobilePhoneNumber;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }
*/
    /*
    @Override
    public String getObjectId() {
        return objectId;
    }
    @Override
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
    */
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
