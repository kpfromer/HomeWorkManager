package com.kylepfromer.todo;

/**
 * Created by kpfromer on 11/29/16.
 */

public class SchoolClass {
    private String mName;
    private int mPeriod;
    private String mTime;
    private String mTeacher;
    private String mEmail;
    private String mSite;
    private String mNotes;

    public SchoolClass(String name, int period, String time, String teacher, String email, String site, String notes) {
        this.mName = name;
        this.mPeriod = period;
        this.mTime = time;
        this.mTeacher = teacher;
        this.mEmail = email;
        this.mSite = site;
        this.mNotes = notes;
    }

    public SchoolClass(String name, int period, String time, String teacher, String email, String site) {
        this(name, period, time, teacher, email, site, "");
    }

    private boolean isEmpty(String text) {
        return text == "";
    }

    public String getContactInfo() {
        String contactinfo = "";
        if (this.mNotes == "LUNCH") {
            contactinfo = "Period " + Integer.toString(this.mPeriod + 1) + ": " + this.mName;
        } else {
            if (this.isEmpty(this.mName) || this.isEmpty(Integer.toString(this.mPeriod)) || this.isEmpty(this.mTime) || this.isEmpty(this.mTeacher) || this.isEmpty(this.mEmail)) {
                throw new IllegalArgumentException("Missing important class info.");
            }

            contactinfo = "Period " + Integer.toString(this.mPeriod + 1) + ": " + this.mName + " with " + this.mTeacher + ". Email: " + this.mEmail + " Site: " + this.mSite;
            if (!this.isEmpty(this.mNotes)) {
                contactinfo = contactinfo + " Notes: " + this.mNotes;
            }
        }

        return contactinfo;
    }

    public String getmName() {
        return this.mName;
    }


}
