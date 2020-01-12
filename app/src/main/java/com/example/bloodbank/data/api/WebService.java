package com.example.bloodbank.data.api;


import com.example.bloodbank.data.model.Auth.AuthResponse;
import com.example.bloodbank.data.model.Auth.ResetResponse;
import com.example.bloodbank.data.model.DonationRequest.DonationResponse;
import com.example.bloodbank.data.model.DonationRequest.OneDonationResponse;
import com.example.bloodbank.data.model.aboutApp.AboutResponse;
import com.example.bloodbank.data.model.generalResponse.ContactResponse;
import com.example.bloodbank.data.model.notification.NotificationResponse;
import com.example.bloodbank.data.model.posts.OnePosts;
import com.example.bloodbank.data.model.posts.Posts;
import com.example.bloodbank.data.model.generalResponse.GeneralResponse;
import com.example.bloodbank.data.model.settingNotification.NotificationSettingResponse;

import java.util.List;

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


    @POST("ge")
    @FormUrlEncoded
    Call<AuthResponse> getProfile(@Field("api_token") String api_token);


    @POST("reset-password")
    @FormUrlEncoded
    Call<ResetResponse> getPhone(@Field("phone") String phone);


    @POST("new-password")
    @FormUrlEncoded
    Call<ResetResponse> changePassword(@Field("phone") String phone,
                                 @Field("pin_code") String pinCode,
                                 @Field("password") String password,
                                 @Field("password_confirmation") String passwordConfirmation);





    @POST("login")
    @FormUrlEncoded
    Call<AuthResponse> onLogin(@Field("phone") String phone,
                               @Field("password") String password);




    @GET("categories")
    Call<GeneralResponse> getCategories();


    @POST("contact")
    @FormUrlEncoded
    Call<ContactResponse> contactUs();


    @GET("posts")
    Call<Posts> getAllPosts(@Query("api_token") String api_token,
                            @Query("page") int page);


    @GET("my-favourites")
    Call<Posts> getAllFavourites(@Query("api_token") String api_token,
                                 @Query("page") int page);


    @GET("posts")
    Call<Posts> getPostsFilter(@Query("api_token") String apiToken,
                               @Query("page") int page,
                               @Query("keyword") String keyword,
                               @Query("category_id") int categoryId);


    @POST("post-toggle-favourite")
    @FormUrlEncoded
    Call<Posts> addFavourite(@Field("post_id") int post_id,
                             @Field("api_token") String api_token);

    @GET("my-favourites")
    Call<Posts> getFavourite(@Query("api_token") String api_token);

    @GET("donation-requests")
    Call<DonationResponse> getAllDonationRequest(@Query("api_token") String api_token,
                                                 @Query("page") int page);


    @GET("donation-request")
    Call<OneDonationResponse> getDonationDetails(@Query("api_token") String api_token,
                                                 @Query("donation_id") int id);



    @GET("donation-requests")
    Call<DonationResponse> getFilter(@Query("api_token") String apiToken,
                                        @Query("page") int page,
                                        @Query("blood_type_id") int bloodTypeId,
                                        @Query("city_id") int cityId);




    @POST("donation-request/create")
    @FormUrlEncoded
    Call<DonationResponse> createRequest(@Field("api_token") String api_token,
                                            @Field("patient_name") String patient_name,
                                            @Field("patient_age") String patient_age,
                                            @Field("blood_type_id") int blood_type_id,
                                            @Field("bags_num") String bags_num,
                                            @Field("hospital_name") String hospital_name,
                                            @Field("hospital_address") String hospital_addresss,
                                            @Field("city_id") int city_id,
                                            @Field("phone") String phone,
                                            @Field("notes") String notes,
                                            @Field("latitude") Double latitude,
                                            @Field("longitude") Double longitude);



    @GET("notifications")
    Call<NotificationResponse>getNotification(@Query("api_token")String apiToken,
                                              @Query("page") int page);


    @GET("settings")
    Call<AboutResponse>getAboutApp(@Query("api_token")String apiToken);



    @POST("notifications-settings")
    @FormUrlEncoded
    Call<NotificationSettingResponse>getNotificationSetting(@Field("api_token") String apiToken);

    @POST("notifications-settings")
    @FormUrlEncoded
    Call<NotificationSettingResponse>setNotificationSetting(@Field("api_token") String apiToken,
                                                            @Field("governorates[]")List<Integer>governorates,
                                                            @Field("blood_types[]")List<Integer>blood_types);


    @POST("contact")
    @FormUrlEncoded
    Call<ContactResponse>contact(@Field("api_token") String apiToken,
                                 @Field("title") String title,
                                 @Field("message") String message);




}
