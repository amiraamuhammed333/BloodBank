package com.example.bloodbank.data.model.Auth;

import com.google.gson.annotations.SerializedName;

public class ClientData {

	@SerializedName("api_token")
	private String apiToken;

	@SerializedName("client")
	private Client client;

	public void setApiToken(String apiToken){
		this.apiToken = apiToken;
	}

	public String  getApiToken(){
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