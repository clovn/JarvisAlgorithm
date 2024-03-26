package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VisualizeHull extends JPanel {
    private final List<Point> convexHull;
    private final List<Point> points;

    private int DELAY;
    private int currentPointIndex = 0;

    public VisualizeHull(List<Point> points, List<Point> convexHull, int delay) {
        this.convexHull = convexHull;
        this.points = points;
        this.DELAY = delay;
    }

    // Отрисовка точек и выпуклой оболочки
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (Point point : points) {
            g2d.setColor(Color.BLUE);
            g2d.fillOval(point.x - 3, point.y - 3, 6, 6);
        }


        g2d.setColor(Color.RED);
        for (int i = 0; i < currentPointIndex; i++) {
            Point p1 = convexHull.get(i);
            Point p2 = convexHull.get((i + 1) % convexHull.size());
            g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

    // Запуск визуализации
    public void visualize() {
        JFrame frame = new JFrame("Convex Hull Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        while (currentPointIndex < convexHull.size()) {
            // Анимация построения выпуклой оболочки
            currentPointIndex++;
            repaint();
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static List<Point> generatePoints(int count){
        Random rand = new Random();
        List<Point> data = new ArrayList<>();

        for(int i = 0; i < count; i++){
            data.add(new Point(rand.nextInt(30, 400), rand.nextInt(30, 400)));
        }

        return data;
    }

    public static void main(String[] args) {
        JarvisAlgorithm alg = new JarvisAlgorithm();
        List<Point> points = generatePoints(25);
        List<Point> hull =  alg.convexHullAlgorithm(points);

        VisualizeHull visualizer = new VisualizeHull(points, hull, 500);
        visualizer.visualize();
    }
}
