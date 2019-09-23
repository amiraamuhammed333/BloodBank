package com.example.bloodbank.Data.model.Auth;

import com.google.gson.annotations.SerializedName;

public class AuthResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("data")
	private ClientData data;

	@SerializedName("status")
	private int status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setData(ClientData data){
		this.data = data;
	}

	public ClientData getData(){
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
			"AuthResponse{" + 
			"msg = '" + msg + '\'' + 
			",data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}