package com.kylepfromer.todo;

/**
 * Created by kpfromer on 11/29/16.
 */

public class Prompter {
    //Todo: Need to make it user specific
    public static final SchoolClass PERIOD_ONE = new SchoolClass("PE", 0, "8:05-8:55", "Mrs. Roetto", "becky.roetto@bvsd.org", "https://sites.google.com/a/bvsd.org/fairview-pe/home");
    public static final SchoolClass PERIOD_TWO = new SchoolClass("Science", 1, "9:00-9:50", "Mr. Muzny", "dylan.muzny@bvsd.org", "https://www.fairviewhs.org/staff/dylan-muzny/classes/pib-biology");
    public static final SchoolClass PERIOD_THREE = new SchoolClass("World History", 2, "9:55-10:45", "Mr. McCarthy", "daniel.mccarthy@bvsd.org", "https://www.fairviewhs.org/staff/daniel-mccarthy/classes/world-history");
    public static final SchoolClass PERIOD_FOUR = new SchoolClass("Photography One", 3, "10:50-11:40", "Mr. Jaramillo", "michael.jaramillo@bvsd.org", "https://bvsd.schoology.com/course/707776339/materials");
    public static final SchoolClass PERIOD_FIVE = new SchoolClass("Lunch", 4, "11:45–12:35", "", "", "", "LUNCH");
    public static final SchoolClass PERIOD_SIX = new SchoolClass("Spanish", 5, "12:40–1:30", "Mr. Parada", "edgardo.parada@bvsd.org", "https://www.fairviewhs.org/staff/edgardo-parada-1", "Don\'t email him! He wont answer!");
    public static final SchoolClass PERIOD_SEVEN = new SchoolClass("LA", 6, "1:35–2:25", "Mr. Lewis", "james.lewis@bvsd.org", "https://sites.google.com/a/bvsd.org/10th-grade-language-arts-2013-2014/");
    public static final SchoolClass PERIOD_EIGHT = new SchoolClass("Math", 7, "2:30–3:20", "Mrs. Prendergast", "megan.prendergast@bvsd.org", "https://www.fairviewhs.org/staff/megan-prendergast/classes/advanced-algebra-2", "Go to https://www.mathxlforschool.com/home_school.htm for Math XL homework.");
    public static TaskManager taskManager = new TaskManager();

    public Prompter() {
    }
}
