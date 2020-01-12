package com.example.bloodbank.View.Fragment.Homecycle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodbank.R;
import com.example.bloodbank.View.Fragment.BaseFragment;
import com.example.bloodbank.data.api.APIManger;
import com.example.bloodbank.data.model.Auth.ClientData;
import com.example.bloodbank.data.model.aboutApp.AboutResponse;
import com.example.bloodbank.data.model.generalResponse.ContactResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.local.SharedPreference.loadUserData;

public class Contact_Us_Fragment extends BaseFragment {
    View view;
    @BindView(R.id.contact_us_iv_logo)
    ImageView contactUsIvLogo;
    @BindView(R.id.contact_us_tv_phone)
    TextView contactUsTvPhone;
    @BindView(R.id.contact_us_tv_email)
    TextView contactUsTvEmail;
    @BindView(R.id.contact_us_ib_facebook)
    ImageButton contactUsIbFacebook;
    @BindView(R.id.contact_us_ib_tweeter)
    ImageButton contactUsIbTweeter;
    @BindView(R.id.contact_us_ib_youtube)
    ImageButton contactUsIbYoutube;
    @BindView(R.id.contact_us_ib_instegram)
    ImageButton contactUsIbInstegram;
    @BindView(R.id.contact_us_ib_whats)
    ImageButton contactUsIbWhats;
    @BindView(R.id.contact_us_ib_google)
    ImageButton contactUsIbGoogle;
    @BindView(R.id.contact_us_tv_contact)
    TextView contactUsTvContact;

    @BindView(R.id.contact_us_et_message_address)
    EditText contactUsEtMessageAddress;
    @BindView(R.id.contact_us_et_message)
    EditText contactUsEtMessage;
    @BindView(R.id.contact_us_btn_send)
    Button contactUsBtnSend;
    Unbinder unbinder;
    ClientData clientData;
    Intent intent;
    Fragment fragment;


