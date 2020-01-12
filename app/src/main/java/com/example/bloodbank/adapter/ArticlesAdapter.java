package com.example.bloodbank.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bloodbank.data.api.APIManger;
import com.example.bloodbank.data.model.Auth.ClientData;
import com.example.bloodbank.data.model.posts.PostData;
import com.example.bloodbank.R;
import com.example.bloodbank.View.Fragment.Homecycle.DetailArticleFragment;
import com.example.bloodbank.data.model.posts.Posts;
import com.example.bloodbank.helper.HelperMethod;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import static com.example.bloodbank.data.local.SharedPreference.loadUserData;
import static com.example.bloodbank.helper.HelperMethod.onLoadImageFromUrl;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder>  {


    ClientData clientData;
    FragmentActivity baseActivity;
    List<PostData> postDataList = new ArrayList<> ();
    Context context;

    public ArticlesAdapter(List<PostData> dataItems, FragmentActivity activity,Context context) {
        this.postDataList = dataItems;
        this.context = context;
        baseActivity=(activity);
        clientData = loadUserData ( (Activity) context ); }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from ( viewGroup.getContext () ).inflate ( R.layout.item_article_list, viewGroup, false );
        return new ViewHolder ( view ); }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int pos) {
        final PostData item = postDataList.get ( pos );
        viewHolder.articleAdapterTvTitle.setText ( item.getTitle () );
        onLoadImageFromUrl ( viewHolder.articleAdapterIvPostImage, item.getThumbnailFullPath (), context );
        viewHolder.itemView.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View view) {
             DetailArticleFragment detailArticleFragment = new DetailArticleFragment ();
             detailArticleFragment.articleRequest = postDataList.get ( pos );
             HelperMethod.ReplaceFragment(baseActivity.getSupportFragmentManager(), detailArticleFragment, R.id.fram, null, null); }} );
        viewHolder.articleAdapterIvAddAndRemove.setChecked ( postDataList.get ( pos ).getIsFavourite () );
        viewHolder.articleAdapterIvAddAndRemove.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) { addafavourite ( viewHolder, pos);}} ); }

    public void addafavourite(final ViewHolder viewHolder, final int pos) {
        APIManger.getApis ().addFavourite ( postDataList.get ( pos ).getId (), clientData.getApiToken () )
                .enqueue ( new Callback<Posts> () {
                    @Override
                    public void onResponse(Call<Posts> call, Response<Posts> response) {
                        if (response.body ().getStatus () == 1) {
                            //viewHolder.articleAdapterIvAddAndRemove.setChecked ( postDataList.get ( pos ).getIsFavourite () );
                            postDataList.get ( pos ).setIsFavourite ( postDataList.get ( pos ).getIsFavourite () );
                         }}
                    @Override
                    public void onFailure(Call<Posts> call, Throwable t) { }} ); }

    @Override
    public int getItemCount() { return postDataList.size (); }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.article_adapter_tv_title)
        TextView articleAdapterTvTitle;
        @BindView(R.id.article_adapter_iv_add_and_remove)
        CheckBox articleAdapterIvAddAndRemove;
        @BindView(R.id.article_adapter_iv_post_image)
        ImageView articleAdapterIvPostImage;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super ( itemView );
            view = itemView;
            ButterKnife.bind ( this, view ); }}
}
