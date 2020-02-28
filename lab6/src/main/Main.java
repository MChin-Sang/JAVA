package main;

import java.io.*;
import shape.*;

/**
 * An array with a size of the amount of lines in the Shapes.txt is created.
 * Using ShapeFactory, which will return a shape based on the provided token, objects of Shape were added to the array
 */
public class Main {

    public static void main(String[] args) {
        String s;
        int size = 0;
        int i = 0;
        Shape[] shapeArray;

        // count lines in file to get size for array
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            while (br.readLine() != null) {
                size++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        shapeArray = new Shape[size];

        /**
         * Use ShapeFactory to construct shapes and add to array
         */
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            while ((s = br.readLine()) != null) {
                String[] tokens = s.split(",");
                ShapeFactory shapeFactory = new ShapeFactory();
                shapeArray[i] = shapeFactory.getShape(tokens);
                if (shapeArray[i] != null) {
                    i++;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n------->JAC 444 Assignment 1<-------");
        System.out.println("------->Task 1 ... <-------");
        System.out.println(i + " shapes were created:\n");
        for (Shape shape:shapeArray) {
            if (shape != null)
                System.out.println(shape);
        }
    }
}
