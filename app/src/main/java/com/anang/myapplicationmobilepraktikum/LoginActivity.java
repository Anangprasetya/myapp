package com.anang.myapplicationmobilepraktikum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    DatabaseSQLite db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseSQLite(this);

        EditText username = (EditText) findViewById(R.id.f_username);
        EditText pass = (EditText) findViewById(R.id.f_password);

        Button btn_login = (Button) findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUser = username.getText().toString();
                String strPass = pass.getText().toString();

                if (strUser.equals("owner") && strPass.equals("ooad2020")) {
                    Boolean updateSessionLogin = db.upgradeSession("ada",1, "anangpras");

                    if (updateSessionLogin == true) {
                        Toast.makeText(getApplicationContext(), "Berhasil Login", Toast.LENGTH_SHORT).show();
                        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(mainIntent);
                        finish();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Login Gagal", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}