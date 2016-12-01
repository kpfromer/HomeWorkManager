package com.kylepfromer.todo;

import java.time.LocalDate;

/**
 * Created by kpfromer on 11/29/16.
 */

public class Task implements Comparable {

    //Main variables for functionality
    private String mText;
    private LocalDate mDueDate;
    private SchoolClass mSchoolClass;
    //Used for editing task in database
    private long mID;

    //Other variables for displaying
    private int mPosition;
    private boolean mDone;


    public Task(String text, LocalDate duedate, SchoolClass schoolclass, long ID, int position, boolean done) {
        this.mText = TextParser.cleanText(text);
        this.mDueDate = duedate;
        this.mSchoolClass = schoolclass;
        this.mID = ID;
        this.mPosition = position;
        this.mDone = done;
    }

    public Task(String text, LocalDate duedate, long ID, int position) {
        this(text, duedate, null, ID, position, false);
    }

    public Task(String text, SchoolClass schoolclass, long ID, int position) {
        this(TextParser.removeDayTextFromString(text), TextParser.getLocalDateFromString(text), schoolclass, ID, position, false);
    }

    public Task(String text, long ID, int position) {
        this(TextParser.removeSchoolClassFromString(TextParser.removeDayTextFromString(text)), TextParser.getLocalDateFromString(text), TextParser.getSchoolClassFromString(text), ID, position, false);
    }

    //Used to sort tasks by date
    public int compareTo(Object obj) {
        Task otherTask = (Task) obj;
        return this.mDueDate.compareTo(otherTask.getmDueDate());
    }

    public String getmText() {
        return this.mText;
    }


    public void setmText(String mText) {
        if (this.mDueDate == null) {
            this.mDueDate = TextParser.getLocalDateFromString(mText);
        }

        this.mText = mText;
    }

    public SchoolClass getmSchoolClass() {
        return mSchoolClass;
    }

    public LocalDate getmDueDate() {
        return this.mDueDate;
    }

}

