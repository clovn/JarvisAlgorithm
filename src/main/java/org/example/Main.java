package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        java.util.List<Point> points = generateRandomPoints(20, 700, 700);
        JarvisAlgorithm alg = new JarvisAlgorithm();
        List<Point> hull =  alg.convexHullAlgorithm(points);

        VisualizeHull visualizer = new VisualizeHull(points, hull);
        visualizer.visualize();
    }

    private static java.util.List<Point> generateRandomPoints(int numPoints, int maxX, int maxY) {
        java.util.List<Point> points = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < numPoints; i++) {
            int x = random.nextInt(30, maxX);
            int y = random.nextInt(30, maxY);
            points.add(new Point(x, y));
        }
        return points;
    }
}
