package com.kryptoblocks.couponx.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RedeemOutput {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("batch_id")
    @Expose
    private String batchId;
    @SerializedName("shared_by")
    @Expose
    private String sharedBy;
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("redeem")
    @Expose
    private Boolean redeem;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getSharedBy() {
        return sharedBy;
    }

    public void setSharedBy(String sharedBy) {
        this.sharedBy = sharedBy;
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

    public Boolean getRedeem() {
        return redeem;
    }

    public void setRedeem(Boolean redeem) {
        this.redeem = redeem;
    }

   /* @Override
    public String toString() {
        return "Post{" +
                "Name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", email=" + email +
                ", batch_id=" + batchId +
                '}';
    }*/
}
