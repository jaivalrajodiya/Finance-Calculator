package com.all.smart.financecalculator.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.all.smart.financecalculator.CommonMethond;
import com.all.smart.financecalculator.R;

public class Intro_Screen extends AppCompatActivity {

    ImageView layout_letstart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_screen);

        CommonMethond.statusbarcolor(Intro_Screen.this);

        layout_letstart = findViewById(R.id.layout_letstart);

        layout_letstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intro_Screen.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}