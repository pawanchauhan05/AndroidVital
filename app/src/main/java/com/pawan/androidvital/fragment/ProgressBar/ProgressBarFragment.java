package com.pawan.androidvital.fragment.ProgressBar;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.pawan.androidvital.R;
import com.pawan.androidvital.app.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProgressBarFragment extends Fragment implements View.OnClickListener {

    private Button usingjava, usingxml, usingcustom;
    private ProgressBar progressBar;
    public ProgressBarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_progress_bar, container, false);

        usingjava = (Button)view.findViewById(R.id.usingjava);
        usingxml = (Button)view.findViewById(R.id.usingxml);
        usingcustom = (Button)view.findViewById(R.id.usingcustom);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        usingjava.setOnClickListener(this);
        usingcustom.setOnClickListener(this);
        usingxml.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.usingjava) {

            ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage(Html.fromHtml("<font color='#757575'>Loading..</font>"));
            Utils.showProgressBar(progressDialog);
            progressBar.setVisibility(View.INVISIBLE);

        }else if (view.getId() == R.id.usingxml) {
            progressBar.setVisibility(View.VISIBLE);
        }else if (view.getId() == R.id.usingcustom) {
            ProgressDialog progressDialog = new ProgressDialog(getActivity());
            Utils.showCustomProgressBar(progressDialog);
            progressBar.setVisibility(View.INVISIBLE);
        }

    }
}
