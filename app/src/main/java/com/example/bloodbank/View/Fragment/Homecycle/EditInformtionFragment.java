package com.example.bloodbank.View.Fragment.Homecycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodbank.R;
import com.example.bloodbank.View.Activity.HomeActivity;
import com.example.bloodbank.View.Fragment.BaseFragment;
import com.example.bloodbank.adapter.CustomSpinnerAdapter;
import com.example.bloodbank.data.api.APIManger;
import com.example.bloodbank.data.model.Auth.AuthResponse;
import com.example.bloodbank.data.model.Auth.Client;
import com.example.bloodbank.data.model.Auth.ClientData;
import com.example.bloodbank.data.model.generalResponse.GeneralResponse;
import com.example.bloodbank.data.model.generalResponse.GeneralResponseData;
import com.example.bloodbank.data.model.profile.Data;
import com.example.bloodbank.data.model.profile.ProfileResponse;
import com.example.bloodbank.helper.HelperMethod;

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
import static com.example.bloodbank.data.local.SharedPreference.loadStringData;
import static com.example.bloodbank.data.local.SharedPreference.loadUserData;

public class EditInformtionFragment extends BaseFragment {
    View view;
    @BindView(R.id.edit_information_fragment_et_name)
    EditText editInformationFragmentEtName;
    @BindView(R.id.edit_information_fragment_et_email)
    EditText editInformationFragmentEtEmail;
    @BindView(R.id.edit_information_fragment_et_birth)
    TextView editInformationFragmentEtBirth;
    @BindView(R.id.edit_information_fragment_et_blood_type)
    EditText editInformationFragmentEtBloodType;
    @BindView(R.id.edit_information_fragment_et_spin_blood_type)
    Spinner editInformationFragmentEtSpinBloodType;
    @BindView(R.id.edit_information_fragment_et_last_date)
    EditText editInformationFragmentEtLastDate;
    @BindView(R.id.edit_information_fragment_et_government)
    EditText editInformationFragmentEtGovernment;
    @BindView(R.id.edit_information_fragment_et_spin_government)
    Spinner editInformationFragmentEtSpinGovernment;
    @BindView(R.id.edit_information_fragment_et_city)
    EditText editInformationFragmentEtCity;
    @BindView(R.id.edit_information_fragment_et_spin_city)
    Spinner editInformationFragmentEtSpinCity;
    @BindView(R.id.edit_information_fragment_et_phone)
    EditText editInformationFragmentEtPhone;
    @BindView(R.id.edit_information_fragment_et_password)
    EditText editInformationFragmentEtPassword;
    @BindView(R.id.edit_information_fragment_et_confirm_password)
    EditText editInformationFragmentEtConfirmPassword;
    @BindView(R.id.edit_information_fragment_btn_edit)
    Button editInformationFragmentBtnEdit;
    Unbinder unbinder;

    ClientData clientData;
    List<String> bloodTypeNames = new ArrayList<>();
    List<String> governmentNames = new ArrayList<>();
    List<String> citiesNames = new ArrayList<>();

    CustomSpinnerAdapter bloodTypeAdapter;
    CustomSpinnerAdapter governmentAdapter;
    CustomSpinnerAdapter cityAdapter;
    private String password;

