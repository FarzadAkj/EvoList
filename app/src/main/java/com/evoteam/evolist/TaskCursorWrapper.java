package com.evoteam.evolist;

import android.database.Cursor;
import android.database.CursorWrapper;

/**
 * Created by user on 8/9/2017.
 */

public class TaskCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public TaskCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Task getTask(){
        String name = getString(getColumnIndex(DataBaseSchema.TaskDataBase.cols.NAME));
        String day = getString(getColumnIndex(DataBaseSchema.TaskDataBase.cols.DAY));
        String date = getString(getColumnIndex(DataBaseSchema.TaskDataBase.cols.DATE));
        String time = getString(getColumnIndex(DataBaseSchema.TaskDataBase.cols.TIME));
        String description = getString(getColumnIndex(DataBaseSchema.TaskDataBase.cols.DESCRIPTION));
        boolean isImportant = Boolean.parseBoolean(getString(getColumnIndex(DataBaseSchema.TaskDataBase.cols.ISIMPORTANT)));

        Task currentTask = new Task(name, day, date, time, description, isImportant);
        return currentTask;
    }
}
