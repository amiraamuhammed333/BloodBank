package com.example.bloodbank.Data.model.forget;

import java.util.List;

public class Data{
	private int pinCodeForTest;
	private List<Object> mailFails;
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
			"PostsPagination{" +
			"pin_code_for_test = '" + pinCodeForTest + '\'' + 
			",mail_fails = '" + mailFails + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}