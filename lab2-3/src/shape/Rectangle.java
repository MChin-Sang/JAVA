package shape;

import java.util.concurrent.RecursiveAction;

public class Rectangle implements Shape {
    private double length;
    private double width;

    public Rectangle(double width, double length) throws RectangleException {
        if (length <= 0 || width <= 0) {
            throw new RectangleException("Invalid side(s)!");
        }
        else {
            this.length = length;
            this.width = width;
        }
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    @Override
    public String getName() {
        return "Rectangle";
    }
    @Override
    public double perimeter() {
        return (double)2*(getLength()+getWidth());
    }
    @Override
    public String toString() {
        return String.format("Rectangle {w=%.1f, h=%.1f} perimeter = %.4f", getWidth(), getLength(), perimeter());
    }
}
