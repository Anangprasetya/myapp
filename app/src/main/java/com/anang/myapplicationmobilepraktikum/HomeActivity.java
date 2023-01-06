package com.anang.myapplicationmobilepraktikum;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    String[] daftar;
    ListView ListView01;
    Menu menu;
    protected Cursor cursor;
    DatabaseSQLite dbcenter;

    public static HomeActivity ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btn = (Button) findViewById(R.id.btn_tambah);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(HomeActivity.this, TambahBarangActivity.class);
                startActivity(inte);
            }
        });

        dbcenter = new DatabaseSQLite(this);
        RefreshList();
    }

    public void RefreshList(){
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM barang",null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
        }
        ListView01 = (ListView)findViewById(R.id.listView1);
        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        ListView01.setSelected(true);

        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView arg0, View arg1,
                                    int arg2, long arg3) {
                final String selection = daftar[arg2];
//.getItemAtPosition(arg2).toString();
                final CharSequence[] dialogitem = {"Lihat Barang", "Update Barang", "Hapus Barang"};
                AlertDialog.Builder builder = new
                        AlertDialog.Builder(HomeActivity.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int
                                    item) {
                                switch(item){
                                    case 0 :
                                        Intent i = new
                                                Intent(getApplicationContext(), DetailBarangActivity.class);
                                        i.putExtra("namaBarang", selection);

                                        startActivity(i);
                                        break;
                                    case 1 :
                                        Intent in = new Intent(getApplicationContext(), UpdateBarangActivity.class);
                                        in.putExtra("namaBarang", selection);
                                        startActivity(in);

                                        break;
                                    case 2 :
                                        SQLiteDatabase db = dbcenter.getWritableDatabase();
                                        db.execSQL("delete from barang where nama_brg = '"+selection+"'");
                                        Toast.makeText(getApplicationContext(),
                                                "Berhasil Menghapus Barang", Toast.LENGTH_LONG).show();
                                        RefreshList();
                                        break;
                                }
                            }
                        });
                builder.create().show();
            }});

    }
}