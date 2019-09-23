package com.example.bloodbank.Data.model.Login;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("api_token")
	private String apiToken;

	@SerializedName("client")
	private Client client;

	public void setApiToken(String apiToken){
		this.apiToken = apiToken;
	}

	public String getApiToken(){
		return apiToken;
	}

	public void setClient(Client client){
		this.client = client;
	}

	public Client getClient(){
		return client;
	}

	@Override
 	public String toString(){
		return 
			"PostsPagination{" +
			"api_token = '" + apiToken + '\'' + 
			",client = '" + client + '\'' + 
			"}";
		}
}