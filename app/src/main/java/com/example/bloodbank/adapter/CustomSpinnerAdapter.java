package com.example.bloodbank.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bloodbank.data.model.generalResponse.GeneralResponseData;
import com.example.bloodbank.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomSpinnerAdapter extends ArrayAdapter<String> {

    private final List<GeneralResponseData> dataItemList;
    LayoutInflater flater;
    @BindView(R.id.custom_spinner_adapter_tv_name)
    TextView customSpinnerAdapterTvName;
    public int selectId = 0;

    public CustomSpinnerAdapter(Activity context, int resouceId, int textviewId,
                                List<GeneralResponseData> dataItemList, List<String> names) {
        super(context, resouceId, textviewId, names);
        this.dataItemList = dataItemList;
        flater = context.getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        GeneralResponseData dataItem = dataItemList.get(position);

        View view = flater.inflate(R.layout.item_custom_spinner, null, true);
        ButterKnife.bind(this, view);
        customSpinnerAdapterTvName.setText(dataItem.getName());
        selectId = dataItem.getId();

        return view;
    }

}
