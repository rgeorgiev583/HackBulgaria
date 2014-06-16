package com.hackbulgaria.corejava.oopintro.datetime;

import java.util.Calendar;

public class Time {
    final Calendar calendar;
    
    protected Time() {
        calendar = Calendar.getInstance();
    }
    
    protected Time(int year, int month, int date, int hour, int minute, int second) {
        calendar = Calendar.getInstance();
        calendar.set(year, month, date, hour, minute, second);
    }
    
    public int getYear() {
        return calendar.get(Calendar.YEAR);
    }
    
    public int getMonth() {
        return calendar.get(Calendar.MONTH);
    }
    
    public int getDate() {
        return calendar.get(Calendar.DATE);
    }
    
    public int getHour() {
        return calendar.get(Calendar.HOUR_OF_DAY);
    }
    
    public int getMinute() {
        return calendar.get(Calendar.MINUTE);
    }
    
    public int getSecond() {
        return calendar.get(Calendar.SECOND);
    }
    
    public void setYear(int year) {
        calendar.set(Calendar.YEAR, year);
    }
    
    public void setMonth(int month) {
        calendar.set(Calendar.MONTH, month);
    }
    
    public void setDate(int date) {
        calendar.set(Calendar.DATE, date);
    }
    
    public void setHour(int hour) {
        calendar.set(Calendar.HOUR_OF_DAY, hour);
    }
    
    public void setMinute(int minute) {
        calendar.set(Calendar.MINUTE, minute);
    }
    
    public void setSecond(int second) {
        calendar.set(Calendar.SECOND, second);
    }
    
    public String toString() {
        return String.format("%d:%d:%d %d.%d.%d", getHour(), getMinute(), getSecond(), getDate(), getMonth(), getYear());
    }
}
