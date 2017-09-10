package com.punajabapps.volleylistview;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by WaheedSabir on 10/09/2017.
 */


//Todo base adapter class create 4 override method

public class CustomBaseAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<GetSetData> getSetDataslist;


    //Todo create  constructor here
    public CustomBaseAdapter(Activity activity, List<GetSetData> getSetDataslist) {
        this.activity = activity;
        this.getSetDataslist = getSetDataslist;
    }

    @Override
    public int getCount() {

//Todo  get list size here
        return getSetDataslist.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    //Todo find xml view and set value here
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null)
            view = inflater.inflate(R.layout.custom_list, null);

        GetSetData m = getSetDataslist.get(i);

        ImageView imageView = view.findViewById(R.id.thumbnail);
        Picasso.with(activity).load(m.getImg()).into(imageView);
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView address = (TextView) view.findViewById(R.id.worth);
        TextView phone = (TextView) view.findViewById(R.id.source);
        TextView date = (TextView) view.findViewById(R.id.inYear);

        // getting billionaires data for the row


        // thumbnail image


        // name
        name.setText(m.getName());
        address.setText(m.getAddress());
        phone.setText(m.getPhone());


        // release year
        //  year.setText(" Contact ☏ # " + String.valueOf(m.getYear()));

        return view;

    }
}
