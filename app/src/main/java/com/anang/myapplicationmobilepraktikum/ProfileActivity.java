package com.anang.myapplicationmobilepraktikum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    DatabaseSQLite db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button b_logout = (Button) findViewById(R.id.btn_logout);

        db = new DatabaseSQLite(this);

        b_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean keluarSession = db.upgradeSession("kosong", 1, "kosong");
                if (keluarSession == true) {
                    Toast.makeText(getApplicationContext(), "Berhasil Keluar", Toast.LENGTH_SHORT).show();
                    Intent loginIntent = new Intent(ProfileActivity.this, MainActivity.class);
                    startActivity(loginIntent);
                    finish();
                }
            }
        });

    }
}