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

public class UpdateBarangActivity extends AppCompatActivity {

    protected Cursor cursor;
    DatabaseSQLite dbHelper;
    Button ton1, ton2;
    EditText text1, text2, text3, text4, text5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_barang);

        dbHelper = new DatabaseSQLite(this);
        text1 = (EditText) findViewById(R.id.txtName);
        text2 = (EditText) findViewById(R.id.txtharga);
        text3 = (EditText) findViewById(R.id.txtstok);
        text4 = (EditText) findViewById(R.id.txtukuran);
        text5 = (EditText) findViewById(R.id.txtkualitas);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM barang WHERE nama_brg = '" + getIntent().getStringExtra("namaBarang") + "'",null);
        cursor.moveToFirst();

        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(1).toString());
            text2.setText(cursor.getString(2).toString());
            text3.setText(cursor.getString(3).toString());
            text4.setText(cursor.getString(4).toString());
            text5.setText(cursor.getString(5).toString());
        }

        ton1 = (Button) findViewById(R.id.buttonSimpan);
//        ton2 = (Button) findViewById(R.id.button2);

        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db =
                        dbHelper.getWritableDatabase();
                db.execSQL("update barang set nama_brg='"+ text1.getText().toString() +"', harga_brg='" + text2.getText().toString()+"', stok_brg='"+
                        text3.getText().toString() +"', ukuran_brg='"
                        +
                        text4.getText().toString() + "', kualitas_brg='"+text5.getText().toString()+"' where nama_brg='" +cursor.getString(1).toString()+"'");
                Toast.makeText(getApplicationContext(),
                        "Berhasil Di Perbarui", Toast.LENGTH_LONG).show();
//                HomeActivity.ma.RefreshList();
                Intent pindah = new Intent(UpdateBarangActivity.this, HomeActivity.class);
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