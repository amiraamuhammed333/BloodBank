package com.example.bloodbank.View.Fragment.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodbank.Data.api.APIManger;
import com.example.bloodbank.Data.model.Auth.AuthResponse;
import com.example.bloodbank.R;
import com.example.bloodbank.View.Fragment.BaseFragment;
import com.example.bloodbank.View.Fragment.Homecycle.HomeActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.Data.local.SharedPreference.SaveData;

public class LoginFragment extends BaseFragment implements View.OnClickListener {
    View view;
    Fragment fragment;
    TextView login_fragmentTvForget;
    EditText ephone, epassword;
    Button elogin, btn1;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate ( R.layout.fragment_login, container, false );
        login_fragmentTvForget = view.findViewById ( R.id.login_fragment_tv_forget );
        elogin = view.findViewById ( R.id.login_fragment_login );
        btn1 = view.findViewById ( R.id.login_fragment_btn_new_accout );
        ephone = view.findViewById ( R.id.login_fragment_phone );
        epassword = view.findViewById ( R.id.login_fragment_password );


        login_fragmentTvForget.setOnClickListener ( this );
        elogin.setOnClickListener ( this );
        btn1.setOnClickListener ( this );


        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId () == R.id.login_fragment_tv_forget) {
            fragment = new ForgetFragment ();

            getActivity ()
                    .getSupportFragmentManager ()
                    .beginTransaction ()
                    .replace ( R.id.fram, fragment )
                    .commit ();


        } else if (view.getId () == R.id.login_fragment_btn_new_accout) {

            fragment = new RegisterFragment ();


            getActivity ()
                    .getSupportFragmentManager ()
                    .beginTransaction ()
                    .replace ( R.id.fram, fragment )
                    .commit ();

        } else if (view.getId () == R.id.login_fragment_login) {

            String phone = ephone.getText ().toString ();
            String password = epassword.getText ().toString ();

            if (validateLogin ( phone, password )) {
                //do login
                onLogin ( phone, password );
            }


        }


      /* getActivity ()
                .getSupportFragmentManager ()
                .beginTransaction ()
                .replace ( R.id.fram, fragment )
                .commit ();
*/
    }

    private void onLogin(String phone, String password) {
        APIManger.getApis ().onLogin ( phone, password ).enqueue ( new Callback<AuthResponse> () {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {

                if (response.isSuccessful ()) {
                    //save user
                    //open profile
                    // Log.i ( "Logon" ,response.body ().toString ());


                    Toast.makeText ( getActivity (), response.body ().getMsg (), Toast.LENGTH_LONG ).show ();


                    Intent intent = new Intent ( getActivity (), HomeActivity.class );
                    startActivity ( intent );

                } else {
                    Toast.makeText ( getActivity (), response.body ().getMsg (), Toast.LENGTH_LONG ).show ();
                }


            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Toast.makeText ( getActivity (), t.getMessage (), Toast.LENGTH_SHORT ).show ();


            }
        } );
    }



       /* APIManger.getApis ().onLogin ( phone,password ).enqueue ( new Callback<LoginResponse> () {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if(response.isSuccessful ()){
                    //save user
                    //open profile
                   // Log.i ( "Logon" ,response.body ().toString ());

                    Toast.makeText ( getActivity (),response.body ().getMsg (),Toast.LENGTH_LONG ).show ();


                    Intent intent = new Intent(getActivity (), HomeActivity.class);
                    startActivity(intent);

                }else {
                    Toast.makeText ( getActivity (),response.body ().getMsg (),Toast.LENGTH_LONG ).show ();
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                Toast.makeText(getActivity (), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        } );


    }*/


    private boolean validateLogin(String phone, String password) {
        if (phone == null || phone.trim ().length () == 0) {
            Toast.makeText ( getActivity (), "Username is required", Toast.LENGTH_SHORT ).show ();
            return false;
        }
        if (password == null || password.trim ().length () == 0) {
            Toast.makeText ( getActivity (), "Password is required", Toast.LENGTH_SHORT ).show ();
            return false;
        }
        return true;
    }
}