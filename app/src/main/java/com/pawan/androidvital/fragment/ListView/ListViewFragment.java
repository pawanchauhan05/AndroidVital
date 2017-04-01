package com.pawan.androidvital.fragment.ListView;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.pawan.androidvital.R;
import com.pawan.androidvital.app.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListViewFragment extends Fragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

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
        listview.setOnItemClickListener(this);
        listview.setOnItemLongClickListener(this);
        registerForContextMenu(listview);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        Utils.generateToast(getContext(), "item click", true);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("Edit company name");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getTitle().equals("Edit company name")) {

        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        Utils.generateToast(getContext(), "item click long press", true);
        return false;
    }


    class CustomList extends BaseAdapter {
        private Context c;

        String [] names= {"Company","Company1","Company2","Company3","Company4","Company5","Company6","accenture"};

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
