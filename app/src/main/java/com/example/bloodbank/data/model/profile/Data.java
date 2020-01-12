package com.example.bloodbank.data.model.profile;

import com.example.bloodbank.data.model.Auth.Client;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("client")
	private Client client;

	public void setClient(Client client){
		this.client = client;
	}

	public Client getClient(){
		return client;
	}

	@Override
 	public String toString(){
		return 
			"NotificationPagination{" +
			"client = '" + client + '\'' + 
			"}";
		}
}