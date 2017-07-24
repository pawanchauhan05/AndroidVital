package com.pawan.androidvital.fragment.ChromeCustomTab;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pawan.androidvital.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChromeCustomTabFragment extends Fragment {
    private CustomTabsClient mCustomTabsClient;
    private CustomTabsSession mCustomTabsSession;
    private CustomTabsServiceConnection mCustomTabsServiceConnection;
    private CustomTabsIntent mCustomTabsIntent;


    public ChromeCustomTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chrome_custom_tab, container, false);
        showChromeCustomTab(getActivity(), "https://github.com/pawanchauhan05/AndroidVital");
        return view;
    }

    public void showChromeCustomTab(Activity activity, String token) {

        final String CUSTOM_TAB_PACKAGE_NAME = "com.android.chrome";

        ApplicationInfo ai = null;
        boolean appStatus = false;
        boolean isInstalled = isAppInstalled(activity, CUSTOM_TAB_PACKAGE_NAME);
        try {
            ai = activity.getPackageManager().getApplicationInfo(CUSTOM_TAB_PACKAGE_NAME, 0);
            appStatus = ai.enabled;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        if (!isInstalled) {
            // TODO not installed, handle on yourself
        } else if (isInstalled && !appStatus) {
            // TODO installed but deactivated, handle on yourself
        } else {
            mCustomTabsServiceConnection = new CustomTabsServiceConnection() {

                @Override
                public void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
                    mCustomTabsClient = customTabsClient;
                    mCustomTabsClient.warmup(0L);
                    mCustomTabsSession = mCustomTabsClient.newSession(null);
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    mCustomTabsClient = null;
                }
            };

            CustomTabsClient.bindCustomTabsService(activity, CUSTOM_TAB_PACKAGE_NAME, mCustomTabsServiceConnection);

            mCustomTabsIntent = new CustomTabsIntent.Builder(mCustomTabsSession)
                    .setShowTitle(true)
                    .setToolbarColor(Color.parseColor("#009087"))
                    .enableUrlBarHiding()
                    .build();
            mCustomTabsIntent.intent.setData(Uri.parse(token));
            mCustomTabsIntent.intent.setPackage(CUSTOM_TAB_PACKAGE_NAME);
            startActivity(mCustomTabsIntent.intent);
        }

    }

    private boolean isAppInstalled(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        boolean installed;
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
        return installed;
    }

}
