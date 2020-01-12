package com.example.bloodbank.data.local;

import android.app.Activity;
import android.content.SharedPreferences;

import com.example.bloodbank.data.model.Auth.ClientData;
import com.google.gson.Gson;

public class SharedPreference {

    private static SharedPreferences sharedPreferences = null;
    public static String USER_DATA = "USER_DATA";
    public static String REMEMBER = "REMEMBER";
    public static String PASSWORD = "PASSWORD";






    public static void setSharedPreferences(Activity activity) {
        if (sharedPreferences == null) {
            sharedPreferences = activity.getSharedPreferences(
                    "Blood", activity.MODE_PRIVATE);
        }
    }


    public static void SaveData(Activity activity, String data_Key, String data_Value) {
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(data_Key, data_Value);
            editor.commit();
        } else {
            setSharedPreferences(activity);
        }
    }


    public static void SaveData(Activity activity, String  data_Key, boolean data_Value) {
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(data_Key, data_Value);
            editor.commit();
        } else {
            setSharedPreferences(activity);
        }
    }





    public static void SaveData(Activity activity, String data_Key, Object data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson ();
            String StringData = gson.toJson(data_Value);
            editor.putString(data_Key, StringData);
            editor.commit();
        }
    }


    public static void saveUserData(Activity activity, ClientData userData) {
        SaveData(activity, USER_DATA, userData);
    }
    public static ClientData loadUserData(Activity activity){

        ClientData userData=null;
        Gson gson= new Gson ();
        userData = gson.fromJson ( loadStringData ( activity,USER_DATA ), ClientData.class );
        return userData;

    }



    public static String loadStringData(Activity activity, String data_key){
        if (sharedPreferences!=null){
                 SharedPreferences.Editor editor = sharedPreferences.edit ();
             }else {
                 setSharedPreferences ( activity ); }
             return sharedPreferences.getString ( data_key,null );
    }



    public static boolean loadBooleanData(Activity activity, String data_key){
        if (sharedPreferences!=null){
            SharedPreferences.Editor editor = sharedPreferences.edit ();
        }else {
            setSharedPreferences ( activity ); }
        return sharedPreferences.getBoolean ( data_key,false );
    }




    public static void clean(Activity activity){

      setSharedPreferences ( activity );
      if (sharedPreferences != null){
          SharedPreferences.Editor editor = sharedPreferences.edit ();
          editor.clear ();
          editor.commit ();
      }


    }





























}
