package com.hackbulgaria.corejava.threads;

import java.awt.Point;
import java.util.List;
import java.util.Map;

public class NearestPointsGetter extends Thread {
    private final List<Point> points;
    private final int indexFrom, indexTo;
    private final Map<Point, Point> nearestPoints;
    
    public static void addNearestPointsToMap(List<Point> points, int indexFrom, int indexTo, Map<Point, Point> nearestPoints) {
        for (int i = indexFrom; i <= indexTo; i++) {
            Point nearestPoint = points.get(0);
           
            for (Point anotherPoint : points) {
                if (points.get(i).equals(nearestPoint) && !nearestPoint.equals(anotherPoint) || !points.get(i).equals(nearestPoint) &&
                        points.get(i).distance(anotherPoint) < points.get(i).distance(nearestPoint)) {
                    nearestPoint = anotherPoint;
                }
            }
            
            nearestPoints.put(points.get(i), nearestPoint);
        }
    }
    
    public NearestPointsGetter(List<Point> points, int indexFrom, int indexTo, Map<Point, Point> nearestPoints) {
        this.points = points;
        this.indexFrom = indexFrom;
        this.indexTo = indexTo;
        this.nearestPoints = nearestPoints;
    }

    @Override
    public void run() {
        addNearestPointsToMap(points, indexFrom, indexTo, nearestPoints);
    }
}
