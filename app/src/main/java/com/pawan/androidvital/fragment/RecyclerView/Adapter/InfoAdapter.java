package com.pawan.androidvital.fragment.RecyclerView.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pawan.androidvital.R;
import com.pawan.androidvital.fragment.RecyclerView.Model.Info;

import java.util.List;


public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.MyViewHolder> {

    private List<Info> infoList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, institute;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            institute = (TextView) view.findViewById(R.id.institute);
        }
    }


    public InfoAdapter(List<Info> infoList) {
        this.infoList = infoList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_items, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Info info = infoList.get(position);
        holder.name.setText(info.getName());
        holder.institute.setText(info.getInstitute());
    }

    @Override
    public int getItemCount() {
        return infoList.size();
    }
}