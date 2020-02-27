package shape;

public class Square implements Shape {
    //Fields
    //
    private double side;

    // Constructors
    //
    public Square(double side) throws SquareException{
        if (side <= 0)
            throw new SquareException("Invalid side!");
        else
            this.side = side;
    }

    // Overridden methods
    //
    @Override
    public String getName() {
        return "Square";
    }
    @Override
    public double perimeter() { return getSide() * 4; }

    @Override
    public String toString() {
        return String.format("Square {s=%.1f} perimeter = %.4f", getSide(), perimeter());
    }

    // public methods
    //
    public double getSide() { return side; }

    // main
    //
    public static void main(String[] args) {
        try {
            Shape shape1 = new Square(-4);
            System.out.println(shape1.toString());
        }
        catch(SquareException err) {
            System.out.println(err.getMessage());
        }
    }
}
