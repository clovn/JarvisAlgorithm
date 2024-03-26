package org.example;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FileGenerator {
    public static void main(String[] args) {
        generateFiles(100);
    }

    public static void generateFiles(int count){
        for(int i = 0; i < count; i++){
            File file = new File("src/main/resources/data" + i + ".txt");
            try {
               boolean f =  file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try(BufferedWriter out = new BufferedWriter(new FileWriter(file))){
                generateData(out);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("data" + i + ".txt");
        }
    }

    public static void generateData(BufferedWriter out) throws IOException{
        Random rand = new Random();
        int countEl = rand.nextInt(100, 10000);
        out.write(countEl + "\n");
        for(int i = 0; i < countEl; i++){
            out.write(rand.nextInt(0, 600) + " " + rand.nextInt(0, 600) + "\n");
        }
    }

    public static List<Point> getDataFromFile(String file){
        try(BufferedReader in = new BufferedReader(new FileReader(file))){

            List<Point> data = new ArrayList<>();
            int length = Integer.parseInt(in.readLine());

            for(int i = 0; i < length; i++){
                String[] tmp = in.readLine().split(" ");
                data.add(new Point(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1])));
            }

            return data;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
