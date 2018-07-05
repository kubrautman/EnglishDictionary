package com.example.kub.englishlearn;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
        ImageView bSimple;
        ImageView bMedium;
        ImageView bHard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bSimple=(ImageView)findViewById(R.id.imageVSimple);
        bMedium=(ImageView)findViewById(R.id.imageVMedium);
        bHard=(ImageView)findViewById(R.id.imageVHard);
        bSimple.setOnClickListener(this);
        bMedium.setOnClickListener(this);
        bHard.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.imageVSimple:
                Intent i=new Intent(Main2Activity.this,MainSimpleActivity.class);
                startActivity(i);

                break;
            case  R.id.imageVMedium:
                Intent i1=new Intent(Main2Activity.this,MainMediumActivity.class);
                startActivity(i1);

                break;

            case R.id.imageVHard:
                Intent i2=new Intent(Main2Activity.this,MainHardActivity.class);
                startActivity(i2);

                break;
        }
}}
