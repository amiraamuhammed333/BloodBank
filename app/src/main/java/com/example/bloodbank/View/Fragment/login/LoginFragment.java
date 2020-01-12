package com.example.bloodbank.View.Fragment.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodbank.R;
import com.example.bloodbank.View.Activity.HomeActivity;
import com.example.bloodbank.View.Fragment.BaseFragment;
import com.example.bloodbank.data.api.APIManger;
import com.example.bloodbank.data.local.SharedPreference;
import com.example.bloodbank.data.model.Auth.AuthResponse;
import com.example.bloodbank.data.model.Auth.ClientData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.local.SharedPreference.PASSWORD;
import static com.example.bloodbank.data.local.SharedPreference.REMEMBER;
import static com.example.bloodbank.data.local.SharedPreference.SaveData;
import static com.example.bloodbank.data.local.SharedPreference.USER_DATA;
import static com.example.bloodbank.data.local.SharedPreference.saveUserData;

public class LoginFragment extends BaseFragment implements View.OnClickListener {
    View view;
    Fragment fragment;
    TextView login_fragmentTvForget;
    EditText ephone, epassword;
    Button elogin, btn1;

    Unbinder unbinder;
    @BindView(R.id.login_fragment_cb)
    CheckBox loginFragmentCb;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate ( R.layout.fragment_login, container, false );
        login_fragmentTvForget = view.findViewById ( R.id.login_fragment_tv_forget );
        elogin = view.findViewById ( R.id.login_fragment_login );
        btn1 = view.findViewById ( R.id.login_fragment_btn_new_accout );
        ephone = view.findViewById ( R.id.login_fragment_phone );
        epassword = view.findViewById ( R.id.login_fragment_password );

        login_fragmentTvForget.setOnClickListener ( this );
        elogin.setOnClickListener ( this );
        btn1.setOnClickListener ( this );


        unbinder = ButterKnife.bind ( this, view );
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
                onLogin ( phone, password );

            }

        }
    }


    private void onLogin(String phone, final String password) {
        APIManger.getApis ().onLogin ( phone, password ).enqueue ( new Callback<AuthResponse> () {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {

                if (response.isSuccessful ()) {

                    SaveData ( getActivity (), REMEMBER, loginFragmentCb.isChecked () );
                    SaveData ( getActivity (), PASSWORD, password );
                    SaveData ( getActivity (), USER_DATA, response.body ().getData () );

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

    @Override
    public void onDestroyView() {
        super.onDestroyView ();
        unbinder.unbind ();
    }

    @OnClick(R.id.login_fragment_cb)
    public void onViewClicked() {

    }
}