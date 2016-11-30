package com.kylepfromer.todo;

import java.time.LocalDate;

/**
 * Created by kpfromer on 11/29/16.
 */

public class Task implements Comparable {
    private String mText;
    private LocalDate mDueDate;
    private SchoolClass mSchoolClass;

    public Task(String text, LocalDate duedate, SchoolClass schoolclass) {
        this.mText = TextParser.cleanText(text);
        this.mDueDate = duedate;
        this.mSchoolClass = schoolclass;
    }

    public Task(String text, LocalDate duedate) {
        this(text, duedate, (SchoolClass) null);
    }

    public Task(String text, SchoolClass schoolclass) {
        this(TextParser.removeDayTextFromString(text), TextParser.getLocalDateFromString(text), schoolclass);
    }

    public Task(String text) {
        this(TextParser.removeSchoolClassFromString(TextParser.removeDayTextFromString(text)), TextParser.getLocalDateFromString(text), TextParser.getSchoolClassFromString(text));
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


    public LocalDate getmDueDate() {
        return this.mDueDate;
    }

}

