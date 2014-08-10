package com.hackbulgaria.corejava.racecondition;

import java.text.SimpleDateFormat;

public class RaceConditionSyncMethodSolution extends Thread {
    private static int counter = 0;
    
    public static synchronized void increment() {
        for (int i = 0; i < 2_000_000; i++) {
            counter++;
        }
    }
    
    @Override
    public void run() {
        increment();
    }
    
    public static void main(String[] args) {
        RaceConditionSyncMethodSolution A = new RaceConditionSyncMethodSolution();
        RaceConditionSyncMethodSolution B = new RaceConditionSyncMethodSolution();
        
        final SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        long startTime = System.currentTimeMillis();
        System.err.printf("Started at %s...\n", dateFormat.format(startTime));
        A.start();
        B.start();
        try {
            A.join();
            B.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(counter);
        long endTime = System.currentTimeMillis();
        System.err.printf("Finished at %s.\n", dateFormat.format(endTime));
        System.err.printf("Time elapsed (ms): %d\n", endTime - startTime);
    }
}
