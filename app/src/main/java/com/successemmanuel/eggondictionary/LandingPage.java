package com.successemmanuel.eggondictionary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LandingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        Thread dThread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(1000);
                    Intent d = new Intent(getApplicationContext(),DictionaryMain.class);
                    startActivity(d);
                    finish();

                }catch (InterruptedException e){
                    e.getStackTrace();
                }
            }
        };
        dThread.start();
    }
}