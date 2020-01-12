package com.example.bloodbank.data.model.Login;

import com.example.bloodbank.data.model.generalResponse.GeneralResponseData;
import com.google.gson.annotations.SerializedName;

public class Client{

	@SerializedName("is_active")
	private String isActive;

	@SerializedName("can_donate")
	private boolean canDonate;

	@SerializedName("city")
	private GeneralResponseData city;

	@SerializedName("birth_date")
	private String birthDate;

	@SerializedName("pin_code")
	private String pinCode;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("phone")
	private String phone;

	@SerializedName("blood_type_id")
	private String bloodTypeId;

	@SerializedName("blood_type")
	private GeneralResponseData bloodType;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("donation_last_date")
	private String donationLastDate;

	@SerializedName("email")
	private String email;

	@SerializedName("city_id")
	private String cityId;

	public void setIsActive(String isActive){
		this.isActive = isActive;
	}

	public String getIsActive(){
		return isActive;
	}

	public void setCanDonate(boolean canDonate){
		this.canDonate = canDonate;
	}

	public boolean isCanDonate(){
		return canDonate;
	}

	public void setCity(GeneralResponseData city){
		this.city = city;
	}

	public GeneralResponseData getCity(){
		return city;
	}

	public void setBirthDate(String birthDate){
		this.birthDate = birthDate;
	}

	public String getBirthDate(){
		return birthDate;
	}

	public void setPinCode(String pinCode){
		this.pinCode = pinCode;
	}

	public String getPinCode(){
		return pinCode;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setBloodTypeId(String bloodTypeId){
		this.bloodTypeId = bloodTypeId;
	}

	public String getBloodTypeId(){
		return bloodTypeId;
	}

	public void setBloodType(GeneralResponseData bloodType){
		this.bloodType = bloodType;
	}

	public GeneralResponseData getBloodType(){
		return bloodType;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setDonationLastDate(String donationLastDate){
		this.donationLastDate = donationLastDate;
	}

	public String getDonationLastDate(){
		return donationLastDate;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setCityId(String cityId){
		this.cityId = cityId;
	}

	public String getCityId(){
		return cityId;
	}

	@Override
 	public String toString(){
		return 
			"Client{" + 
			"is_active = '" + isActive + '\'' + 
			",can_donate = '" + canDonate + '\'' + 
			",city = '" + city + '\'' + 
			",birth_date = '" + birthDate + '\'' + 
			",pin_code = '" + pinCode + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",phone = '" + phone + '\'' + 
			",blood_type_id = '" + bloodTypeId + '\'' + 
			",blood_type = '" + bloodType + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",donation_last_date = '" + donationLastDate + '\'' + 
			",email = '" + email + '\'' + 
			",city_id = '" + cityId + '\'' + 
			"}";
		}
}