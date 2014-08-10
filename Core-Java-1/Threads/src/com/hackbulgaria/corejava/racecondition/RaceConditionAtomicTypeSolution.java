package com.hackbulgaria.corejava.racecondition;

import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicInteger;

public class RaceConditionAtomicTypeSolution extends Thread {
    private static AtomicInteger counter = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i = 0; i < 2_000_000; i++) {
            counter.incrementAndGet();
        }
    }
    
    public static void main(String[] args) {
        RaceConditionAtomicTypeSolution A = new RaceConditionAtomicTypeSolution();
        RaceConditionAtomicTypeSolution B = new RaceConditionAtomicTypeSolution();
        
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
