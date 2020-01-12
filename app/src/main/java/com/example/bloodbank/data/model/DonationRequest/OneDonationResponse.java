package com.example.bloodbank.data.model.DonationRequest;

import com.google.gson.annotations.SerializedName;

public class OneDonationResponse {

    @SerializedName("msg")
    private String msg;

    @SerializedName("data")
    private DonationItem data;

    @SerializedName("status")
    private int status;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setData(DonationItem data) {
        this.data = data;
    }

    public DonationItem getData() {
        return data;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return
                "DonationResponse{" +
                        "msg = '" + msg + '\'' +
                        ",data = '" + data + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}