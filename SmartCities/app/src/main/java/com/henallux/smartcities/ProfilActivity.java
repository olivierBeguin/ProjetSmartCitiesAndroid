package com.henallux.smartcities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfilActivity extends LayoutActivity
{

    private Button myCommentButton, modifyButton, myActivitiesButton;

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
        modifyButton = (Button) findViewById(R.id.modify_button);
        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToModifProfil();
            }
        });
        myActivitiesButton = (Button) findViewById(R.id.myActivitiesButton);
        myActivitiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMyActivityActivity();
            }
        });
    }

    private void goToComment()
    {
        Intent intent = new Intent(ProfilActivity.this, SeeCommentActivity.class);
        startActivity(intent);
    }

    private void goToModifProfil()
    {
        Intent intent = new Intent(ProfilActivity.this, ModifProfilActivity.class);
        startActivity(intent);
    }

    private void goToMyActivityActivity()
    {
        Intent intent = new Intent(ProfilActivity.this, MyActivitiesActivity.class);
        startActivity(intent);
    }
}
