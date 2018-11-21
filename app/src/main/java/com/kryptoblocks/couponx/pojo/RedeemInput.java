package com.kryptoblocks.couponx.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RedeemInput {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("uuid")
    @Expose
    private String uuid;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


}
