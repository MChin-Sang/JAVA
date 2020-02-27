package shape;

public class Triangle implements Shape{
    //Fields
    //
    private double sideA, sideB, sideC;

    // Constructors
    //
    public Triangle(double sideA, double sideB, double sideC) throws TriangleException{
        if (sideA <= 0 || sideB <= 0 || sideC <= 0 || (sideA+sideB) < sideC)
            throw new TriangleException("Invalid side(s)!");
        else
            this.sideA = sideA;
            this.sideB = sideB;
            this.sideC = sideC;
    }

    // Overridden methods
    //
    @Override
    public double perimeter() { return getSideA() + getSideB() + getSideC(); }

    @Override
    public String toString() {
        return String.format("Triangle {s1=%.1f, s2=%.1f, s3=%.1f} perimeter = %.4f", getSideA(), getSideB(),getSideC(), perimeter());
    }

    @Override
    public String getName() {
        return "Triangle";
    }

    // public methods
    //
    public double getSideA() { return sideA; }
    public double getSideB() { return sideB; }
    public double getSideC() { return sideC; }
}
