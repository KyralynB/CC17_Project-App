package com.example.mantradashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class BottomNavigation extends AppCompatActivity {

    //Varriables
    ImageView callDashbaord, callOthers, callQr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        //Hooks
        callDashbaord = findViewById(R.id.goto_dashboard);
        callOthers = findViewById(R.id.goto_others);
        callQr = findViewById(R.id.goto_qr);

        callDashbaord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BottomNavigation.this, Login.class);
                startActivity(intent);
            }
        });
    }
}