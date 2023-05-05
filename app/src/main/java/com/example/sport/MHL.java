package com.example.sport;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MHL extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mhl);
        setContentView(R.layout.mhl);
        ImageButton khl = (ImageButton)  findViewById(R.id.khl_btn_m);
        khl.setOnClickListener(this::onClick);
        ImageButton whl = (ImageButton)  findViewById(R.id.whl_btn_m);
        whl.setOnClickListener(this::onClick);
    }
    public void onClick(View v) {

            if (v.getId()== R.id.khl_btn_m){
                Intent intent1 = new Intent(MHL.this, KHL.class);
                startActivity(intent1);}

            if( v.getId()== R.id.whl_btn_m){
                Intent intent2 = new Intent(MHL.this, WHL.class);
                startActivity(intent2);}

        }
    }
