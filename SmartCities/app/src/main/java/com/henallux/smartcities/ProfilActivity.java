package com.henallux.smartcities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfilActivity extends AppCompatActivity {

    private Button myActivityButton, myCommentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        myActivityButton = (Button) findViewById(R.id.myActivitiesButton);
        myActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity();
            }
        });
        myCommentButton = (Button) findViewById(R.id.myCommentsButton);
        myCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToComment();
            }
        });
    }

    private void goToActivity()
    {
        Intent intent = new Intent(ProfilActivity.this, ActivityActivity.class);
        startActivity(intent);
    }

    private void goToComment()
    {
        Intent intent = new Intent(ProfilActivity.this, CommentActivity.class);
        startActivity(intent);
    }
}
