package com.example.bloodbank.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bloodbank.Data.model.posts.PostData;
import com.example.bloodbank.R;
import com.example.bloodbank.View.Activity.BaseActivity;

import java.util.ArrayList;
import java.util.Formattable;
import java.util.Formatter;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.bloodbank.Helper.HelperMethod.onLoadImageFromUrl;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> implements Filterable {

    List<PostData> postDataList = new ArrayList<> ();
    List<PostData> postDataListFull = new ArrayList<> ();
Context context;
    public ArticlesAdapter(List<PostData> dataItems, Context context) {
        this.postDataList = dataItems;
        postDataListFull= new ArrayList<> ( postDataList );
        this.context = context;
    }



    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from ( viewGroup.getContext () )
                .inflate ( R.layout.item_article_list, viewGroup, false );
        return new ViewHolder ( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int pos) {

        PostData item = postDataList.get ( pos );
        viewHolder.articleAdapterTvTitle.setText ( item.getTitle () );

        onLoadImageFromUrl ( viewHolder.articleAdapterIvPostImage, item.getThumbnailFullPath (),context );


    }

    @Override
    public int getItemCount() {
        return postDataList.size ();
    }

    public void changeData(List<PostData> items) {

        postDataList = items;
        notifyDataSetChanged ();
    }


    public Filter getFilter(){
        return exampleFilter;

    }

    private Filter exampleFilter = new Filter () {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<PostData> filteredList = new ArrayList<> (  );
            if (constraint==null && constraint.length ()==0){
                filteredList.addAll ( postDataListFull );
            }else {
                String filterpattern=constraint.toString ().toLowerCase ().trim ();
                for(PostData  item: postDataListFull){
                    if (item.getTitle ().toLowerCase ().contains ( filterpattern )){
                        filteredList.add (item  );

                    }
                }
            }
            FilterResults results = new FilterResults ();
            results.values=filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            postDataList.clear ();
            postDataList.addAll ( (List)results.values );
            notifyDataSetChanged ();

        }
    };



    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.article_adapter_tv_title)
        TextView articleAdapterTvTitle;
        @BindView(R.id.article_adapter_iv_add_and_remove)
        ImageView articleAdapterIvAddAndRemove;
        @BindView(R.id.article_adapter_iv_post_image)
        ImageView articleAdapterIvPostImage;

        View view;

        public ViewHolder(@NonNull View itemView) {
            super ( itemView );

            view = itemView;

            ButterKnife.bind ( this, view );

        }
    }


}
