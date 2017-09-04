package com.evoteam.evolist;

/**
 * Created by user on 8/9/2017.
 */

public class DataBaseSchema {

    //table
    public static final class TaskDataBase {
        //table's name
        public static final String NAME = "favorite_table";

        //defining each column
        public static final class cols {
            public static final String NAME        = "name";
            public static final String DAY         = "day";
            public static final String DATE        = "date";
            public static final String TIME        = "time";
            public static final String DESCRIPTION = "description";
            public static final String ISIMPORTANT = "isImportant";
        }
    }
}
