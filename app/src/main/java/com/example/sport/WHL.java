package com.example.sport;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class WHL extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.whl);
        setContentView(R.layout.whl);
        ImageButton khl = (ImageButton)  findViewById(R.id.khl_btn_w);
        khl.setOnClickListener(this::onClick);
        ImageButton mhl = (ImageButton)  findViewById(R.id.mhl_btn_w);
        mhl.setOnClickListener(this::onClick);
    }
    public void onClick(View v) {

            if( v.getId()== R.id.khl_btn_w) {
                Intent intent1 = new Intent(WHL.this, KHL.class);
                startActivity(intent1);
            }
            if(v.getId()== R.id.mhl_btn_w) {
                Intent intent2 = new Intent(WHL.this, MHL.class);
                startActivity(intent2);
            }
        }
    }

