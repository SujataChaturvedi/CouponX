package com.kryptoblocks.couponx.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FbSignInOut {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private DataFB data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataFB getData() {
        return data;
    }

    public void setData(DataFB data) {
        this.data = data;
    }

    public class DataFB {
        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("google_id")
        @Expose
        private String googleId;
        @SerializedName("role")
        @Expose
        private String role;
        @SerializedName("uuid")
        @Expose
        private String uuid;
        @SerializedName("__v")
        @Expose
        private Integer v;
        @SerializedName("last_modified")
        @Expose
        private Long lastModified;
        @SerializedName("created_on")
        @Expose
        private Long createdOn;
        @SerializedName("accessToken")
        @Expose
        private String accessToken;
        @SerializedName("refreshToken")
        @Expose
        private String refreshToken;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGoogleId() {
            return googleId;
        }

        public void setGoogleId(String googleId) {
            this.googleId = googleId;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public Integer getV() {
            return v;
        }

        public void setV(Integer v) {
            this.v = v;
        }

        public Long getLastModified() {
            return lastModified;
        }

        public void setLastModified(Long lastModified) {
            this.lastModified = lastModified;
        }

        public Long getCreatedOn() {
            return createdOn;
        }

        public void setCreatedOn(Long createdOn) {
            this.createdOn = createdOn;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }

    }
}
