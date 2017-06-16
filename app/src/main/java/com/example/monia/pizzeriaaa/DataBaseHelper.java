package com.example.monia.pizzeriaaa;

import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Monia on 2017-06-17.
 */


public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String database_name = "nowaaportmonetkaa.db";//nazwa bazy danych
    //nazwy tabel

    public DataBaseHelper(Context context) {
        super(context, database_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase _db) {
        _db.execSQL(LogowanieDataBase.DATABASE_CREATE_TABLE_USERS);//CARD VIEW
        _db.execSQL(LogowanieDataBase.DATABASE_CREATE_TABLE_ROZCHODY);
        _db.execSQL(LogowanieDataBase.DATABASE_CREATE_TABLE_BANKI);
        _db.execSQL(LogowanieDataBase.DATABASE_CREATE_TABLE_NUMERY_KONT);
        //_db.execSQL(LogowanieDataBase.DATABASE_CREATE_TABLE_MIEJSCA_WYDATKOW);
        _db.execSQL(LogowanieDataBase.DATABASE_CREATE_TABLE_PRZYCHODY);
        _db.execSQL(LogowanieDataBase.DATABASE_CREATE_TABLE_ZRODLA_PRZYCHODOW_KATEGORIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {
        Log.w("TaskDBAdapter", "Uaktualnienie z wersji " + _oldVersion + " do " + _newVersion + ",  która zniszczy stare dane");
        _db.execSQL("DROP TABLE IF EXISTS " + "TEMPLATE");
        onCreate(_db);
    }
    public SQLiteCursor getDate(String query){//zwaracanie danych pobiera dane
        SQLiteDatabase database = this.getReadableDatabase();
        SQLiteCursor kursor = (SQLiteCursor) database.rawQuery(query, null);
        return kursor;
    }
}