package com.example.bloodbank.View.Fragment.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bloodbank.data.api.APIManger;
import com.example.bloodbank.R;
import com.example.bloodbank.View.Fragment.BaseFragment;
import com.example.bloodbank.data.model.Auth.ResetResponse;
import com.google.android.gms.common.api.Api;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetFragment extends BaseFragment {
    View view;
    Fragment fragment;
    @BindView(R.id.forget_fragment_et)
    EditText forgetFragmentEt;
    @BindView(R.id.forget_fragment_btn)
    Button forgetFragmentBtn;
    Unbinder unbinder;

    public ForgetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate ( R.layout.fragment_forget, container, false );
        unbinder = ButterKnife.bind ( this, view );
        return view;
    }

    private void getPhone() {
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView ();
        unbinder.unbind ();
    }
    String phone;

    @OnClick(R.id.forget_fragment_btn)
    public void onViewClicked(View view) {
        if (view.getId () == R.id.forget_fragment_btn){

             phone = forgetFragmentEt.getText ().toString ();

            if(validateSend(phone)){
                //do login
                send ();
            }


           //  fragment= new ChangeNumberFragment ();
        }
       // getActivity ()
         //       .getSupportFragmentManager ()
           //     .beginTransaction ()
             //   .replace (R.id.fram,fragment  )
               // .commit ();
    }


    private boolean validateSend(String phone){
        if(phone == null || phone.trim().length() == 0){
            Toast.makeText(getActivity (), "Username is required", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void send(){
        APIManger.getApis().getPhone(phone).enqueue(new Callback<ResetResponse>() {
            @Override
            public void onResponse(Call<ResetResponse> call, Response<ResetResponse> response) {

                if(response.isSuccessful ()) {

                    Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();

                    fragment = new ChangeNumberFragment();


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





    }









