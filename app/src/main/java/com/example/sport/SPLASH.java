package com.example.sport;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class SPLASH extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SPLASH.this,KHL.class);
                startActivity(i);
                finish();
            }
        },2000);
    }

    private void onClick(View view) {
        Intent intent = new Intent(SPLASH.this, KHL.class);
        startActivity(intent);
    }
}
