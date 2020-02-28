package main;

import java.io.*;
import shape.*;

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


        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            while ((s = br.readLine()) != null) {
                String[] tokens = s.split(",");
                try { // try to construct shapes and if success then add into array
                    if (tokens[0].equals("Square")) {
                        if (tokens.length > 2) {
                            throw new IllegalArgumentException("To may arguments for type 'Square'");
                        } else {
                            shapeArray[i] = new Square(Double.parseDouble(tokens[1]));
                            i++;
                        }
                    } else if (tokens[0].equals("Circle")) {
                        if (tokens.length > 2) {
                            throw new IllegalArgumentException("To may arguments for type 'Circle'");
                        } else {
                            shapeArray[i] = new Circle(Double.parseDouble(tokens[1]));
                            i++;
                        }
                    } else if (tokens[0].equals("Triangle")) {
                        if (tokens.length > 4) {
                            throw new IllegalArgumentException("To may arguments for type 'Triangle'");
                        } else {
                            shapeArray[i] = new Triangle(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]));
                            i++;
                        }
                    } else if (tokens[0].equals("Rectangle")) {
                        shapeArray[i] = new Rectangle(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]));
                        i++;
                    } else if (tokens[0].equals("Parallelogram")) {
                        if (tokens.length > 3) {
                            throw new IllegalArgumentException("To may arguments for type 'Parallelogram'");
                        } else {
                            shapeArray[i] = new Parallelogram(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]));
                            i++;
                        }
                    }
                }
                catch (SquareException | RectangleException | CircleException | TriangleException | IllegalArgumentException e) {
                    System.out.println(e.getMessage());
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

        System.out.println("\n------->Task 2 ... <------");
        /**
         * getName() will return the derived name of the polymoriphic object.
         * in the for loop check for Triangle or Circle and keep comparing values
         * to get the min Triangle and max Circle
         */
        double minValTri = 1000.00;
        double maxValCirc = 0.0;

        for (int j = 0; j < shapeArray.length; j++) {
            if (shapeArray[j] != null) {
                if (shapeArray[j].getName().equals("Triangle")) {
                    double temp = shapeArray[j].perimeter();
                    if (temp < minValTri)
                        minValTri = temp;
                }
                else if (shapeArray[j].getName().equals("Circle")) {
                    double temp = shapeArray[j].perimeter();
                    if (temp > maxValCirc)
                        maxValCirc = temp;
                }
            }
        }
        /**
         * Because the Shape array contains extra elements of null, since not all ojects could be constructed,
         * I had to wrap for loop conditions in a not null. Then I checked for either a circle or triangle
         * and if the values matched what was cacluated above. If this was the case I set that element to null
         * and if the value was not set the null I printed out to the console.
         */
        for (int j = 0; j < shapeArray.length; j++) {
            if (shapeArray[j] != null) {
                if ((shapeArray[j].getName().equals("Triangle") && shapeArray[j].perimeter() == minValTri) ||
                        (shapeArray[j].getName().equals("Circle") && shapeArray[j].perimeter() == maxValCirc))
                    shapeArray[j] = null;
                if (shapeArray[j] != null)
                    System.out.println(shapeArray[j]);
            }
        }

        /**
         * created variable to store the running total for both Parallelogram and Triangle. Then
         * outputed to the screen when completed
         */
        System.out.println("------->Task 3 ... <-------\n");
        double paraTotal = 0.0;
        double triTotal = 0.0;
        for (Shape shape:shapeArray) {
            if (shape != null) {
                if (shape.getName().equals("Parallelogram"))
                    paraTotal += shape.perimeter();
                else if (shape.getName().equals("Triangle"))
                    triTotal += shape.perimeter();
            }
        }
        System.out.println("Total perimeter of Parallelogram is: " + paraTotal);
        System.out.println("Total perimeter of Triangle is: " + triTotal);
    }
}
