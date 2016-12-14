package com.henallux.smartcities.model;

import android.content.Context;
import android.util.SparseBooleanArray;
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
 * Created by olivierbeguin on 6/12/16.
 */

public class ServicesAdapter extends ArrayAdapter
{
    private int positionChecked;
    private CheckBox checkBox;
    public ServicesAdapter(Context context, ArrayList<Service> services)
    {
        super(context, 0, services);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        Service service = (Service) getItem(position);
        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_template, parent, false);
        }

        TextView serviceLabel = (TextView) convertView.findViewById(R.id.label_list);
        TextView serviceDescription = (TextView) convertView.findViewById(R.id.description_list);
        serviceLabel.setText(service.getLabelService());
        serviceDescription.setText(service.getDescriptionService());
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
