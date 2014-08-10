package com.hackbulgaria.corejava.racecondition;

import java.text.SimpleDateFormat;

public class RaceConditionSlowSolution extends Thread {
    private static int counter = 0;
    private static Object monitor = new Object();
    
    @Override
    public void run() {
        for (int i = 0; i < 2_000_000; i++) {
            synchronized (monitor) {
                counter++;
            }
        }
    }

    public static void main(String[] args) {
        RaceConditionSlowSolution A = new RaceConditionSlowSolution();
        RaceConditionSlowSolution B = new RaceConditionSlowSolution();
        
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
