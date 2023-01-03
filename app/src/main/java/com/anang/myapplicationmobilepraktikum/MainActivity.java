package com.anang.myapplicationmobilepraktikum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button b_loc, b_dash, b_prof, b_about;
    DatabaseSQLite db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseSQLite(this);

        Boolean checkSession = db.checkSession("ada");
        if (checkSession == false) {
            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            finish();
        }

        b_dash = (Button) findViewById(R.id.btn_dash);
        b_prof = (Button) findViewById(R.id.btn_profile);
        b_dash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(pindah);
            }
        });


        b_prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(pindah);
            }
        });


    }
}