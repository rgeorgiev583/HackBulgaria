package com.hackbulgaria.corejava.threads;

import java.awt.Point;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class NearestPointsVisualizer {
    public static List<Point> generatePoints() {
        final List<Point> points = new ArrayList<Point>();
        final Random random = new Random();
        final int numberOfPoints, maxCoordsOfPoints;
        
        try (Scanner scin = new Scanner(System.in)) {
            numberOfPoints = scin.nextInt();
            maxCoordsOfPoints = scin.nextInt();
        }
        
        for (int i = 0; i < numberOfPoints; i++) {
            points.add(new Point(random.nextInt(maxCoordsOfPoints), random.nextInt(maxCoordsOfPoints)));
        }
        
        return points;
    }
    
    public static Map<Point, Point> getNearestPoints(List<Point> points) {
        Map<Point, Point> nearestPoints = Collections.synchronizedMap(new HashMap<Point, Point>());

        for (Point point : points) {
            Point nearestPoint = points.get(0);
           
            for (Point anotherPoint : points) {
                if (point.equals(nearestPoint) && !point.equals(anotherPoint) || !point.equals(nearestPoint) &&
                        !point.equals(anotherPoint) && point.distance(anotherPoint) < point.distance(nearestPoint)) {
                    nearestPoint = anotherPoint;
                }
            }
            
            nearestPoints.put(point, nearestPoint);
        }
        
        return nearestPoints;
    }
    
    public static void main(String[] args) {
        //List<Point> points = generatePoints();
        List<Point> points = new ArrayList<Point>();
        points.add(new Point(6, 6));
        points.add(new Point(4, 5));
        points.add(new Point(4, 8));        
        points.add(new Point(9, 5));
        points.add(new Point(4, 7));
        Map<Point, Point> nearestPoints = Collections.synchronizedMap(new HashMap<Point, Point>());
        List<NearestPointsGetter> getters = new ArrayList<NearestPointsGetter>();
        int length = points.size();
        
        final SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        long startTime = System.currentTimeMillis();
        System.err.printf("Started at %s...\n", dateFormat.format(startTime));
        
        NearestPointsGetter firstHalfGetter = new NearestPointsGetter(points, 0, length / 2, nearestPoints);
        getters.add(firstHalfGetter);
        firstHalfGetter.start();
        
        NearestPointsGetter secondHalfGetter = new NearestPointsGetter(points, length / 2 + 1, length - 1, nearestPoints);
        getters.add(secondHalfGetter);
        secondHalfGetter.start();
        
        for (NearestPointsGetter getter : getters) {
            try {
                getter.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        long endTime = System.currentTimeMillis();
        System.err.printf("Finished at %s.\n", dateFormat.format(endTime));
        System.err.printf("Time elapsed (ms): %d\n", endTime - startTime);
        System.out.println(nearestPoints);
    }
}
