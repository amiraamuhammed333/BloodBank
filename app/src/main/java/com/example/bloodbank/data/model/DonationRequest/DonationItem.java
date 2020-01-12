package com.example.bloodbank.data.model.DonationRequest;

import com.example.bloodbank.data.model.Login.Client;
import com.example.bloodbank.data.model.generalResponse.GeneralResponseData;
import com.google.gson.annotations.SerializedName;

public class DonationItem {

    @SerializedName("notes")
    private String notes;

    @SerializedName("city")
    private GeneralResponseData city;

    @SerializedName("bags_num")
    private String bagsNum;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("client_id")
    private String clientId;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("phone")
    private String phone;

    @SerializedName("blood_type_id")
    private String bloodTypeId;

    @SerializedName("blood_type")
    private GeneralResponseData bloodType;

    @SerializedName("patient_name")
    private String patientName;

    @SerializedName("client")
    private Client client;

    @SerializedName("id")
    private int id;

    @SerializedName("patient_age")
    private String patientAge;

    @SerializedName("hospital_address")
    private String hospitalAddress;

    @SerializedName("hospital_name")
    private String hospitalName;

    @SerializedName("city_id")
    private String cityId;

    @SerializedName("longitude")
    private String longitude;

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }

    public void setCity(GeneralResponseData city) {
        this.city = city;
    }

    public GeneralResponseData getCity() {
        return city;
    }

    public void setBagsNum(String bagsNum) {
        this.bagsNum = bagsNum;
    }

    public String getBagsNum() {
        return bagsNum;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setBloodTypeId(String bloodTypeId) {
        this.bloodTypeId = bloodTypeId;
    }

    public String getBloodTypeId() {
        return bloodTypeId;
    }

    public void setBloodType(GeneralResponseData bloodType) {
        this.bloodType = bloodType;
    }

    public GeneralResponseData getBloodType() {
        return bloodType;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientAge() {
        return patientAge;
    }

    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return
                "DonationItem{" +
                        "notes = '" + notes + '\'' +
                        ",city = '" + city + '\'' +
                        ",bags_num = '" + bagsNum + '\'' +
                        ",latitude = '" + latitude + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        ",client_id = '" + clientId + '\'' +
                        ",updated_at = '" + updatedAt + '\'' +
                        ",phone = '" + phone + '\'' +
                        ",blood_type_id = '" + bloodTypeId + '\'' +
                        ",blood_type = '" + bloodType + '\'' +
                        ",patient_name = '" + patientName + '\'' +
                        ",client = '" + client + '\'' +
                        ",id = '" + id + '\'' +
                        ",patient_age = '" + patientAge + '\'' +
                        ",hospital_address = '" + hospitalAddress + '\'' +
                        ",hospital_name = '" + hospitalName + '\'' +
                        ",city_id = '" + cityId + '\'' +
                        ",longitude = '" + longitude + '\'' +
                        "}";
    }
}