package com.example.bloodbank.data.model.Auth;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("pin_code_for_test")
	private int pinCodeForTest;

	@SerializedName("mail_fails")
	private List<Object> mailFails;

	@SerializedName("email")
	private String email;

	public void setPinCodeForTest(int pinCodeForTest){
		this.pinCodeForTest = pinCodeForTest;
	}

	public int getPinCodeForTest(){
		return pinCodeForTest;
	}

	public void setMailFails(List<Object> mailFails){
		this.mailFails = mailFails;
	}

	public List<Object> getMailFails(){
		return mailFails;
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
			"pin_code_for_test = '" + pinCodeForTest + '\'' + 
			",mail_fails = '" + mailFails + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}