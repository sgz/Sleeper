package com.apps.serghei.sleeper;


import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by serg-desktop on 7/4/2016.
 */
public class DayModel {
    public Calendar CurrentDay; // date of the model
    public Calendar RiseTime;   // time of being awoken
    public Calendar SleepTime;  // time of bed
    public double HoursSlept; // hours slept last night
    public String Comments; // any remarks on the day
    public int Rating;      // a good-bad rating 0-bad, 1-ok, 2-good

    public DayModel(){
        CurrentDay = GregorianCalendar.getInstance();
        RiseTime = GregorianCalendar.getInstance();
        SleepTime = GregorianCalendar.getInstance();
        RiseTime.setTimeInMillis(0);
        SleepTime.setTimeInMillis(0);
    }
}
