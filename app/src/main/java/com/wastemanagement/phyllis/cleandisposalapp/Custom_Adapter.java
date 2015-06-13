package com.wastemanagement.phyllis.cleandisposalapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Phyllis on 4/29/2015.
 * adapter for the main menu items
 * setting thr different items onto the list view
 */
public class Custom_Adapter extends ArrayAdapter<String> {

    /* declare the adapter variables */
    private Activity context;
    private final String[] menuItems;
    private final String[] option_description;

    public Custom_Adapter(Activity context, String[] menuItems, String[] option_description) {
        super(context, R.layout.activity_main_menu, menuItems );
        this.context = context;
        this.menuItems = menuItems;
        this.option_description = option_description;
    }


    /* getting the layout and puting list items on those layouts*/
    public View getView(int position, View view, ViewGroup menu){
        LayoutInflater inflater=context.getLayoutInflater();
/*Defining our View*/
        View rowView=inflater.inflate(R.layout.menulist, null, true);
/*Get elements on the View*/
        TextView textTitle=(TextView) rowView.findViewById(R.id.itemName);
        TextView textDes=(TextView) rowView.findViewById(R.id.itemDescription);
/*Set the variables in the list to the view*/
        textTitle.setText(menuItems[position]);
        textDes.setText(option_description[position]);
        return rowView;
    };
}
