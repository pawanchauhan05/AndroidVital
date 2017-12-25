package com.pawan.androidvital.fragment.SnackBar;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.pawan.androidvital.R;
import com.pawan.androidvital.app.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class SnackBarFragment extends Fragment implements View.OnClickListener {

    private Button buttonShortSnackBar, buttonLongSnackBar, buttonCustomSnackBar;
    private View view;

    public SnackBarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_snack_bar, container, false);
        buttonShortSnackBar = (Button) view.findViewById(R.id.button_short_snack_bar);
        buttonLongSnackBar = (Button) view.findViewById(R.id.button_long_snack_bar);
        buttonCustomSnackBar = (Button) view.findViewById(R.id.button_custom_snack_bar);
        this.view = inflater.inflate(R.layout.custom_snackbar_layout, null);
        buttonShortSnackBar.setOnClickListener(this);
        buttonLongSnackBar.setOnClickListener(this);
        buttonCustomSnackBar.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_short_snack_bar:
                Utils.generateSnackBar(getView(), "Short Snack Bar", true);
                break;

            case R.id.button_long_snack_bar:
                Utils.generateSnackBar(getView(), "Long Snack Bar", false);
                break;

            case R.id.button_custom_snack_bar:
                //customSnackBar();
                break;
        }
    }

    private void customSnackBar() {
        Snackbar snackbar = Snackbar.make(getView(), "", Snackbar.LENGTH_LONG);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        TextView textView = (TextView) layout.findViewById(android.support.design.R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);
        //ImageView imageView = (ImageView) snackView.findViewById(R.id.image);
        //imageView.setImageBitmap(image);
        //TextView textViewTop = (TextView) snackView.findViewById(R.id.text);
        //textViewTop.setText(text);
        //textViewTop.setTextColor(Color.WHITE);
        layout.addView(view);
        snackbar.show();
    }
}
