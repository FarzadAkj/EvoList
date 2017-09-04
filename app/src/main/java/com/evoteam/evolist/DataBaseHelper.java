package com.evoteam.evolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 8/9/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    final static int VERSION = 2;
    final static String DATABASE_NAME = "dic.db";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DataBaseSchema.TaskDataBase.NAME + "(" +
                " _id integer primary key autoincrement, " +
                DataBaseSchema.TaskDataBase.cols.NAME + ", " +
                DataBaseSchema.TaskDataBase.cols.DAY + ", " +
                DataBaseSchema.TaskDataBase.cols.DATE + ", " +
                DataBaseSchema.TaskDataBase.cols.TIME + ", " +
                DataBaseSchema.TaskDataBase.cols.DESCRIPTION + ", " +
                DataBaseSchema.TaskDataBase.cols.ISIMPORTANT +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
