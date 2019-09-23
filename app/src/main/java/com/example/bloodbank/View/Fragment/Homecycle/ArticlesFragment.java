package com.example.bloodbank.View.Fragment.Homecycle;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bloodbank.Adapter.ArticlesAdapter;
import com.example.bloodbank.Adapter.CustomSpinnerAdapter;
import com.example.bloodbank.Data.api.APIManger;
import com.example.bloodbank.Data.model.Auth.ClientData;
import com.example.bloodbank.Data.model.generalResponse.GeneralResponse;
import com.example.bloodbank.Data.model.generalResponse.GeneralResponseData;
import com.example.bloodbank.Data.model.posts.PostData;
import com.example.bloodbank.Data.model.posts.Posts;
import com.example.bloodbank.Helper.OnEndless;
import com.example.bloodbank.Helper.constant.Constant;
import com.example.bloodbank.R;
import com.example.bloodbank.View.Fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import static com.example.bloodbank.Data.local.SharedPreference.REMEMBER;
import static com.example.bloodbank.Data.local.SharedPreference.SaveData;
import static com.example.bloodbank.Data.local.SharedPreference.USER_DATA;
import static com.example.bloodbank.Data.local.SharedPreference.loadUserData;
import static com.example.bloodbank.Helper.HelperMethod.dismissProgressDialog;

public class ArticlesFragment extends BaseFragment {
    View view;
    ProgressDialog progressDialog;
    ArticlesAdapter articlesAdapter;
    @BindView(R.id.articles_fragment_recycler_view)
    RecyclerView articlesFragmentRecyclerView;
    Unbinder unbinder;
    CustomSpinnerAdapter categoryAdapter;
    List<String> categoryNames = new ArrayList<> ();
    List<String> categoryId = new ArrayList<> ();
    List<PostData> dataItems = new ArrayList<> ();
    @BindView(R.id.articles_fragment_et)
    EditText articlesFragmentEt;
    @BindView(R.id.articles_fragment_spinner)
    Spinner articlesFragmentSpinner;

    private LinearLayoutManager linearLayoutManager;
    private Integer maxPage = 0;
    private OnEndless onEndless;
    private int previousPage = 1;
    private ClientData clientData;
    private boolean Filter = false;


    public ArticlesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate ( R.layout.fragment_articles, container, false );

        unbinder = ButterKnife.bind ( this, view );

        clientData = loadUserData ( getActivity () );

        getCategories ();
        initRecyclerView ();
        return view;

    }


    public void initRecyclerView() {

        linearLayoutManager = new LinearLayoutManager ( getActivity () );
        articlesFragmentRecyclerView.setLayoutManager ( linearLayoutManager );

        onEndless = new OnEndless ( linearLayoutManager, 1 ) {
            @Override
            public void onLoadMore(int current_page) {

                if (maxPage != 0) {


                    getAllPosts ( current_page );
                }

                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        previousPage = current_page;
                        if (Filter) {
                            getPostsFilter ( current_page, getId ());

                        } else {
                            getAllPosts ( current_page );
                        }
                    } else {
                        dismissProgressDialog ();
                        onEndless.current_page = previousPage;
                    }
                } else {
                    onEndless.current_page = previousPage;
                }

            }


        };
        articlesFragmentRecyclerView.addOnScrollListener ( onEndless );


        articlesAdapter = new ArticlesAdapter ( dataItems, getContext () );
        articlesFragmentRecyclerView.setAdapter ( articlesAdapter );
        getAllPosts ( 1 );

//        dataItems = new ArrayList<> ();
//        articlesAdapter = new ArticlesAdapter ( dataItems, getContext () );
//        previousPage = 1;
//        onEndless.current_page = 1;
//        onEndless.previousTotal = 0;


    }


    public void getCategories() {
        APIManger.getApis ().getCategories ()
                .enqueue ( new Callback<GeneralResponse> () {
                    @Override
                    public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                        try {

                            if (response.body ().getStatus () == 1) {

                                response.body ().getData ().add ( 0, new GeneralResponseData ( "category", 0 ) );
                                categoryNames = new ArrayList<> ();
                                // categoryId = new ArrayList<> ();

                                for (int i = 0; i < response.body ().getData ().size (); i++) {
                                    categoryNames.add ( response.body ().getData ().get ( i ).getName () );
                                    //categoryId.add ( response.body ().getData ().get ( i ).getName ());
                                }

                                categoryAdapter = new CustomSpinnerAdapter ( getActivity ()
                                        , R.layout.item_custom_spinner,
                                        R.id.custom_spinner_adapter_tv_name, response.body ().getData (), categoryNames );

                                getPostsFilter ( previousPage,getId () );
                                articlesFragmentSpinner.setAdapter ( categoryAdapter );

                            } else {
                                Toast.makeText ( getActivity (), response.body ().getMsg (), Toast.LENGTH_LONG ).show ();
                            }

                        } catch (Exception e) {
                        }
                    }

                    @Override
                    public void onFailure(Call<GeneralResponse> call, Throwable t) {


                        try {
                            Log.d ( "", "on failure : " + toString () );

                        } catch (Exception e) {

                        }

                    }
                } );


    }


    private void getAllPosts(int page) {

        showProgreesBar ( R.string.loading );
        APIManger.getApis ().getAllPosts ( clientData.getApiToken (), page )
                .enqueue ( new Callback<Posts> () {
                    @Override
                    public void onResponse(Call<Posts> call, Response<Posts> response) {
                        hideProgressDialog ();

                        if (response.body ().getStatus () == 1) {
                            maxPage = response.body ().getData ().getLastPage ();

                            dataItems.addAll ( response.body ().getData ().getData () );
                            articlesAdapter.notifyDataSetChanged ();

                        }
                    }
                    //dataItems = response.body ().getData ().getData ();
                    //articlesAdapter.changeData ( dataItems );


                    @Override
                    public void onFailure(Call<Posts> call, Throwable t) {

                        try {
                            hideProgressDialog ();

                            Log.d ( "", "on failure : " + toString () );

                        } catch (Exception e) {

                        }

                    }
                } );

    }


   private void getPostsFilter(int page, final int category_id) {
        APIManger.getApis ().getPostsFilter ( clientData.getApiToken (),page,"",category_id)
                .enqueue ( new Callback<Posts> () {
                    @Override
                    public void onResponse(Call<Posts> call, Response<Posts> response) {
                        if (response.body ().getStatus () == 1) {

                           maxPage= response.body ().getData ().getCurrentPage ();

                            dataItems.addAll ( response.body ().getData ().getData ());
                          //articlesAdapter.getFilter ().filter ( );




                        }
                    }

                    @Override
                    public void onFailure(Call<Posts> call, Throwable t) {

                    }
                } );
    }


    public ProgressDialog showProgreesBar(int message) {
        progressDialog = new ProgressDialog ( getActivity () );
        progressDialog.setTitle ( message );
        progressDialog.setCancelable ( false );
        progressDialog.show ();
        return progressDialog;


    }


    public void hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss ();
        }
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
}
