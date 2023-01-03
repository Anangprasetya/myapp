package com.anang.myapplicationmobilepraktikum;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseSQLite extends SQLiteOpenHelper {
    public DatabaseSQLite(Context context) {
        super(context, "toko_kelontong.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE session(id integer PRIMARY KEY, login text, user_username text)");
        sqLiteDatabase.execSQL("INSERT INTO session(id, login, user_username) VALUES(1, 'kosong', 'kosong')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void perbarui(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS session");
        onCreate(db);
    }

    //check session
    public Boolean checkSession(String sessionValues) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM session WHERE login = ?", new String[]{sessionValues});
        if (cursor.getCount() > 0) {
            return true;
        }
        else {
            return false;
        }
    }


    //upgrade session
    public Boolean upgradeSession(String sessionValues, int id, String usr) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("login", sessionValues);
        contentValues.put("user_username", usr);
        long update = db.update("session", contentValues, "id="+id, null);
        if (update == -1) {
            return false;
        }
        else {
            return true;
        }
    }

}

