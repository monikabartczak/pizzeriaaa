package com.example.monia.pizzeriaaa;

/**
 * Created by Monia on 2017-06-17.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by Monika on 2016-10-30.
 */
//zaimplementujemy inetrfejs BaseColumns, ktory dostarcza nam pole id i count
public class LogowanieDataBase implements BaseColumns {


    // TODO: Create public field for each column in your table.

    static final String DATABASE_CREATE_TABLE_USERS = "create table " + "LOGIN" +
            "( " + "ID" + " integer primary key autoincrement," + "USERNAME text,PASSWORD text); ";

    static final String DATABASE_CREATE_TABLE_ROZCHODY = "CREATE TABLE \"Rozchody\" (\n" +
            "\t`id_rozchody`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`miejsce_wydatku`\tTEXT NOT NULL,\n" +
            "\t`nazwa_kategorii_rozchodu`\tTEXT NOT NULL,\n" +
            "\t`kwota`\tNUMERIC NOT NULL,\n" +
            "\t`data`\tTEXT NOT NULL,\n" +
            "\t`sposób_finansowania`\tTEXT NOT NULL,\n" +
            "\t`login`\tTEXT NOT NULL\n" +
            ") ";

    static final String DATABASE_CREATE_TABLE_NUMERY_KONT = "CREATE TABLE \"numery_kont\" (\n" +
            "\t`id_numery_kont`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`login`\tTEXT NOT NULL,\n" +
            "\t`numer_konta`\tNUMERIC NOT NULL,\n" +
            "\t`nazwa`\tTEXT NOT NULL\n" +
            ") ";

    static final String DATABASE_CREATE_TABLE_UZYTKOWNICY = "CREATE TABLE \"uzytkownicy\" (\n" +
            "\t`id_user`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`haslo`\tTEXT NOT NULL,\n" +
            "\t`login`\tTEXT NOT NULL,\n" +
            "\t`numer_telefonu`\tNUMERIC NOT NULL,\n" +
            "\t`email`\tTEXT NOT NULL,\n" +
            "\t`nazwa_banku`\tTEXT NOT NULL,\n" +
            "\t`id_powiadomienia`\tINTEGER\n" +
            ") ";


    static final String DATABASE_CREATE_TABLE_PRZYCHODY = "CREATE TABLE \"Przychody\" (\n" +
            "\t`id_przychody`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`przychody_kategorie`\tTEXT NOT NULL,\n" +
            "\t`kwota`\tTEXT NOT NULL,\n" +
            "\t`data_przychodu`\tNUMERIC NOT NULL,\n" +
            "\t`rodzaj_wplywu`\tNUMERIC NOT NULL,\n" +
            "\t`login`\tTEXT NOT NULL\n" +
            ") ";

    static final String DATABASE_CREATE_TABLE_ZRODLA_PRZYCHODOW_KATEGORIE = "CREATE TABLE \"zroda_przychodow_kategorie\" (\n" +
            "\t`id_zroda_przychodow_kategorie`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`nawa_kategorii`\tTEXT NOT NULL\n" +
            ") ";

    static final String DATABASE_CREATE_TABLE_MIEJSCA_WYDATKOW = "CREATE TABLE \"miejsca_wydatkow\" (\n" +
            "\t`id_miejsca_wydatkow`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`nazwa_miejsca`\tTEXT NOT NULL,\n" +
            "\t`id_miasta`\tTEXT,\n" +
            "\t`opis`\tNUMERIC NOT NULL\n" +
            ") ";

    static final String DATABASE_CREATE_TABLE_MIASTA = "CREATE TABLE \"miasta\" (\n" +
            "\t`id_miasta`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`id_kody_pocztowe`\tINTEGER NOT NULL,\n" +
            "\t`nazwa_miasta`\tTEXT NOT NULL\n" +
            ") ";

    static final String DATABASE_CREATE_TABLE_KODY_POCZTOWE = "CREATE TABLE \"kody_pocztowe\" (\n" +
            "\t`id_kody_pocztowe`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`kod_pocztowy`\tTEXT NOT NULL\n" +
            ") ";
    static final String DATABASE_CREATE_TABLE_BANKI = "CREATE TABLE \"banki\" (\n" +
            "\t`id_banki`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`adres_strony_banku`\tTEXT NOT NULL\n" +
            ") ";

    public SQLiteDatabase db;
    private final Context context;
    private DataBaseHelper dbHelper;

    public LogowanieDataBase(Context _context) {
        context = _context;
        dbHelper = new DataBaseHelper(context);
    }

    public LogowanieDataBase open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    public void insertEntry(String userName, String password) {
        ContentValues newValues = new ContentValues();
        newValues.put("USERNAME", userName);
        newValues.put("PASSWORD", password);


        db.insert("LOGIN", null, newValues);

    }

    public int deleteEntry(String UserName) {

        String where = "USERNAME=?";
        int numberOFEntriesDeleted = db.delete("LOGIN", where, new String[]{UserName});

        return numberOFEntriesDeleted;
    }

    public String getSinlgeEntry(String userName) {
        Cursor cursor = db.query("LOGIN", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return "Nie istnieję";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }

    public void updateEntry(String userName, String password) {

        ContentValues updatedValues = new ContentValues();

        updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD", password);


        String where = "USERNAME = ?";
        db.update("LOGIN", updatedValues, where, new String[]{userName});
    }


}