    public EditInformtionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setUpActivity();
        setUpHomeActivity ();
        view = inflater.inflate(R.layout.fragment_edit_information, container, false);
        homeActivity.setHomeActivityTitle("Edit Information");
        unbinder = ButterKnife.bind(this, view);
        clientData = loadUserData(getActivity());
        password = loadStringData(getActivity(), PASSWORD);
        getProfileInformation();
        getBloodTypes();
        getgovernment();
        return view; }

    public void getProfileInformation() {
        editInformationFragmentEtName.setText(clientData.getClient().getName());
        editInformationFragmentEtEmail.setText(clientData.getClient().getEmail());
        editInformationFragmentEtBirth.setText(clientData.getClient().getBirthDate());
        editInformationFragmentEtPhone.setText(clientData.getClient().getPhone());
        editInformationFragmentEtLastDate.setText(clientData.getClient().getDonationLastDate());
        editInformationFragmentEtPassword.setText(password);
        editInformationFragmentEtConfirmPassword.setText(password); }

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

                                int pos = 0;
                                for (int i = 0; i < response.body().getData().size(); i++) {
                                    bloodTypeNames.add(response.body().getData().get(i).getName());
                                    if (clientData.getClient().getBloodType().getId() == response.body().getData().get(i).getId()) {
                                        pos = i;
                                        break;
                                    }
                                }

                                bloodTypeAdapter = new CustomSpinnerAdapter(getActivity()
                                        , R.layout.item_custom_spinner,
                                        R.id.custom_spinner_adapter_tv_name, response.body().getData(), bloodTypeNames);

                                editInformationFragmentEtSpinBloodType.setAdapter(bloodTypeAdapter);
                                editInformationFragmentEtSpinBloodType.setSelection(pos);
                            } else {
                                Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception e) {
                        } }

                    @Override
                    public void onFailure(Call<GeneralResponse> call, Throwable t) {
                        try {
                            Log.d("", "on failure : " + toString());

                        } catch (Exception e) {
                        } }
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

                                int pos = 0;
                                for (int i = 0; i < response.body().getData().size(); i++) {
                                    governmentNames.add(response.body().getData().get(i).getName());
                                    if (clientData.getClient().getCity().getGovernorate().getId() == response.body().getData().get(i).getId()) {
                                        pos = i;
                                        break;
                                    }
                                }

                                governmentAdapter = new CustomSpinnerAdapter(getActivity()
                                        , R.layout.item_custom_spinner,
                                        R.id.custom_spinner_adapter_tv_name, response.body().getData(), governmentNames);

                                editInformationFragmentEtSpinGovernment.setAdapter(governmentAdapter);
                                editInformationFragmentEtSpinGovernment.setSelection(pos);
                                editInformationFragmentEtSpinGovernment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                                        if (position != 0) {
                                            getCities(governmentAdapter.selectId);
                                        } else {
                                            editInformationFragmentEtCity.setVisibility(View.GONE);
                                            editInformationFragmentEtSpinCity.setVisibility(View.GONE);

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

                                editInformationFragmentEtCity.setVisibility(View.VISIBLE);
                                editInformationFragmentEtSpinCity.setVisibility(View.VISIBLE);

                                response.body().getData().add(0, new GeneralResponseData(getString(R.string.city), 0));

                                int pos = 0;
                                for (int i = 0; i < response.body().getData().size(); i++) {
                                    citiesNames.add(response.body().getData().get(i).getName());
                                    if (clientData.getClient().getCity().getId() == response.body().getData().get(i).getId()) {
                                        pos = i;
                                        break;
                                    }
                                }

                                cityAdapter = new CustomSpinnerAdapter(getActivity()
                                        , R.layout.item_custom_spinner,
                                        R.id.custom_spinner_adapter_tv_name, response.body().getData(), citiesNames);


                                editInformationFragmentEtSpinCity.setAdapter(cityAdapter);
                                editInformationFragmentEtSpinCity.setSelection(pos);
                            } else {
                                Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                            }

                        } catch (
                                Exception e) {


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
    public void onBack() {
        super.onBack(); }



    Fragment fragment;
    @OnClick(R.id.edit_information_fragment_btn_edit)
    public void onViewClicked() {
        onRegister();
        fragment = new HomeFragment(); }

    private void onRegister() {
        String name = editInformationFragmentEtName.getText().toString();
        String email = editInformationFragmentEtEmail.getText().toString();
        String birth_date = editInformationFragmentEtBirth.getText().toString();
        int city_id = cityAdapter.selectId;
        String phone = editInformationFragmentEtPhone.getText().toString();
        String donation_last_date = editInformationFragmentEtLastDate.getText().toString();
        final String password = editInformationFragmentEtPassword.getText().toString();
        String password_confirmation = editInformationFragmentEtConfirmPassword.getText().toString();
        int blood_type_id = bloodTypeAdapter.selectId;
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
                            Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show(); } }
                    Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                } catch (Exception e) { } }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
            }}); }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
