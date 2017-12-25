package com.pawan.androidvital.fragment.Menu;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.pawan.androidvital.R;
import com.pawan.androidvital.app.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment implements View.OnClickListener {

    ListView listview;
    Button popupButton;

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        listview = (ListView) view.findViewById(R.id.contextmenulist);
        popupButton = (Button) view.findViewById(R.id.popupmenu);

        CustomList adapter = new CustomList(getActivity());
        listview.setAdapter(adapter);
        listview.setEnabled(true);

        popupButton.setOnClickListener(this);
        registerForContextMenu(listview);

        return view;
    }

    /**
     * Context menu
     *
     * @param menu
     * @param v
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("Edit company name");
        menu.add("Remove company");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getTitle().equals("Edit company name")) {
            Utils.generateToast(getContext(), "Edit clicked", true);

        } else if (item.getTitle().equals("Remove company")) {
            Utils.generateToast(getContext(), "Remove clicked", true);
        }
        return super.onContextItemSelected(item);
    }

    /**
     * Popup menu
     *
     * @param view
     */
    @Override
    public void onClick(View view) {

        PopupMenu popupMenu = new PopupMenu(getActivity(), popupButton);
        popupMenu.getMenuInflater().inflate(R.menu.poupup_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                Utils.generateToast(getContext(), "Menu item clicked", true);

                return true;
            }
        });
        popupMenu.show();

    }

    /**
     * Custom Adapter
     */
    class CustomList extends BaseAdapter {
        private Context c;
        String[] names = {"Android", "Java", "XML"};

        public CustomList(Context ctx) {
            this.c = ctx;
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
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.list_item, null);
            }
            TextView nameTxt = (TextView) convertView.findViewById(R.id.nametext);

            nameTxt.setText(names[pos]);
            return convertView;
        }

    }

}
