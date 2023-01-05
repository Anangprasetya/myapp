package com.anang.myapplicationmobilepraktikum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TambahBarangActivity extends AppCompatActivity {

    protected Cursor cursor;
    DatabaseSQLite dbHelper;
    Button ton1, ton2;
    EditText text1, text2, text3, text4, text5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_barang);

        dbHelper = new DatabaseSQLite(this);
        text1 = (EditText) findViewById(R.id.txtName);
        text2 = (EditText) findViewById(R.id.txtharga);
        text3 = (EditText) findViewById(R.id.txtstok);
        text4 = (EditText) findViewById(R.id.txtukuran);
        text5 = (EditText) findViewById(R.id.txtkualitas);

        ton1 = (Button) findViewById(R.id.buttonSimpan);
//        ton2 = (Button) findViewById(R.id.button2);

        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db =
                        dbHelper.getWritableDatabase();
                db.execSQL("insert into barang(nama_brg, harga_brg, stok_brg, ukuran_brg, kualitas_brg) values('" +
                        text1.getText().toString()+"','"+
                        text2.getText().toString() +"','" +
                        text3.getText().toString()+"','"+
                        text4.getText().toString() +"','" +
                        text5.getText().toString() + "')");
                Toast.makeText(getApplicationContext(),
                        "Berhasil", Toast.LENGTH_LONG).show();
//                HomeActivity.ma.RefreshList();
                Intent pindah = new Intent(TambahBarangActivity.this, HomeActivity.class);
                startActivity(pindah);
                finish();
            }
        });

//        ton2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
    }
}