package com.pawan.androidvital.fragment.Toast.androidAnnotations;


import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pawan.androidvital.R;
import com.pawan.androidvital.app.AndroidVitalApplication;
import com.pawan.androidvital.app.Utils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.SystemService;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_android_annotations)
public class AndroidAnnotationsFragment extends Fragment {

    /**
     * @App is one more annotation provided by android annotations.
     * it is used for create custom application instance.
     * for eg.
     * @App
     * AndroidVitalApplication androidVitalApplication;
     *
     * similar to
     * AndroidVitalApplication androidVitalApplication = (AndroidVitalApplication) getActivity().getApplication();
     */
    @App
    AndroidVitalApplication androidVitalApplication;

    /**
     * @SystemService is one more annotation provided by android annotations.
     * it is used for create system services instance.
     * for eg.
     * @SystemService
     * ConnectivityManager connectivityManager;
     *
     * similar to
     * ConnectivityManager connectivityManager = getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
     *
     */
    @SystemService
    ConnectivityManager connectivityManager;

    /**
     * @ViewByID annotation is provided by android annotations.
     * it is used for create reference of view or other.
     * eg.
     * @ViewById(R.id.text_view)
     * TextView textView;
     *
     * similar to textView = (TextView) view.findViewById(R.id.textView);
     */
    @ViewById(R.id.text_view)
    TextView textView;

    /*
    while using android annotations for fragment
    1) add @EFragment(and layout of fragment eg. @EFragment(R.layout.fragment_android_annotations)

    public AndroidAnnotationsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_android_annotations, container, false);
    }
    */

    /**
     * @AfterViews method is used by android Annotations
     * it is used while you want to do any task while fragment is created.
     */
    @AfterViews
    void init() {
    }


    /**
     * @Click() is one more annotation provided by android annotations.
     * it is used for add click functionality to any instance of view.
     * for eg
     * @Click(R.id.text_view)
     * void Action() {}
     * similar to
     *
     * textView.setOnClickListener(new View.OnClickListener() {
     *                      @Override
     *                      public void onClick(View view) {
     *
     *                      }
     * });
     *
     */
    @Click(R.id.text_view)
    void changeText() {
        Utils.generateToast(getContext(), "Clicked!", true);
    }

}
