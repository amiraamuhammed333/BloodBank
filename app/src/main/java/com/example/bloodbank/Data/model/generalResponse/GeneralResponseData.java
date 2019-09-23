package com.example.bloodbank.Data.model.generalResponse;

import com.google.gson.annotations.SerializedName;

public class GeneralResponseData {

	@SerializedName("updated_at")
	private Object updatedAt;

	@SerializedName("governorate")
	private GeneralResponseData governorate;

	@SerializedName("name")
	private String name;

	@SerializedName("created_at")
	private Object createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("governorate_id")
	private String governorateId;

	public GeneralResponseData(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public void setUpdatedAt(Object updatedAt){
		this.updatedAt = updatedAt;
	}

	public Object getUpdatedAt(){
		return updatedAt;
	}

	public void setGovernorate(GeneralResponseData governorate){
		this.governorate = governorate;
	}

	public GeneralResponseData getGovernorate(){
		return governorate;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
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

	public void setGovernorateId(String governorateId){
		this.governorateId = governorateId;
	}

	public String getGovernorateId(){
		return governorateId;
	}

	@Override
 	public String toString(){
		return 
			"GeneralResponseData{" +
			"updated_at = '" + updatedAt + '\'' + 
			",governorate = '" + governorate + '\'' + 
			",name = '" + name + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",governorate_id = '" + governorateId + '\'' + 
			"}";
		}
}