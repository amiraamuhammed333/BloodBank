package com.example.bloodbank.data.model.settingNotification;

import com.google.gson.annotations.SerializedName;

public class NotificationSettingResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("data")
	private NotificationSettingData data;

	@SerializedName("status")
	private int status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setData(NotificationSettingData data){
		this.data = data;
	}

	public NotificationSettingData getData(){
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
			"NotificationSettingResponse{" + 
			"msg = '" + msg + '\'' + 
			",data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}