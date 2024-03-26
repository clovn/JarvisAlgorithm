package org.example;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        try(BufferedWriter out = new BufferedWriter(new FileWriter("out.txt"))){
            for(int i = 0; i < 100; i++){
                JarvisAlgorithm alg = new JarvisAlgorithm();
                List<Point> points = FileGenerator.getDataFromFile("src/main/resources/data" + i + ".txt");
                List<Point> hull =  alg.convexHullAlgorithm(points);

                VisualizeHull visualizer = new VisualizeHull(points, hull, 10);
                visualizer.visualize();

                out.write(points.size() + " " + alg.getTime() + " " + alg.getIterations() + " " + hull.size() + "\n");
                out.flush();
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
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
