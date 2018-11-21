package com.kryptoblocks.couponx.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RefreshTokenInput {
    @SerializedName("refreshToken")
    @Expose
    private String refreshToken;
    @SerializedName("uuid")
    @Expose
    private String uuid;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
