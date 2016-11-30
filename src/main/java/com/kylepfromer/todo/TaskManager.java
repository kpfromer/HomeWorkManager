package com.kylepfromer.todo;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by kpfromer on 11/29/16.
 */

public class TaskManager {
    private ArrayList<Task> mTasks;

    public TaskManager(ArrayList<Task> tasks) {
        this.mTasks = tasks;
    }

    public TaskManager() {
        this.mTasks = new ArrayList();
    }

    public void addTask(Task task) {
        this.mTasks.add(task);
    }

    public void deleteTaskByName(String name) {
        for (int y = 0; y < this.mTasks.size(); ++y) {
            Task x = (Task) this.mTasks.get(y);
            if (name.equalsIgnoreCase(x.getmText())) {
                this.mTasks.remove(y);
            }
        }

    }

    public Task findTaskByName(String name) {
        int position = -1;

        for (int y = 0; y < this.mTasks.size(); ++y) {
            Task x = (Task) this.mTasks.get(y);
            if (name.equalsIgnoreCase(x.getmText())) {
                position = y;
            }
        }

        return (Task) this.mTasks.get(position);
    }

    public ArrayList<Task> sortByDate() {
        ArrayList tasks = this.mTasks;
        Collections.sort(tasks);
        return tasks;
    }

    public ArrayList<Task> getmTasks() {
        return this.mTasks;
    }

    public void setmTasks(ArrayList<Task> mTasks) {
        this.mTasks = mTasks;
    }
}
