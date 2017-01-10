package com.pawan.androidvital.fragment.Toast;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.pawan.androidvital.R;
import com.pawan.androidvital.app.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ToastFragment extends Fragment implements View.OnClickListener{

    private Button buttonShortToast, buttonLongToast;
    public ToastFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_toast, container, false);
        buttonShortToast = (Button) getActivity().findViewById(R.id.button_short_toast);
        buttonLongToast = (Button) getActivity().findViewById(R.id.button_long_toast);
        buttonShortToast.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button_short_toast) {
            Utils.generateToast(getContext(), "Short Toast", true);
        } else if(view.getId() == R.id.button_long_toast) {
            Utils.generateToast(getContext(), "Long Toast", true);
        }
    }
}
