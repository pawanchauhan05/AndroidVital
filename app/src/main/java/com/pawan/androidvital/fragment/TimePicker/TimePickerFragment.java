package com.pawan.androidvital.fragment.TimePicker;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.pawan.androidvital.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimePickerFragment extends Fragment {


    TimePicker timePicker;
    TextView displayTime;
    Button changeTime;

    public TimePickerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_time_picker, container, false);
        timePicker = (TimePicker) view.findViewById(R.id.timePicker);
        displayTime = (TextView) view.findViewById(R.id.textView);
        changeTime = (Button) view.findViewById(R.id.bchange_time);

        timePicker.setIs24HourView(true);
        displayTime.setText(currentTime());
        changeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayTime.setText(currentTime());
            }
        });
        return view;
    }

    public String currentTime() {
        String mcurrentTime = "Time: " + timePicker.getCurrentHour() + ":" + timePicker.getCurrentMinute();
        return mcurrentTime;
    }
}
