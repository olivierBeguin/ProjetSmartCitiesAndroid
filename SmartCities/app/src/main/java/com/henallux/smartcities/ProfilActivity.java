package com.henallux.smartcities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class ProfilActivity extends LayoutActivity
{

    private Button myCommentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        myCommentButton = (Button) findViewById(R.id.myCommentsButton);
        myCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToComment();
            }
        });
    }

    private void goToComment()
    {
        Intent intent = new Intent(ProfilActivity.this, CommentActivity.class);
        startActivity(intent);
    }
}
