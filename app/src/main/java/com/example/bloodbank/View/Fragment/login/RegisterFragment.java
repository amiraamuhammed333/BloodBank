package com.example.bloodbank.View.Fragment.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodbank.adapter.CustomSpinnerAdapter;
import com.example.bloodbank.data.api.APIManger;
import com.example.bloodbank.data.model.Auth.AuthResponse;
import com.example.bloodbank.data.model.DateModel;
import com.example.bloodbank.data.model.generalResponse.GeneralResponseData;
import com.example.bloodbank.data.model.generalResponse.GeneralResponse;
import com.example.bloodbank.helper.HelperMethod;
import com.example.bloodbank.R;
import com.example.bloodbank.View.Fragment.BaseFragment;
import com.example.bloodbank.View.Activity.HomeActivity;
import com.example.bloodbank.View.Fragment.Homecycle.HomeFragment;

import java.util.ArrayList;
import java.util.List;

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
import static com.example.bloodbank.helper.HelperMethod.showCalender;

public class RegisterFragment extends BaseFragment {
    View view;
    @BindView(R.id.register_fragment_tb)
    Toolbar registerFragmentTb;
    @BindView(R.id.register_fragment_tv_title)
    TextView registerFragmentTvTitle;
    @BindView(R.id.register_fragment_et_name)
    EditText registerFragmentEtName;
    @BindView(R.id.register_fragment_et_email)
    EditText registerFragmentEtEmail;
    @BindView(R.id.register_fragment_et_birth)
    TextView registerFragmentEtBirth;
    @BindView(R.id.register_fragment_et_blood_type)
    EditText registerFragmentEtBloodType;
    @BindView(R.id.register_fragment_et_spin1)
    Spinner registerFragmentEtSpin1;
    @BindView(R.id.register_fragment_et_last_date)
    TextView registerFragmentEtLastDate;
    @BindView(R.id.register_fragment_et_government)
    EditText registerFragmentEtGovernment;
    @BindView(R.id.register_fragment_et_spin2)
    Spinner registerFragmentEtSpin2;
    @BindView(R.id.register_fragment_et_city)
    EditText registerFragmentEtCity;
    @BindView(R.id.register_fragment_et_spin3)
    Spinner registerFragmentEtSpin3;
    @BindView(R.id.register_fragment_et_phone)
    EditText registerFragmentEtPhone;
    @BindView(R.id.register_fragment_et_password)
    EditText registerFragmentEtPassword;
    @BindView(R.id.register_fragment_et_confirm_password)
    EditText registerFragmentEtConfirmPassword;
    @BindView(R.id.register_fragment_btn_register)
    Button registerFragmentBtnRegister;
    @BindView(R.id.register_fragment_rl_sub_view)
    RelativeLayout registerFragmentRlSubView;
    Unbinder unbinder;
    Fragment fragment;

    List<String> bloodTypeNames = new ArrayList<>();
    List<String> governmentNames = new ArrayList<>();
    List<String> citiesNames = new ArrayList<>();


    CustomSpinnerAdapter bloodTypeAdapter;
    CustomSpinnerAdapter governmentAdapter;
    CustomSpinnerAdapter cityAdapter;

