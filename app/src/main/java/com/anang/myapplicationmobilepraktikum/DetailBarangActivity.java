package com.anang.myapplicationmobilepraktikum;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailBarangActivity extends AppCompatActivity {

    protected Cursor cursor;
    DatabaseSQLite dbHelper;
    Button ton2;
    TextView text1, text2, text3, text4, text5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_barang);

        dbHelper = new DatabaseSQLite(this);
        text1 = (TextView) findViewById(R.id.nama_brg);
        text2 = (TextView) findViewById(R.id.harga_brg);
        text3 = (TextView) findViewById(R.id.stok_brg);
        text4 = (TextView) findViewById(R.id.ukuran_brg);
        text5 = (TextView) findViewById(R.id.kualitas_brg);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM barang WHERE nama_brg = '" + getIntent().getStringExtra("namaBarang") + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(1).toString());
            text2.setText(cursor.getString(2).toString());
            text3.setText(cursor.getString(3).toString());
            text4.setText(cursor.getString(4).toString());
            text5.setText(cursor.getString(5).toString());
        }

        ton2 = (Button) findViewById(R.id.kembali);
        ton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }
}