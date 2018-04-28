package com.shiro.issolution.gamerforum.model;

import cn.bmob.v3.BmobObject;

public class Plate extends BmobObject {
    private String PlateName;
    private String PlateId;

    public String getPlateName() {
        return PlateName;
    }

    public void setPlateName(String plateName) {
        PlateName = plateName;
    }

    public String getPlateId() {
        return PlateId;
    }

    public void setPlateId(String plateId) {
        PlateId = plateId;
    }

}
