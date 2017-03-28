/**
 * Alex Diker 100746284
 * George Brown College
 */

package com.example.alexdiker.assignment3;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseSetting extends SQLiteOpenHelper {

    private static final int DB_VERSION = 2;

    private static final String DB_NAME = "db2_todo_list";

    DatabaseSetting(Context context) {
// database connection information
        super(context,

                DB_NAME,

                null,

                DB_VERSION
        );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE  TODOLIST (" + "DATA TEXT); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {

    }
    /** Add Data here */

    protected static void addNew(SQLiteDatabase db,
                                    String data) {
        ContentValues todoData = new ContentValues();
        todoData.put("DATA", data);

        db.insert("TODOLIST", null, todoData);

        System.out.println("OUTPUT: DATABASE ADDED ENTERY SUCCESSFULLY! - FOR DEBUG PURPOSES");

        System.out.println(todoData); // Print debugger information just for you :) - to show the value
    }

    /** Delete and Remove Data here */

    public static void delete(SQLiteDatabase db, String data) {
        db.execSQL("delete from TODOLIST where DATA='" + data + "'");

        System.out.println("OUTPUT: DATABASE DELETED ENTERY SUCCESSFULLY - FOR DEBUG PURPOSES");

        System.out.println(data); // Print debugger information just for you :) - to show the value
    }

}
