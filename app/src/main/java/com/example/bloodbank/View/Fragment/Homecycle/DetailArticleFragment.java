package com.example.bloodbank.View.Fragment.Homecycle;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bloodbank.adapter.ArticlesAdapter;
import com.example.bloodbank.data.api.APIManger;
import com.example.bloodbank.data.model.Auth.ClientData;
import com.example.bloodbank.data.model.posts.OnePosts;
import com.example.bloodbank.data.model.posts.PostData;
import com.example.bloodbank.R;
import com.example.bloodbank.View.Fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.local.SharedPreference.loadUserData;
import static com.example.bloodbank.helper.HelperMethod.onLoadImageFromUrl;

public class DetailArticleFragment extends BaseFragment {
    View view;
    @BindView(R.id.fragment_detail_article_image)
    ImageView fragmentDetailArticleImage;
    @BindView(R.id.fragment_detail_article_title)
    TextView fragmentDetailArticleTitle;
    @BindView(R.id.fragment_detail_article_favorite)
    ImageView fragmentDetailArticleFavorite;
    @BindView(R.id.fragment_detail_article_text)
    TextView fragmentDetailArticleText;
    Unbinder unbinder;
    public PostData articleRequest;
    ArticlesAdapter articlesAdapter;
    public int id;
    ClientData clientData;

    public DetailArticleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate ( R.layout.fragment_detail_article, container, false );
        unbinder = ButterKnife.bind ( this, view );
        clientData = loadUserData ( getActivity () );
        setUpActivity();
        setUpHomeActivity ();
        homeActivity.setHomeActivityTitle("Detail");
        getDetail();
        return view;
    }

    public void getDetail(){
        fragmentDetailArticleText.setText ( articleRequest.getContent ());
        fragmentDetailArticleTitle.setText ( articleRequest.getTitle () );
        onLoadImageFromUrl ( fragmentDetailArticleImage, articleRequest.getThumbnailFullPath (),getActivity () );
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
