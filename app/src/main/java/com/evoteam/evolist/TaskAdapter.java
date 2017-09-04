package com.evoteam.evolist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by user on 8/9/2017.
 */

public class TaskAdapter extends ArrayAdapter {

    private List<Task> tasks;

    public TaskAdapter(Context context, List<Task> tasks) {
        super(context, R.layout.task_list_item, tasks);
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Task currentTask = tasks.get(position);

        LayoutInflater inflator = (LayoutInflater)
                getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflator.inflate(R.layout.task_list_item, parent, false);
        TextView name = (TextView) view.findViewById(R.id.taskNameTextViewListItem);
        TextView day = (TextView) view.findViewById(R.id.taskDayTextViewListItem);
        ImageView importance = (ImageView) view.findViewById(R.id.importanceImageViewListItem);

        name.setText(currentTask.getName());
        day.setText(currentTask.getDay());
        if(currentTask.isImportant())
            importance.setVisibility(View.VISIBLE);
        else
            importance.setVisibility(View.INVISIBLE);

        return view;
    }
}
