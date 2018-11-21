package com.kryptoblocks.couponx.pojo;

public class DashBoardOutput {

    private String _id;
    private String name;
    private String code;
    private String email;
    private String summary;
    private String batch_id;
    private String shared_by;

    private String uuid;
    private Integer __v;
    private Boolean redeem;

    public DashBoardOutput(String _id, String name, String code, String email, String summary, String batch_id, String shared_by, String uuid, Integer _v, Boolean redeem) {
        this._id = _id;
        this.name = name;
        this.code = code;
        this.email = email;
        this.summary = summary;
        this.batch_id = batch_id;
        this.shared_by = shared_by;
        this.uuid = uuid;
        this.__v= _v;
        this.redeem = redeem;
    }


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
        return __v;
    }

    public void setV(Integer v) {
        this.__v = v;
    }

    public Boolean getRedeem() {
        return redeem;
    }

    public void setRedeem(Boolean redeem) {
        this.redeem = redeem;
    }

}
