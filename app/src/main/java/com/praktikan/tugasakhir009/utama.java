package com.praktikan.tugasakhir009;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class utama extends AppCompatActivity {

    Button logot;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);

        sp = getSharedPreferences("Data", MODE_PRIVATE);
        editor = sp.edit();

        logot = (Button) findViewById(R.id.logot);

        logot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keluar();
            }
        });

    }

    private void keluar() {
        editor.clear();
        editor.apply();
        startActivity(new Intent(utama.this,MainActivity.class));
        finish();
    }
}