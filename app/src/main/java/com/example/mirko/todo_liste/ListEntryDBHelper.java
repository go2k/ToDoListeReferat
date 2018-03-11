package com.example.mirko.todo_liste;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mirko on 10.03.18.
 */

public class ListEntryDBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "totolist";

    private static final String TABLE_TODO = "toto";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TEXT= "textdata";


    private static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_TODO +
                    " (" + COLUMN_ID + " TEXT PRIMARY KEY NOT NULL, " +
                    COLUMN_TEXT + " TEXT NOT NULL);";

    private String[] columns = {
            COLUMN_ID,
            COLUMN_TEXT
    };

    private SQLiteDatabase database;


    public ListEntryDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void open() {
        this.database = super.getWritableDatabase();
        Log.d("Meins", "Pfad zur Datenbank: " + database.getPath());
    }

    public void close() {
        super.close();
    }


    public void saveEntry(ListEntry entry) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entry.getId());
        values.put(COLUMN_TEXT, entry.getText());

        long insertId = this.database.insert(TABLE_TODO, null, values);
    }

    private ListEntry cursorToListEntry(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(COLUMN_ID);
        int idText = cursor.getColumnIndex(COLUMN_TEXT);

        String text = cursor.getString(idText);
        String id = cursor.getString(idIndex);

        ListEntry listEntry = new ListEntry(id,text);

        return listEntry;
    }

    public List<ListEntry> getAllEntries() {
        List<ListEntry> entryArrayList = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_TODO,
                columns, null, null, null, null, null);


        if(cursor != null) {
            cursor.moveToFirst();

            while (cursor.moveToNext()) {
                ListEntry listEntry = cursorToListEntry(cursor);
                entryArrayList.add(listEntry);
            }

            cursor.close();
        }

        return entryArrayList;
    }

    public boolean removeListEntry(ListEntry entry){
        if(entry != null){
            return (this.database.delete(TABLE_TODO, COLUMN_ID + " = '" + entry.getId() + "'",null) > 0);
        }

        return false;
    }


}
