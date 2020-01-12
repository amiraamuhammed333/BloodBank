package com.example.bloodbank.data.model.settingNotification;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class NotificationSettingData {

	@SerializedName("governorates")
	private List<String> governorates;

	@SerializedName("blood_types")
	private List<String> bloodTypes;

	public void setGovernorates(List<String> governorates){
		this.governorates = governorates;
	}

	public List<String> getGovernorates(){
		return governorates;
	}

	public void setBloodTypes(List<String> bloodTypes){
		this.bloodTypes = bloodTypes;
	}

	public List<String> getBloodTypes(){
		return bloodTypes;
	}

	@Override
 	public String toString(){
		return 
			"NotificationSettingData{" +
			"governorates = '" + governorates + '\'' + 
			",blood_types = '" + bloodTypes + '\'' + 
			"}";
		}
}