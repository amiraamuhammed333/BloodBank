package com.example.bloodbank.View.Fragment.Homecycle;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.bloodbank.R;
import com.example.bloodbank.View.Fragment.BaseFragment;
import com.example.bloodbank.adapter.ArticlesAdapter;
import com.example.bloodbank.adapter.CustomSpinnerAdapter;
import com.example.bloodbank.data.api.APIManger;
import com.example.bloodbank.data.model.Auth.ClientData;
import com.example.bloodbank.data.model.generalResponse.GeneralResponse;
import com.example.bloodbank.data.model.generalResponse.GeneralResponseData;
import com.example.bloodbank.data.model.posts.PostData;
import com.example.bloodbank.data.model.posts.Posts;
import com.example.bloodbank.helper.OnEndless;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static com.example.bloodbank.data.local.SharedPreference.loadUserData;
import static com.example.bloodbank.helper.HelperMethod.dismissProgressDialog;

public class ArticlesFragment extends BaseFragment {
    View view;
    ArticlesAdapter articlesAdapter;
    @BindView(R.id.articles_fragment_recycler_view)
    RecyclerView articlesFragmentRecyclerView;
    Unbinder unbinder;
    CustomSpinnerAdapter categoryAdapter;
    List<String> categoryNames = new ArrayList<>();
    List<PostData> dataItems = new ArrayList<>();
    @BindView(R.id.articles_fragment_et)
    EditText articlesFragmentEt;
    @BindView(R.id.articles_fragment_spinner)
    Spinner articlesFragmentSpinner;
    @BindView(R.id.donation_fragment_ib_filter)
    ImageButton donationFragmentIbFilter;
    private boolean filter=false;
    private LinearLayoutManager linearLayoutManager;
    private Integer maxPage = 0;
    private OnEndless onEndless;
    private int previousPage = 1;
    private ClientData clientData;
    private boolean Filter = false;
    public boolean fivoret = false;

    public ArticlesFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_articles, container, false);
        unbinder = ButterKnife.bind(this, view);
        setUpActivity();
        clientData = loadUserData(getActivity());
        if (fivoret) { articlesFragmentEt.setVisibility(View.GONE);
                       articlesFragmentSpinner.setVisibility(View.GONE);
                       donationFragmentIbFilter.setVisibility(view.GONE);
                       initRecyclerView();
                       setUpHomeActivity();
                       homeActivity.setHomeActivityTitle("favourite");
        } else       { articlesFragmentEt.setVisibility(View.VISIBLE);
                       articlesFragmentSpinner.setVisibility(View.VISIBLE);
                       donationFragmentIbFilter.setVisibility(View.VISIBLE);
                       getCategories();
                       initRecyclerView(); }
        return view; }

        public void initRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        articlesFragmentRecyclerView.setLayoutManager(linearLayoutManager);
        onEndless = new OnEndless(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (maxPage != 0) {
                    getAllPosts(current_page); }
                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        previousPage = current_page;
                        if (Filter) {
                            getPostsFilter(current_page);
                        } else { getAllPosts(current_page); }
                    } else { dismissProgressDialog();
                              onEndless .current_page = previousPage; }
                } else { onEndless.current_page = previousPage; }
            }};
        articlesFragmentRecyclerView.addOnScrollListener(onEndless);
        articlesAdapter = new ArticlesAdapter(dataItems, getActivity(),getActivity());
        articlesFragmentRecyclerView.setAdapter(articlesAdapter);
        getAllPosts(1); }

    public void getCategories() {
        APIManger.getApis().getCategories()
                .enqueue(new Callback<GeneralResponse>() {
                    @Override
                    public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                        try {

                            if (response.body().getStatus() == 1) {

                                response.body().getData().add(0, new GeneralResponseData("category", 0));
                                categoryNames = new ArrayList<>();
                                // categoryId = new ArrayList<> ();

                                for (int i = 0; i < response.body().getData().size(); i++) {
                                    categoryNames.add(response.body().getData().get(i).getName());
                                    //categoryId.add ( response.body ().getData ().get ( i ).getName ());
                                }

                                categoryAdapter = new CustomSpinnerAdapter(getActivity()
                                        , R.layout.item_custom_spinner,
                                        R.id.custom_spinner_adapter_tv_name, response.body().getData(), categoryNames);

                                getPostsFilter(previousPage);
                                articlesFragmentSpinner.setAdapter(categoryAdapter);

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
    private void getAllPosts(int page) {
        Call<Posts> call;
        if (fivoret) { call = APIManger.getApis().getAllFavourites(clientData.getApiToken(), page);
        } else { call = APIManger.getApis().getAllPosts(clientData.getApiToken(), page); }
        call.enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                if (response.body().getStatus() == 1) {
                    maxPage = response.body().getData().getLastPage();
                    dataItems.addAll(response.body().getData().getData());
                    articlesAdapter.notifyDataSetChanged(); } }
            @Override
            public void onFailure(Call<Posts> call, Throwable t) {
                try { Log.d("", "on failure : " + toString()); } catch (Exception e) { } }}); }


    private void getPostsFilter(int page) {
        filter = true;
        onEndless.previousTotal = 0;
        onEndless.current_page = 1;
        onEndless.privious_page = 1;
        dataItems = new ArrayList<>();
        articlesAdapter = new ArticlesAdapter(dataItems,getActivity(),getActivity());
        articlesFragmentRecyclerView.setAdapter(articlesAdapter);
        APIManger.getApis().getPostsFilter(clientData.getApiToken(), page, "", categoryAdapter.selectId)
                .enqueue(new Callback<Posts>() {
                    @Override
                    public void onResponse(Call<Posts> call, Response<Posts> response) {
                        if (response.body().getStatus() == 1) {
                            maxPage = response.body().getData().getCurrentPage();
                            dataItems.addAll(response.body().getData().getData());
                            articlesAdapter.notifyDataSetChanged();
                           }}
                    @Override
                    public void onFailure(Call<Posts> call, Throwable t) { }}); }

    @OnClick(R.id.donation_fragment_ib_filter)
    public void onViewClicked() {
        getPostsFilter(1);
    }

//    public ProgressDialog showProgreesBar(int message) {
//        progressDialog = new ProgressDialog(getActivity());
//        progressDialog.setTitle(message);
//        progressDialog.setCancelable(false);
//        progressDialog.show();
//        return progressDialog; }
//    public void hideProgressDialog() {
//            if (progressDialog != null) { progressDialog.dismiss(); } }

    @Override
    public void onBack() {
        super.onBack();
    }

    @Override
    public void onDestroyView() { super.onDestroyView();
                                  unbinder.unbind(); }
}
