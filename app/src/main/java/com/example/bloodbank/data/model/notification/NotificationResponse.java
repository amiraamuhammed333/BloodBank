package com.example.bloodbank.data.model.notification;

import com.google.gson.annotations.SerializedName;

public class NotificationResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("data")
	private NotificationPagination data;

	@SerializedName("status")
	private int status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setData(NotificationPagination data){
		this.data = data;
	}

	public NotificationPagination getData(){
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
			"NotificationResponse{" + 
			"msg = '" + msg + '\'' + 
			",data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}