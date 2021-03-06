package com.hackbulgaria.corejava.exam1.logger;

import java.util.Calendar;

public class DateLogger extends Logger {
    private final Calendar calendar;
    
    public DateLogger() {
        super();
        calendar = Calendar.getInstance();
    }
    
    public DateLogger(int level) {
        super(level);
        calendar = Calendar.getInstance();
    }
    
    public void log(int level, String message) {
        System.out.printf("|%d:%d:%d %d.%d.%d| ", calendar.get(Calendar.HOUR),
                                                  calendar.get(Calendar.MINUTE),
                                                  calendar.get(Calendar.SECOND),
                                                  calendar.get(Calendar.DATE),
                                                  calendar.get(Calendar.MONTH),
                                                  calendar.get(Calendar.YEAR));
        super.log(level, message);
    }
    
    public void log(String message) {
        log(getLevel(), message);
    }
}
