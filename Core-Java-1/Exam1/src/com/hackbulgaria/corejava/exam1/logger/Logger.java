package com.hackbulgaria.corejava.exam1.logger;

public class Logger {
    private int level;
    
    public Logger() {
        level = 3;
    }
    
    public Logger(Integer level) {
        if (level > 0) {
            this.level = level;
        }
        else {
            this.level = 3;
        }
    }
    
    public int getLevel() {
        return level;
    }
    
    public void setLevel(int level) {
        this.level = level;
    }
    
    public void log(int level, String message) {
        if (level <= this.level) {
            System.out.printf("%d => %s\n", level, message);
        }
    }
    
    public void log(String message) {
    	log(3, message);
    }
}
