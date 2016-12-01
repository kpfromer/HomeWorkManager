package com.kylepfromer.todo;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * Created by kpfromer on 11/29/16.
 */

public class TextParser {
    private static String[] daysOfWeek = new String[]{"sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday"};
    private static SchoolClass[] classes;

    static {
        classes = new SchoolClass[]{Prompter.PERIOD_ONE, Prompter.PERIOD_TWO, Prompter.PERIOD_THREE, Prompter.PERIOD_FOUR, Prompter.PERIOD_FIVE, Prompter.PERIOD_SIX, Prompter.PERIOD_SEVEN, Prompter.PERIOD_EIGHT};
    }

    public TextParser() {
    }

    public static String removeDayTextFromString(String text) {
        String noDate = text;
        String[] var2 = daysOfWeek;
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            String day = var2[var4];
            if (text.contains(day)) {
                noDate = text.replace(day, "").replaceAll("\\s+", " ");
                break;
            }
        }

        return noDate;
    }

    public static LocalDate getLocalDateFromString(String text) {
        LocalDate date = null;
        LocalDate todayDate = LocalDate.now();
        text = text.toLowerCase();
        String dayContained = "";
        //todo: better variable names
        String[] var4 = daysOfWeek;
        int var5 = var4.length;

        for (int var6 = 0; var6 < var5; ++var6) {
            String day = var4[var6];
            if (text.contains(day)) {
                dayContained = day;
                break;
            }
        }

        if (!dayContained.equals("")) {
            if (Arrays.asList(daysOfWeek).indexOf(dayContained) <= todayDate.getDayOfWeek().getValue()) {

                //If the date from text is already passed in the current week
                //for example, date in text is monday but today is tuesday

                //Add a week from current date
                date = todayDate.plusWeeks(1L);
                //same as the else statement, get the difference and create new date from the difference
                date = date.minusDays((long) (todayDate.getDayOfWeek().getValue() - Arrays.asList(daysOfWeek).indexOf(dayContained)));
            } else {

                //get date from text as a value 1-6
                int dayContainedValue = Arrays.asList(daysOfWeek).indexOf(dayContained);
                //Get today's day of week value
                int todayValue = todayDate.getDayOfWeek().getValue();
                //Subtract them to get the difference
                long value = (long) (dayContainedValue - todayValue);
                //use the difference to find the new date value
                date = todayDate.plusDays(value);
            }
        }
        return date;
    }

    public static SchoolClass getSchoolClassFromString(String text) {
        text = text.toLowerCase();
        SchoolClass returnSchoolclass = null;
        SchoolClass[] var2 = classes;
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            SchoolClass schoolClass = var2[var4];
            if (text.contains("@" + schoolClass.getmName().toLowerCase())) {
                returnSchoolclass = schoolClass;
                break;
            }
        }

        return returnSchoolclass;
    }

    public static String removeSchoolClassFromString(String text) {
        String returnSchoolclass = text;
        SchoolClass[] var2 = classes;
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            SchoolClass schoolClass = var2[var4];
            String schoolClassText = "@" + schoolClass.getmName().toLowerCase();
            if (text.contains(schoolClassText)) {
                returnSchoolclass = text.replace(schoolClassText, "").replaceAll("\\s+", " ");
                break;
            }
        }

        return returnSchoolclass;
    }

    public static String cleanText(String text) {
        return text.replaceAll("^[\\s+ ]|[ \\t]+$", "").replaceAll("( )+", " ");
    }
}
