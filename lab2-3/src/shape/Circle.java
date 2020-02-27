package shape;

public class Circle implements Shape{
    private double radius;

    public Circle(double radius) throws CircleException {
        if (radius <= 0) {
            throw new CircleException("Invalid radius!");
        }
        else {
            this.radius = radius;
        }
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String getName() {
        return "Circle";
    }
    @Override
    public double perimeter() {
        return (double)(2*Math.PI*getRadius());
    }
    @Override
    public String toString() {
        return String.format("Circle {r=%.1f} perimeter = %.4f", getRadius(), perimeter());
    }
}
