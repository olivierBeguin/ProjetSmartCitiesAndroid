package com.henallux.smartcities.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import com.henallux.smartcities.DAO.DoServiceDAO;
import com.henallux.smartcities.R;
import com.henallux.smartcities.model.DoService;
import com.henallux.smartcities.model.UserConnected;


public class CommentActivity extends LayoutActivity {
    private DoService doServiceToAddComment;
    private UserConnected userConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        doServiceToAddComment = (DoService) getIntent().getSerializableExtra("doService");
        EditText ownerService = (EditText) findViewById(R.id.ownerServiceEditText);
        EditText labelService = (EditText) findViewById(R.id.serviceEditText);
        ownerService.setText(doServiceToAddComment.getUserDoService().getFirstName() + " " + doServiceToAddComment.getUserDoService().getLastName());
        labelService.setText(doServiceToAddComment.getServiceDone().getLabelService());
        Button btn_add_comment = (Button) findViewById(R.id.validateButtonComment);
        btn_add_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionBtnValidate();
            }
        });
        userConnected = new UserConnected();
    }

    private void actionBtnValidate() {
        String token = userConnected.getToken(CommentActivity.this);
        doServiceToAddComment.setComment(String.valueOf(((EditText) findViewById(R.id.commentText)).getText()));
        doServiceToAddComment.setRating((double) ((RatingBar) findViewById(R.id.ratingBar)).getRating());
        new AddComment().execute(token);
    }

    private class AddComment extends AsyncTask<String, Void, Boolean> {
        private Exception exceptionToBeThrow;

        @Override
        protected Boolean doInBackground(String... params) {
            try {
                DoServiceDAO doServiceDAO = new DoServiceDAO();
                doServiceDAO.putDoService(params[0], doServiceToAddComment);
                return true;
            } catch (Exception e) {
                exceptionToBeThrow = e;
                return false;
            }

        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (!success && exceptionToBeThrow != null)
                Toast.makeText(CommentActivity.this, exceptionToBeThrow.getMessage(), Toast.LENGTH_LONG).show();
            else {
                Intent intent = new Intent( CommentActivity.this, MyActivitiesActivity.class);
                startActivity(intent);
            }
        }
    }
}
