package com.evoteam.evolist;

/**
 * Created by user on 7/15/2017.
 */

public class Task {
    private String name, day, date, time, description;
    private boolean isImportant;

    public Task(String name,String day,String date,String time,String description, boolean isImportant){
        setName(name);
        setDay(day);
        setDate(date);
        setTime(time);
        setDescription(description);
        setImportant(isImportant);
    }
    public Task(String name){
        this(name, " ", " ", " ", " ", false);
    }
    public Task(){
        this(" ");
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        if(date.length() >= 4)
            this.date = date;
        else
            this.date = " ";
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        if(time.length() >= 2)
            this.time = time;
        else
            this.time = " ";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.length() >= 3)
            this.name = name;
        else
            this.name = " ";
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(description.length() >= 3)
            this.description = description;
        else
            this.description = " ";
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        if(day.length() >= 3)
            this.day = day;
        else
            this.day = " ";

    }

    @Override
    public String toString() {
        return this.name;
    }
}
