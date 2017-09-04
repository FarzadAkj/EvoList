package com.evoteam.evolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by user on 8/9/2017.
 */

public class DataBaseManager {

    private Context mContext;
    private SQLiteDatabase mySQLiteDatabase;

    private ArrayList taskList;
    private static TaskCursorWrapper cursor;

    public DataBaseManager(Context context){
        mContext = context.getApplicationContext();
        mySQLiteDatabase = new DataBaseHelper(mContext).getWritableDatabase();
        cursor = queryTask();
    }

    public void addTask(Task currentTask){
        ContentValues value = getTaskValues(currentTask);
        mySQLiteDatabase.insert(DataBaseSchema.TaskDataBase.NAME, null, value);
    }

    private static ContentValues getTaskValues(Task currentTask){
        ContentValues values = new ContentValues();

        values.put(DataBaseSchema.TaskDataBase.cols.NAME        , currentTask.getName());
        values.put(DataBaseSchema.TaskDataBase.cols.DAY         , currentTask.getDay());
        values.put(DataBaseSchema.TaskDataBase.cols.DATE        , currentTask.getDate());
        values.put(DataBaseSchema.TaskDataBase.cols.TIME        , currentTask.getTime());
        values.put(DataBaseSchema.TaskDataBase.cols.DESCRIPTION , currentTask.getDescription());
        values.put(DataBaseSchema.TaskDataBase.cols.ISIMPORTANT , String.valueOf(currentTask.isImportant()));

        return values;
    }

    public ArrayList getTasks(){
        taskList = new ArrayList<>();

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Task temp = cursor.getTask();
            taskList.add(temp);
            cursor.moveToNext();
        }

        return taskList;
    }

    private TaskCursorWrapper queryTask() {
        Cursor cursor = mySQLiteDatabase.query(DataBaseSchema.TaskDataBase.NAME
                , null, null, null, null, null, null);

        return new TaskCursorWrapper(cursor);
    }

    public void deleteTask(Task currentTask){
        mySQLiteDatabase.delete(DataBaseSchema.TaskDataBase.NAME, DataBaseSchema.TaskDataBase.cols.NAME + "=" + "'" + currentTask.getName() + "'"
                + " and " + DataBaseSchema.TaskDataBase.cols.DAY + "=" + "'" + currentTask.getDay() + "'"
                + " and " + DataBaseSchema.TaskDataBase.cols.DATE + "=" + "'" + currentTask.getDate() + "'"
                + " and " + DataBaseSchema.TaskDataBase.cols.TIME + "=" + "'" + currentTask.getTime() + "'", null);
    }

    private int databaseSize() {
        int size = 0;
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if(!cursor.isAfterLast()) {
                size++;
                cursor.moveToNext();
            }
        }
        return size;
    }

    public int getDataBaseSize() {
        int size = databaseSize();
        return size;
    }

    public void deleteAllDataBase() {
        mySQLiteDatabase.delete(DataBaseSchema.TaskDataBase.NAME, null, null);
        try {
            Log.d("***size db", String.valueOf(getDataBaseSize()) + " " + String.valueOf(getDataBaseInJson()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getDataBaseInJson() throws JSONException {
        JSONArray datas = new JSONArray();
        if(this.getDataBaseSize() > 0) {

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                JSONObject tempData = new JSONObject();
                Task currentTask = cursor.getTask();
                tempData.put("name", currentTask.getName());
                tempData.put("day", currentTask.getDay());
                tempData.put("date", currentTask.getDate());
                tempData.put("time", currentTask.getTime());
                tempData.put("description", currentTask.getDescription());
                tempData.put("importance", currentTask.isImportant());

                datas.put(tempData);

                cursor.moveToNext();
            }
        }
        JSONObject sendToDO = new JSONObject();
        sendToDO.put("username","farzad");
        sendToDO.put("todo", datas);

        return sendToDO;
    }

}
