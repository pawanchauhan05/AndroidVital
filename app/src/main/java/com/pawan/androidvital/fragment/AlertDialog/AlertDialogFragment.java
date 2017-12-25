package com.pawan.androidvital.fragment.AlertDialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.pawan.androidvital.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlertDialogFragment extends Fragment implements View.OnClickListener {

    private Button showAlert, twoBtnAlert, threeBtnAlert, customButtonAlert;
    public AlertDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_alert_dialog, container, false);

        showAlert = (Button)view.findViewById(R.id.show_alert);
        twoBtnAlert = (Button)view.findViewById(R.id.show_2_btn_alert);
        threeBtnAlert = (Button)view.findViewById(R.id.show_3_btn_alert);
        customButtonAlert = (Button)view.findViewById(R.id.show_4_btn_alert);

        showAlert.setOnClickListener(this);
        twoBtnAlert.setOnClickListener(this);
        threeBtnAlert.setOnClickListener(this);
        customButtonAlert.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.show_alert:
                SimpleShowAlert();
                break;

            case R.id.show_2_btn_alert:
                TwoButtonAlert();
                break;

            case R.id.show_3_btn_alert:
                ThreeButtonAlert();
                break;

            case R.id.show_4_btn_alert:
                customAlert();
                break;
        }


    }

    public void SimpleShowAlert()
    {
       AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
         builder.setTitle("Simple Alert Box")
                .setMessage(Html.fromHtml("<font color='#757575'>Click ok button to cancel</font>"))
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
                .setMessage(Html.fromHtml("<font color='#757575'>Click ok button to cancel</font>"))
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
                .setMessage(Html.fromHtml("<font color='#757575'>Click ok button to cancel</font>"))
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

    private void customAlert() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.custom_alert_dialog_layout, null);

        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        view.findViewById(R.id.buttonSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }
}
