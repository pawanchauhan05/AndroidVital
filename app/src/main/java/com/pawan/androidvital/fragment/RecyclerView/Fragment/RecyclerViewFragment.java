package com.pawan.androidvital.fragment.RecyclerView.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pawan.androidvital.R;
import com.pawan.androidvital.fragment.RecyclerView.Adapter.InfoAdapter;
import com.pawan.androidvital.fragment.RecyclerView.Model.Info;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewFragment extends Fragment {

    private List<Info> infoList = new ArrayList<>();
    private RecyclerView recyclerView;
    private InfoAdapter mAdapter;

    public RecyclerViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mAdapter = new InfoAdapter(infoList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        infoData();

        return view;
    }

    private void infoData() {
        Info info = new Info("Divya", "Institute : Sunbeam Pune");
        infoList.add(info);

        info = new Info("Pawan", "Institute : Sunbeam Pune");
        infoList.add(info);

        info = new Info("Megha", "Institute : Sunbeam Pune");
        infoList.add(info);

        info = new Info("Pradnya", "Institute : Sunbeam Pune");
        infoList.add(info);

        mAdapter.notifyDataSetChanged();
    }

}
