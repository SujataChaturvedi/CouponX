package com.kryptoblocks.couponx.pojo;

import java.util.List;

public class Dashboard {

    int status = 200;

    private List<DashBoardOutput> data = null;

    private String _id;
    private String name;
    private String code;
    private String email;
    private String summary;
    private String batch_id;
    private String shared_by;
    private String uuid;
    private Integer _v;
    private Boolean redeem;


    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
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
        return batch_id;
    }

    public void setBatchId(String batchId) {
        this.batch_id = batchId;
    }

    public String getSharedBy() {
        return shared_by;
    }

    public void setSharedBy(String sharedBy) {
        this.shared_by = sharedBy;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getV() {
        return _v;
    }

    public void setV(Integer v) {
        this._v = v;
    }

    public Boolean getRedeem() {
        return redeem;
    }

    public void setRedeem(Boolean redeem) {
        this.redeem = redeem;
    }



    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DashBoardOutput> getData() {
        return data;
    }

    public void setData(List<DashBoardOutput> data) {
        this.data = data;
    }


}
