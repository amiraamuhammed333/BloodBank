package com.example.bloodbank.data.model.notification;

import com.google.gson.annotations.SerializedName;

public class NotificationData {

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("donation_request_id")
	private String donationRequestId;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("pivot")
	private Pivot pivot;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("content")
	private String content;

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setDonationRequestId(String donationRequestId){
		this.donationRequestId = donationRequestId;
	}

	public String getDonationRequestId(){
		return donationRequestId;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setPivot(Pivot pivot){
		this.pivot = pivot;
	}

	public Pivot getPivot(){
		return pivot;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return content;
	}

	@Override
 	public String toString(){
		return 
			"NotificationData{" +
			"updated_at = '" + updatedAt + '\'' + 
			",donation_request_id = '" + donationRequestId + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",pivot = '" + pivot + '\'' + 
			",id = '" + id + '\'' + 
			",title = '" + title + '\'' + 
			",content = '" + content + '\'' + 
			"}";
		}
}