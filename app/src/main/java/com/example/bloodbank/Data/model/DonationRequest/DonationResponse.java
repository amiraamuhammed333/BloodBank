package com.example.bloodbank.Data.model.DonationRequest;

import com.google.gson.annotations.SerializedName;

public class DonationResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("data")
	private DonationPagination data;

	@SerializedName("status")
	private int status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setData(DonationPagination data){
		this.data = data;
	}

	public DonationPagination getData(){
		return data;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"DonationResponse{" + 
			"msg = '" + msg + '\'' + 
			",data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}