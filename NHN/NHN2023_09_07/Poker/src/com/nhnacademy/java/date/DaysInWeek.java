package com.nhnacademy.java.date;

public enum DaysInWeek {
    MON("Monday"),
    TUE("Tuesday"),
    WED("Wednesday"),
    THU("Thursday"),
    FRI("Friday"),
    SAT("Saturday"),
    SUN("Sunday");

    public final String dayName;

    DaysInWeek(String dayName) {
        this.dayName = dayName;
    }

    public String toString() {
        return this.dayName;
    }
}
