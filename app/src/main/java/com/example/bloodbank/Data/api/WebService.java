package com.example.bloodbank.Data.api;


import com.example.bloodbank.Data.model.Auth.AuthResponse;
import com.example.bloodbank.Data.model.DonationRequest.DonationResponse;
import com.example.bloodbank.Data.model.forget.ForgetPassword;
import com.example.bloodbank.Data.model.posts.Posts;
import com.example.bloodbank.Data.model.generalResponse.GeneralResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WebService {

    @GET("blood-types")
    Call<GeneralResponse> getBloodTypes();

    @GET("governorates")
    Call<GeneralResponse> getGovernment();

    @GET("cities")
    Call<GeneralResponse> getCities(@Query("governorate_id") int governmentId);

    @POST("signup")
    @FormUrlEncoded
    Call<AuthResponse> onRegister(@Field("name") String name,
                                  @Field("email") String email,
                                  @Field("birth_date") String birth_date,
                                  @Field("city_id") int city_id,
                                  @Field("phone") String phone,
                                  @Field("donation_last_date") String donation_last_date,
                                  @Field("password") String password,
                                  @Field("password_confirmation") String password_confirmation,
                                  @Field("blood_type_id") int blood_type_id);


    @POST("login")
    @FormUrlEncoded
    Call<AuthResponse> onLogin(@Field("phone") String phone,
                               @Field("password") String password);


    @POST("reset-password")
    @FormUrlEncoded
    Call<ForgetPassword> send(@Field("phone") String phone);


    @POST("new-password")
    @FormUrlEncoded
    Call<ForgetPassword> confirmPassword(@Field("phone") String phone,
                                         @Field("password") String password,
                                         @Field("password_confirmation") String password_confirmation);


    @GET("categories")
    Call<GeneralResponse> getCategories();

    @GET("posts")
    Call<Posts> getAllPosts(@Query("api_token") String api_token,
                            @Query("page") int page);




    @GET("posts")
    Call<Posts> getPostsFilter(@Query("api_token") String api_token,
                               @Query("page")  int page,
                               @Query("keyword") String keyword,
                               @Query("category_id") int category_id);


    @GET("donation-requests")
    Call<DonationResponse> getAllDonationRequest(@Query("api_token") String api_token,
                                                 @Query("page") int page);


    @GET("donation-request")
    Call<DonationResponse>getDonationDetails(@Query ( "api_token" )String api_token,
                                             @Query  ("donation_id") int id);



}
