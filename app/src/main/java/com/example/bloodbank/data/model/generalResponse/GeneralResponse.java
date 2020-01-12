package com.example.bloodbank.data.model.generalResponse;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class GeneralResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("data")
	private List<GeneralResponseData> data;

	@SerializedName("status")
	private int status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setData(List<GeneralResponseData> data){
		this.data = data;
	}

	public List<GeneralResponseData> getData(){
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
			"GeneralResponse{" + 
			"msg = '" + msg + '\'' + 
			",data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}