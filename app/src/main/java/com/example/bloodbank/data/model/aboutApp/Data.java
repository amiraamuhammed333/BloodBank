package com.example.bloodbank.data.model.aboutApp;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("whatsapp")
	private String whatsapp;

	@SerializedName("facebook_url")
	private String facebookUrl;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("about_app")
	private String aboutApp;

	@SerializedName("instagram_url")
	private String instagramUrl;

	@SerializedName("phone")
	private String phone;

	@SerializedName("created_at")
	private Object createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("youtube_url")
	private String youtubeUrl;

	@SerializedName("twitter_url")
	private String twitterUrl;

	@SerializedName("google_url")
	private String googleUrl;

	@SerializedName("email")
	private String email;

	public void setWhatsapp(String whatsapp){
		this.whatsapp = whatsapp;
	}

	public String getWhatsapp(){
		return whatsapp;
	}

	public void setFacebookUrl(String facebookUrl){
		this.facebookUrl = facebookUrl;
	}

	public String getFacebookUrl(){
		return facebookUrl;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setAboutApp(String aboutApp){
		this.aboutApp = aboutApp;
	}

	public String getAboutApp(){
		return aboutApp;
	}

	public void setInstagramUrl(String instagramUrl){
		this.instagramUrl = instagramUrl;
	}

	public String getInstagramUrl(){
		return instagramUrl;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setCreatedAt(Object createdAt){
		this.createdAt = createdAt;
	}

	public Object getCreatedAt(){
		return createdAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setYoutubeUrl(String youtubeUrl){
		this.youtubeUrl = youtubeUrl;
	}

	public String getYoutubeUrl(){
		return youtubeUrl;
	}

	public void setTwitterUrl(String twitterUrl){
		this.twitterUrl = twitterUrl;
	}

	public String getTwitterUrl(){
		return twitterUrl;
	}

	public void setGoogleUrl(String googleUrl){
		this.googleUrl = googleUrl;
	}

	public String getGoogleUrl(){
		return googleUrl;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	@Override
 	public String toString(){
		return 
			"NotificationSettingData{" +
			"whatsapp = '" + whatsapp + '\'' + 
			",facebook_url = '" + facebookUrl + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",about_app = '" + aboutApp + '\'' + 
			",instagram_url = '" + instagramUrl + '\'' + 
			",phone = '" + phone + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",youtube_url = '" + youtubeUrl + '\'' + 
			",twitter_url = '" + twitterUrl + '\'' + 
			",google_url = '" + googleUrl + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}