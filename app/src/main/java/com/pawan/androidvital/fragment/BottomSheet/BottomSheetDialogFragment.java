package com.pawan.androidvital.fragment.BottomSheet;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pawan.androidvital.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BottomSheetDialogFragment extends android.support.design.widget.BottomSheetDialogFragment {

    String mString;

    static BottomSheetDialogFragment newInstance(String string) {
        BottomSheetDialogFragment f = new BottomSheetDialogFragment();
        Bundle args = new Bundle();
        args.putString("string", string);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mString = getArguments().getString("string");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bottom_sheet_dialog, container, false);
        TextView tv = (TextView) v.findViewById(R.id.text);
        return v;
    }
}

