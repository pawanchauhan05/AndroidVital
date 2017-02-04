package com.pawan.androidvital.fragment.ScanWifi;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.pawan.androidvital.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.Context.WIFI_SERVICE;


public class ScanWifiFragment extends Fragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    WifiManager mainWifi;
    WifiReceiver receiverWifi;
    private ListView listView;
    private ArrayList<ScanResult> connections = new ArrayList<>();
    CustomList adapter;

    StringBuilder sb = new StringBuilder();

    private final Handler handler = new Handler();


    public ScanWifiFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scan_wifi, container, false);
        mainWifi = (WifiManager) getActivity().getSystemService(WIFI_SERVICE);
        listView = (ListView) view.findViewById(R.id.list_view);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        receiverWifi = new WifiReceiver();
        getActivity().registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        if(mainWifi.isWifiEnabled()==false) {
            mainWifi.setWifiEnabled(true);
        }
        doInback();
        return view;
    }

    public void doInback() {
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                mainWifi = (WifiManager) getActivity().getSystemService(WIFI_SERVICE);
            if(receiverWifi == null)
                receiverWifi = new WifiReceiver();
                getActivity().registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
                mainWifi.startScan();
                doInback();
            }
        }, 1000);

    }

    @Override
    public void onResume() {
        getActivity().registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onResume();
    }

    @Override
    public void onPause() {
        getActivity().unregisterReceiver(receiverWifi);
        super.onPause();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.e("Conn", connections.get(i).SSID);
        WifiConfiguration wifiConfig = new WifiConfiguration();
        wifiConfig.SSID = String.format("\"%s\"", connections.get(i).SSID);
        wifiConfig.preSharedKey = String.format("\"%s\"", "huskytomato457");

        WifiManager wifiManager = (WifiManager)getActivity().getSystemService(WIFI_SERVICE);
        int netId = wifiManager.addNetwork(wifiConfig);
        wifiManager.disconnect();
        wifiManager.enableNetwork(netId, true);
        wifiManager.reconnect();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        return false;
    }

    class WifiReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            HashMap<String, ScanResult> resultHashMap = new HashMap<>();
            sb = new StringBuilder();
            List<ScanResult> wifiList = mainWifi.getScanResults();
            for(int i = 0; i < wifiList.size(); i++) {
                resultHashMap.put(wifiList.get(i).SSID, wifiList.get(i));
            }
            connections = new ArrayList<ScanResult>(resultHashMap.values());
            adapter = new CustomList(getActivity());
            listView.setAdapter(adapter);
        }
    }

    class CustomList extends BaseAdapter {
        private Context c;

        public CustomList(Context ctx)
        {
            this.c=ctx;
        }

        @Override
        public int getCount() {
            return connections.size();
        }

        @Override
        public Object getItem(int pos) {
            return connections.get(pos);
        }

        @Override
        public long getItemId(int pos) {
            return pos;
        }

        @Override
        public View getView(final int pos, View convertView, ViewGroup parent) {
            if(convertView == null) {
                LayoutInflater inflater=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView=inflater.inflate(R.layout.list_item,null);
            }
            TextView nameTxt= (TextView) convertView.findViewById(R.id.nametext);
            nameTxt.setText(connections.get(pos).SSID);
            return convertView;
        }

    }

}
