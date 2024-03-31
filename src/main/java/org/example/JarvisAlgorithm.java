package org.example;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class JarvisAlgorithm {

    private long time = 0;
    private int iterations = 0;

    // Определение направления поворота
    private boolean orientation(Point a, Point b, Point c) {
        int val = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
        return val > 0;
    }

    public long getTime() {
        return time;
    }

    public int getIterations() {
        return iterations;
    }

    // Реализация алгоритма Джарвиса
    public List<Point> convexHullAlgorithm(List<Point> points) {
        time = System.nanoTime();

        List<Point> convexHullPoints = new ArrayList<>();

        if(points.size() < 3){
            return convexHullPoints;
        }

        int start = 0;
        //Находим нижнюю правую точку
        for(int i = 1; i < points.size(); i++){
            Point p1 = points.get(i);
            Point p2 = points.get(start);
            if(p1.y > p2.y){
               start = i;
            } else if(p1.y == p2.y && p1.x > p2.x){
                start = i;
            }
            iterations++;
        }

        int p = start;
        int q;

        do{
            convexHullPoints.add(points.get(p));
            q = (p+1) % points.size();

            for(int i = 0; i < points.size(); i++){
                if(orientation(points.get(p), points.get(q), points.get(i))){
                    q = i;
                }
                iterations++;
            }
            p = q;

        } while (p != start);

        time = System.nanoTime() - time;

        return convexHullPoints;
    }
}