    private DateModel birthday, lastTime;


    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_register, container, false);
        unbinder = ButterKnife.bind(this, view);

        getBloodTypes();
        getgovernment();
        birthday = new DateModel("01", "01", "1970", "1970-01-01");
        lastTime = new DateModel("01", "01", "1970", "1970-01-01");


        return view;

    }


    private void getBloodTypes() {
        APIManger.getApis().getBloodTypes()
                .enqueue(new Callback<GeneralResponse>() {
                    @Override
                    public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                        try {
                            if (response.body().getStatus() == 1) {

                                response.body().getData().add(0, new GeneralResponseData(getString(R.string.blood_type), 0));
                                // bloodTypeIds=new ArrayList<> (  );
                                bloodTypeNames = new ArrayList<>();

                                for (int i = 0; i < response.body().getData().size(); i++) {

                                    // bloodTypeIds.add ( response.body ().getData ().get ( i ).getId () );
                                    bloodTypeNames.add(response.body().getData().get(i).getName());

                                }
                                bloodTypeAdapter = new CustomSpinnerAdapter(getActivity()
                                        , R.layout.item_custom_spinner,
                                        R.id.custom_spinner_adapter_tv_name, response.body().getData(), bloodTypeNames);


                                registerFragmentEtSpin1.setAdapter(bloodTypeAdapter);
                            } else {
                                Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception e) {


                        }

                    }

                    @Override
                    public void onFailure(Call<GeneralResponse> call, Throwable t) {

                        try {
                            Log.d("", "on failure : " + toString());

                        } catch (Exception e) {

                        }
                    }
                });
    }


    private void getgovernment() {

        APIManger.getApis().getGovernment()
                .enqueue(new Callback<GeneralResponse>() {
                    @Override
                    public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                        try {

                            if (response.body().getStatus() == 1) {
                                response.body().getData().add(0, new GeneralResponseData(getString(R.string.government), 0));

                                governmentNames = new ArrayList<>();
                                for (int i = 0; i < response.body().getData().size(); i++) {

                                    governmentNames.add(response.body().getData().get(i).getName());
                                }
                                governmentAdapter = new CustomSpinnerAdapter(getActivity()
                                        , R.layout.item_custom_spinner,
                                        R.id.custom_spinner_adapter_tv_name, response.body().getData(), governmentNames);


                                registerFragmentEtSpin2.setAdapter(governmentAdapter);
                                registerFragmentEtSpin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                                        if (position != 0) {
                                            getCities(governmentAdapter.selectId);
                                        } else {
                                            registerFragmentEtCity.setVisibility(View.GONE);
                                            registerFragmentEtSpin3.setVisibility(View.GONE);

                                        }

                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {


                                    }
                                });

                            } else {
                                Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();


                            }


                        } catch (Exception e) {

                        }
                    }

                    @Override
                    public void onFailure(Call<GeneralResponse> call, Throwable t) {
                        try {
                            Log.d("", "on failure : " + toString());

                        } catch (Exception e) {

                        }

                    }
                });

    }

    private void getCities(int selectId) {
        APIManger.getApis().getCities(selectId)
                .enqueue(new Callback<GeneralResponse>() {
                    @Override
                    public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                        try {
                            if (response.body().getStatus() == 1) {


                                registerFragmentEtCity.setVisibility(View.VISIBLE);
                                registerFragmentEtSpin3.setVisibility(View.VISIBLE);

                                response.body().getData().add(0, new GeneralResponseData(getString(R.string.city), 0));
                                // bloodTypeIds=new ArrayList<> (  );
                                citiesNames = new ArrayList<>();

                                for (int i = 0; i < response.body().getData().size(); i++) {

                                    // bloodTypeIds.add ( response.body ().getData ().get ( i ).getId () );
                                    citiesNames.add(response.body().getData().get(i).getName());

                                }
                                cityAdapter = new CustomSpinnerAdapter(getActivity()
                                        , R.layout.item_custom_spinner,
                                        R.id.custom_spinner_adapter_tv_name, response.body().getData(), citiesNames);


                                registerFragmentEtSpin3.setAdapter(cityAdapter);
                            } else {
                                Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception e) {


                        }

                    }

                    @Override
                    public void onFailure(Call<GeneralResponse> call, Throwable t) {

                        try {
                            Log.d("", "on failure : " + toString());

                        } catch (Exception e) {

                        }
                    }
                });


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.register_fragment_rl_sub_view, R.id.register_fragment_et_birth, R.id.register_fragment_et_last_date,
            R.id.register_fragment_btn_register})
    public void onViewClicked(View view) {
        HelperMethod.disappearKeypad(getActivity(), view);
        switch (view.getId()) {
            case R.id.register_fragment_rl_sub_view:
                break;
            case R.id.register_fragment_et_birth:
                showCalender(getActivity(), getString(R.string.birthday), registerFragmentEtBirth, birthday);

                break;
            case R.id.register_fragment_et_last_date:
                showCalender(getActivity(), getString(R.string.last_donation_date), registerFragmentEtLastDate, birthday);

                break;
            case R.id.register_fragment_btn_register:
                onRegister();
                fragment = new HomeFragment();

                break;
        }

    }


    public boolean validate() {
        boolean valid = true;
        if (registerFragmentEtName.length() < 1 || registerFragmentEtName.length() > 32) {
            registerFragmentEtName.setError("Please enter valid name");
            valid = false;
        }
        if (registerFragmentEtLastDate.length() < 1 || registerFragmentEtLastDate.length() > 32) {
            registerFragmentEtLastDate.setError("Please enter valid name");
            valid = false;
        }
        if (registerFragmentEtEmail.length() < 1 || registerFragmentEtEmail.length() > 32) {
            registerFragmentEtEmail.setError("Please enter valid email address");
            valid = false;
        }
        if (registerFragmentEtPassword.length() < 1) {
            registerFragmentEtPassword.setError("Please enter valid password");
            valid = false;
        }
        if (registerFragmentEtConfirmPassword.length() < 1) {
            registerFragmentEtConfirmPassword.setError("Please enter valid password");
            valid = false;
        }
        if (!registerFragmentEtConfirmPassword.equals(registerFragmentEtPassword)) {
            registerFragmentEtPassword.setError("Passwords don't match!");
            valid = false;
        }
        if (registerFragmentEtBirth.length() < 1 || registerFragmentEtBirth.length() > 32) {
            registerFragmentEtBirth.setError("Please enter valid name");
            valid = false;
        } else {
            Toast.makeText(getActivity(), "Success", Toast.LENGTH_LONG).show();
        }
        return valid;
    }


    private void onRegister() {

        String name = registerFragmentEtName.getText().toString();
        String email = registerFragmentEtEmail.getText().toString();
        String birth_date = registerFragmentEtBirth.getText().toString();
        int city_id = cityAdapter.selectId;
        String phone = registerFragmentEtPhone.getText().toString();
        String donation_last_date = registerFragmentEtLastDate.getText().toString();
        final String password = registerFragmentEtPassword.getText().toString();
        String password_confirmation = registerFragmentEtConfirmPassword.getText().toString();
        int blood_type_id = bloodTypeAdapter.selectId;

        validate();

        APIManger.getApis().onRegister(name, email, birth_date, city_id, phone, donation_last_date, password, password_confirmation
                , blood_type_id).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {

                try {

                    if (response.body().getStatus() == 1) {
                        SaveData(getActivity(), USER_DATA, response.body().getData());
                        SaveData(getActivity(), REMEMBER, true);
                        SaveData ( getActivity (), PASSWORD, password );

                        if (response.isSuccessful()) {

                            Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();


                            Intent intent = new Intent(getActivity(), HomeActivity.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                        }


                    }
                    Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {

            }
        });

    }
}

















