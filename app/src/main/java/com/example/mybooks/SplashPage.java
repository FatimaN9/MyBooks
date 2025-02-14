package com.example.mybooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashPage extends AppCompatActivity {


    private static final int SPLASH_DURATION=5000; //5seconds
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page2);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =new Intent(SplashPage.this,LoginPage.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_DURATION);

    }
}