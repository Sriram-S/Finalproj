package com.example.mylibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class jokesactivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokesactivity);
        textView=(TextView)findViewById(R.id.getjoke);
        Intent intent=getIntent();
        String val=intent.getStringExtra("JOKE");
        if(val!=null){
            textView.setText(val);
        }

    }
}
