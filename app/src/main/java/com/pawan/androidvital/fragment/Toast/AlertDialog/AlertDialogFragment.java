package com.pawan.androidvital.fragment.Toast.AlertDialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.pawan.androidvital.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlertDialogFragment extends Fragment implements View.OnClickListener {

Button showAlert, twoBtnAlert, threeBtnAlert;
    public AlertDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_alert_dialog, container, false);

        showAlert = (Button)view.findViewById(R.id.show_alert);
        twoBtnAlert = (Button)view.findViewById(R.id.show_2_btn_alert);
        threeBtnAlert = (Button)view.findViewById(R.id.show_3_btn_alert);

        showAlert.setOnClickListener(this);
        twoBtnAlert.setOnClickListener(this);
        threeBtnAlert.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.show_alert) {
            SimpleShowAlert();

        }else if (view.getId() == R.id.show_2_btn_alert) {
            TwoButtonAlert();

        }else if (view.getId() == R.id.show_3_btn_alert) {
            ThreeButtonAlert();

        }

    }

    public void SimpleShowAlert()
    {
       AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
         builder.setTitle("Simple Alert Box")
                .setMessage("Click ok button to cancel")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void TwoButtonAlert()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Simple Alert Box")
                .setMessage("Click ok button to cancel")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void ThreeButtonAlert()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Simple Alert Box")
                .setMessage("Click ok button to cancel")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        }).setNeutralButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
