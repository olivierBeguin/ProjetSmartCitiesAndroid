package com.henallux.smartcities.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.henallux.smartcities.R;

import java.util.ArrayList;


public class DoServicesAdapter extends ArrayAdapter
{
    public DoServicesAdapter(Context context, ArrayList<DoService> doServices)
    {
        super(context, 0, doServices);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent)
    {
        DoService doService = (DoService) getItem(position);
        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_template_comment, parent, false);
        }

        TextView doServiceLabel = (TextView) convertView.findViewById(R.id.label_list_comment);
        TextView doServiceDescription = (TextView) convertView.findViewById(R.id.description_list_comment);
        doServiceLabel.setText(doService.getServiceDone().getLabelService());
        if(doService.getComment() == null)
            doServiceDescription.setText(R.string.no_comment);
        else
            doServiceDescription.setText(doService.getComment());
        TextView ratingText = (TextView) convertView.findViewById(R.id.rating);

        ratingText.setText(String.format("%s/5", doService.getRating()));
        /*RatingBar ratingBar = (RatingBar) convertView.findViewById(R.id.rating_list_comment);
        ratingBar.setRating(doService.getRating().floatValue());
        ratingBar.getProgressDrawable().setColorFilter(Color.parseColor("#740024"), PorterDuff.Mode.SRC_ATOP);*/
        return convertView;
    }
}
