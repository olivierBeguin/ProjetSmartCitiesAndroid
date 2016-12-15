package com.henallux.smartcities.view;

import android.os.Bundle;
import android.widget.EditText;

import com.henallux.smartcities.R;
import com.henallux.smartcities.model.DoService;
import com.henallux.smartcities.model.Service;

public class CommentActivity extends LayoutActivity
{
    private DoService doServiceToAddComment;
    private EditText ownerService, labelService;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        Bundle bundle = this.getIntent().getExtras();
        doServiceToAddComment = (DoService) bundle.get("doService");
        ownerService = (EditText) findViewById(R.id.ownerServiceEditText);
        ownerService.setText(doServiceToAddComment.getUserDoService().getFirstName() + " " + doServiceToAddComment.getUserDoService().getLastName());
        labelService.setText(doServiceToAddComment.getServiceDone().getLabelService());
    }


}