    public Contact_Us_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_contact, container, false);
        setUpActivity();
        setUpHomeActivity ();
        homeActivity.setHomeActivityTitle("contact us");
        unbinder = ButterKnife.bind(this, view);
        clientData = loadUserData(getActivity());


        return view;
    }


    public void conrtactUs() {
        APIManger.getApis().contactUs()
                .enqueue(new Callback<ContactResponse>() {
                    @Override
                    public void onResponse(Call<ContactResponse> call, Response<ContactResponse> response) {
                        APIManger.getApis().contactUs()
                                .enqueue(new Callback<ContactResponse>() {
                                    @Override
                                    public void onResponse(Call<ContactResponse> call, Response<ContactResponse> response) {

                                        try {

                                            if (response.body().getStatus() == 1) {
                                                response.body().getData().getClientId();
                                            }

                                        } catch (Exception e) {

                                        }

                                    }

                                    @Override
                                    public void onFailure(Call<ContactResponse> call, Throwable t) {

                                    }
                                });

                    }

                    @Override
                    public void onFailure(Call<ContactResponse> call, Throwable t) {

                    }
                });
    }


    @Override
    public void onBack() {
        super.onBack();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.contact_us_ib_facebook, R.id.contact_us_ib_tweeter
            , R.id.contact_us_ib_youtube, R.id.contact_us_ib_instegram
            , R.id.contact_us_ib_whats, R.id.contact_us_ib_google
            , R.id.contact_us_btn_send})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.contact_us_ib_facebook:
                APIManger.getApis().getAboutApp(clientData.getApiToken())
                        .enqueue(new Callback<AboutResponse>() {
                            @Override
                            public void onResponse(Call<AboutResponse> call, Response<AboutResponse> response) {


                                try {
                                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse(response.body().getData().getFacebookUrl()));
                                    startActivity(intent);
                                } catch (Exception e) {

                                }

                            }

                            @Override
                            public void onFailure(Call<AboutResponse> call, Throwable t) {

                            }
                        });
                break;
            case R.id.contact_us_ib_tweeter:

                APIManger.getApis().getAboutApp(clientData.getApiToken()).enqueue(new Callback<AboutResponse>() {
                    @Override
                    public void onResponse(Call<AboutResponse> call, Response<AboutResponse> response) {
                        try {

                            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(response.body().getData().getTwitterUrl()));
                            startActivity(intent);

                        } catch (Exception e) {

                        }

                    }

                    @Override
                    public void onFailure(Call<AboutResponse> call, Throwable t) {

                    }
                });
                break;
            case R.id.contact_us_ib_youtube:
                APIManger.getApis().getAboutApp(clientData.getApiToken()).enqueue(new Callback<AboutResponse>() {
                    @Override
                    public void onResponse(Call<AboutResponse> call, Response<AboutResponse> response) {
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse(response.body().getData().getYoutubeUrl()));
                        startActivity(intent);

                    }

                    @Override
                    public void onFailure(Call<AboutResponse> call, Throwable t) {

                    }
                });
                break;
            case R.id.contact_us_ib_instegram:
                APIManger.getApis().getAboutApp(clientData.getApiToken()).enqueue(new Callback<AboutResponse>() {
                    @Override
                    public void onResponse(Call<AboutResponse> call, Response<AboutResponse> response) {
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse(response.body().getData().getInstagramUrl()));
                        startActivity(intent);

                    }

                    @Override
                    public void onFailure(Call<AboutResponse> call, Throwable t) {

                    }
                });
                break;
            case R.id.contact_us_ib_whats:
                APIManger.getApis().getAboutApp(clientData.getApiToken()).enqueue(new Callback<AboutResponse>() {
                    @Override
                    public void onResponse(Call<AboutResponse> call, Response<AboutResponse> response) {
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse(response.body().getData().getWhatsapp()
                        ));
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<AboutResponse> call, Throwable t) {

                    }
                });
                break;
            case R.id.contact_us_ib_google:
                APIManger.getApis().getAboutApp(clientData.getApiToken()).enqueue(new Callback<AboutResponse>() {
                    @Override
                    public void onResponse(Call<AboutResponse> call, Response<AboutResponse> response) {
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse(response.body().getData().getGoogleUrl()));
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<AboutResponse> call, Throwable t) {

                    }
                });
                break;
           /* case R.id.contact_us_btn_contact_us:
                APIManger.getApis().getAboutApp(clientData.getApiToken()).enqueue(new Callback<AboutResponse>() {
                    @Override
                    public void onResponse(Call<AboutResponse> call, Response<AboutResponse> response) {

                         intent = new Intent(Intent.ACTION_CALL,Uri.parse(response.body().getData().getPhone()));
                         startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<AboutResponse> call, Throwable t) {

                    }
                });
                break;*/
            case R.id.contact_us_btn_send:
                send();

                break;
        }
    }

    private void send() {

        String message_title = contactUsEtMessageAddress.getText().toString();
        String message = contactUsEtMessage.getText().toString();

        APIManger.getApis().contact(clientData.getApiToken(), message_title, message)
                .enqueue(new Callback<ContactResponse>() {
                    @Override
                    public void onResponse(Call<ContactResponse> call, Response<ContactResponse> response) {

                        try {
                            if (response.isSuccessful()) {

                                Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();

                                fragment = new ArticlesFragment();


                                getActivity()
                                        .getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.fram, fragment)
                                        .commit();
                            } else {
                                Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();

                            }

                        } catch (Exception e) {

                        }

                    }

                    @Override
                    public void onFailure(Call<ContactResponse> call, Throwable t) {

                    }
                });


    }

    @OnClick(R.id.contact_us_tv_phone)
    public void onClick() {
        APIManger.getApis().getAboutApp(clientData.getApiToken())
                .enqueue(new Callback<AboutResponse>() {
                    @Override
                    public void onResponse(Call<AboutResponse> call, Response<AboutResponse> response) {
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData( Uri.parse("tel:" + response.body().getData().getPhone()));
                        startActivity(intent);

                    }

                    @Override
                    public void onFailure(Call<AboutResponse> call, Throwable t) {

                    }
                });
    }
}
