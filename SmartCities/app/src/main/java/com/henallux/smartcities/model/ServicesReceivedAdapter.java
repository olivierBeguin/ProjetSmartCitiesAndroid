package com.henallux.smartcities.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.henallux.smartcities.R;

import java.util.ArrayList;

/**
 * Created by olivierbeguin on 15/12/16.
 */

public class ServicesReceivedAdapter extends ArrayAdapter
{
    private int positionChecked;
    private CheckBox checkBox;
    public ServicesReceivedAdapter(Context context, ArrayList<DoService> doServices)
    {
        super(context, 0, doServices);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        DoService doService = (DoService) getItem(position);
        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_template, parent, false);
        }

        TextView serviceLabel = (TextView) convertView.findViewById(R.id.label_list);
        TextView nameUser = (TextView) convertView.findViewById(R.id.description_list);
        serviceLabel.setText(doService.getServiceDone().getLabelService());
        nameUser.setText(doService.getUserDoService().getFirstName()+ " " + doService.getUserDoService().getLastName());
        checkBox = (CheckBox) convertView.findViewById(R.id.checkBox_list);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                positionChecked = position;
            }
        });
        return convertView;
    }

    public int getPositionChecked() {
        return positionChecked;
    }
}

