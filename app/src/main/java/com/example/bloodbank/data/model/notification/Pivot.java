package com.example.bloodbank.data.model.notification;

import com.google.gson.annotations.SerializedName;

public class Pivot{

	@SerializedName("is_read")
	private String isRead;

	@SerializedName("notification_id")
	private String notificationId;

	@SerializedName("client_id")
	private String clientId;

	public void setIsRead(String isRead){
		this.isRead = isRead;
	}

	public String getIsRead(){
		return isRead;
	}

	public void setNotificationId(String notificationId){
		this.notificationId = notificationId;
	}

	public String getNotificationId(){
		return notificationId;
	}

	public void setClientId(String clientId){
		this.clientId = clientId;
	}

	public String getClientId(){
		return clientId;
	}

	@Override
 	public String toString(){
		return 
			"Pivot{" + 
			"is_read = '" + isRead + '\'' + 
			",notification_id = '" + notificationId + '\'' + 
			",client_id = '" + clientId + '\'' + 
			"}";
		}
}