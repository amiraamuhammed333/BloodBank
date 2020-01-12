package com.example.bloodbank.View.Fragment.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bloodbank.View.Fragment.Homecycle.HomeFragment;
import com.example.bloodbank.data.api.APIManger;
import com.example.bloodbank.R;
import com.example.bloodbank.View.Fragment.BaseFragment;
import com.example.bloodbank.data.model.Auth.ClientData;
import com.example.bloodbank.data.model.Auth.ResetResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.local.SharedPreference.loadUserData;

public class ChangeNumberFragment extends BaseFragment {
    View view;
    @BindView(R.id.changenumber_fragment_et_pin_code)
    EditText changenumberFragmentEtPinCode;
    @BindView(R.id.changenumber_fragment_et_password)
    EditText changenumberFragmentEtPassword;
    @BindView(R.id.changenumber_fragment_et_confirm_password)
    EditText changenumberFragmentEtConfirmPassword;
    @BindView(R.id.changenumber_fragment_btn_change_password)
    Button changenumberFragmentBtnChangePassword;
    Unbinder unbinder;
    ClientData clientData;
    Fragment fragment;


    public ChangeNumberFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate ( R.layout.fragment_change_number, container, false );
        unbinder = ButterKnife.bind ( this, view );
        clientData=loadUserData(getActivity());
        return view;
    }

    @Override
    public void onBack() {
        super.onBack ();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView ();
        unbinder.unbind ();
    }
    String pinCode,password,confirmPassword;
    @OnClick(R.id.changenumber_fragment_btn_change_password)
    public void onViewClicked(View view) {

         pinCode = changenumberFragmentEtPinCode.getText ().toString ();
         password = changenumberFragmentEtPassword.getText ().toString ();
         confirmPassword = changenumberFragmentEtConfirmPassword.getText ().toString ();

        if(validateLogin(pinCode, password,confirmPassword)){
            //do login
            changePassword();
           // confirmPassword (pincode,password,confirmPassword);
        }





    }

    private void changePassword( ) {

        APIManger.getApis().changePassword(clientData.getClient().getPhone(),pinCode,password,confirmPassword)
                .enqueue(new Callback<ResetResponse>() {
                    @Override
                    public void onResponse(Call<ResetResponse> call, Response<ResetResponse> response) {
                        if(response.isSuccessful ()) {

                            Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();

                            fragment = new HomeFragment();


                            getActivity ()
                                    .getSupportFragmentManager ()
                                    .beginTransaction ()
                                    .replace (R.id.fram,fragment  )
                                    .commit (); }

                        else{
                            Toast.makeText ( getActivity (),response.body ().getMsg (),Toast.LENGTH_LONG ).show ();

                        }
                    }

                    @Override
                    public void onFailure(Call<ResetResponse> call, Throwable t) {

                    }
                });
    }

    private boolean validateLogin(String phone, String password, String confirmPassword) {



            if(phone == null || phone.trim().length() == 0){
                Toast.makeText(getActivity (), "Username is required", Toast.LENGTH_SHORT).show();
                return false;
            }
            if(password == null || password.trim().length() == 0){
                Toast.makeText(getActivity (), "Password is required", Toast.LENGTH_SHORT).show();
                return false;
            }

            if(confirmPassword == null || confirmPassword.trim().length() == 0){
            Toast.makeText(getActivity (), "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }

            return true;
        }




 /*   private void confirmPassword(String phone, String password, String confirmPassword) {
        APIManger.getApis ().confirmPassword ( phone,password,confirmPassword ).enqueue ( new Callback<ForgetPassword> () {
            @Override
            public void onResponse(Call<ForgetPassword> call, Response<ForgetPassword> response) {


                if(response.isSuccessful ()) {

                    Toast.makeText ( getActivity (),response.body ().getMsg (),Toast.LENGTH_LONG ).show ();

                    fragment= new LoginFragment ();


                    getActivity ()
                            .getSupportFragmentManager ()
                            .beginTransaction ()
                            .replace (R.id.fram,fragment  )
                            .commit ();



                }

                else{
                    Toast.makeText ( getActivity (),response.body ().getMsg (),Toast.LENGTH_LONG ).show ();

                }


            }

            @Override
            public void onFailure(Call<ForgetPassword> call, Throwable t) {

                Toast.makeText(getActivity (), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        } );

    }*/
}
