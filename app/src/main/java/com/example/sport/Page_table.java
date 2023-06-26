package com.example.sport;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Page_table extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_table);


    }
    public void toMatchPage(View v){
        startActivity(new Intent(this, MatchPage.class));
        overridePendingTransition(0,0);
    }
    public void toSostav(View v){
        startActivity(new Intent(this, Page_sostav.class));
        overridePendingTransition(0,0);
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, KHL.class));
    }
}