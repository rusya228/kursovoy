package com.example.sport;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
public class KHL extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.khl);
        setContentView(R.layout.khl);
        ImageButton whl = (ImageButton)  findViewById(R.id.whl_btn_k);
        whl.setOnClickListener(this::onClick);
        ImageButton mhl = (ImageButton)  findViewById(R.id.mhl_btn_k);
        mhl.setOnClickListener(this::onClick);
    }
    public void onClick(View v) {

            if(v.getId()==R.id.whl_btn_k){
                Intent intent1 = new Intent(KHL.this, WHL.class);
                startActivity(intent1);}

            if(v.getId()== R.id.mhl_btn_k){
                Intent intent2 = new Intent(KHL.this, MHL.class);
                startActivity(intent2);}

        }
    }

