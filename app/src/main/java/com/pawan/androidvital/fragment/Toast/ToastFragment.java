package com.pawan.androidvital.fragment.Toast;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.pawan.androidvital.R;
import com.pawan.androidvital.app.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ToastFragment extends Fragment implements View.OnClickListener{

    private Button buttonShortToast, buttonLongToast, buttonCustomToast;
    private View toastLayout;
    public ToastFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_toast, container, false);
        buttonShortToast = (Button) view.findViewById(R.id.button_short_toast);
        buttonLongToast = (Button) view.findViewById(R.id.button_long_toast);
        buttonCustomToast = (Button) view.findViewById(R.id.button_custom_toast);
        toastLayout = inflater.inflate(R.layout.custom_toast_layout, (ViewGroup) view.findViewById(R.id.custom_toast_layout));
        buttonShortToast.setOnClickListener(this);
        buttonLongToast.setOnClickListener(this);
        buttonCustomToast.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button_short_toast) {
            Utils.generateToast(getContext(), "Short Toast", true);
        } else if(view.getId() == R.id.button_long_toast) {
            Utils.generateToast(getContext(), "Long Toast", true);
        } else if(view.getId() == R.id.button_custom_toast) {
            Toast toast = new Toast(getContext());
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(toastLayout);
            toast.show();
        }
    }
}
