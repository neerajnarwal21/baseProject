package com.example.sample.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.sample.R;
import com.example.sample.activity.BaseActivity;
import com.example.sample.data.DemoSerializedData;

import java.util.ArrayList;


public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.MyViewHolder> {

    BaseActivity baseActivity;
    private ArrayList<DemoSerializedData> demoSerializedDatas;


    public DemoAdapter(BaseActivity baseActivity, ArrayList<DemoSerializedData> demoSerializedDatas) {
        this.demoSerializedDatas = demoSerializedDatas;
        this.baseActivity = baseActivity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fg_home /* Change with you adapter layout*/, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        DemoSerializedData demoSerializedData = demoSerializedDatas.get(position);
//        holder.favIV.setImageResource(demoSerializedData.isfav.equals("0") ? R.mipmap.ic_fav_grey : R.mipmap.ic_fav_selected);
    }


    @Override
    public int getItemCount() {
        return demoSerializedDatas.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView logoIV, favIV;

        public MyViewHolder(View view) {
            super(view);
//            logoIV = (ImageView) view.findViewById(R.id.logoIV);
//            favIV = (ImageView) view.findViewById(R.id.favIV);
        }
    }
}