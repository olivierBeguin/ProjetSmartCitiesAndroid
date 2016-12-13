package com.henallux.smartcities.model;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.henallux.smartcities.R;

import java.util.ArrayList;

/**
 * Created by olivierbeguin on 6/12/16.
 */

public class DoServicesAdapter extends ArrayAdapter
{
    public DoServicesAdapter(Context context, ArrayList<DoService> doServices)
    {
        super(context, 0, doServices);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        DoService doService = (DoService) getItem(position);
        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_template, parent, false);
        }

        TextView doServiceLabel = (TextView) convertView.findViewById(R.id.label_list);
        TextView doServiceDescription = (TextView) convertView.findViewById(R.id.description_list);
        doServiceLabel.setText(doService.getServiceDone().getLabelService());
        if(doService.getComment() == null)
            doServiceDescription.setText("Il n'y a pas de commentaire");
        else
            doServiceDescription.setText(doService.getComment());
        return convertView;
    }
}
