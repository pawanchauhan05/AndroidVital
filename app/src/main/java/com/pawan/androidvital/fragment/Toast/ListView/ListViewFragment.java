package com.pawan.androidvital.fragment.Toast.ListView;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.pawan.androidvital.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListViewFragment extends Fragment {

    ListView listview;

    public ListViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_view, container, false);
        listview=(ListView)view.findViewById(R.id.list);
        CustomList adapter=new CustomList(getActivity());
        listview.setAdapter(adapter);
        listview.setEnabled(true);

        return view;
    }

    class CustomList extends BaseAdapter {
        private Context c;
        String [] names= {"Company1","Company2","Company3","Company4","Company5","Company6","Company7","Company8"};

        public CustomList(Context ctx)
        {
            this.c=ctx;
        }

        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public Object getItem(int pos) {
            return names[pos];
        }

        @Override
        public long getItemId(int pos) {
            return pos;
        }

        @Override
        public View getView(final int pos, View convertView, ViewGroup parent) {
            if(convertView==null)
            {
                LayoutInflater inflater=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView=inflater.inflate(R.layout.list_item,null);
            }
            TextView nameTxt= (TextView) convertView.findViewById(R.id.nametext);

            nameTxt.setText(names[pos]);
            return convertView;
        }

    }

}
