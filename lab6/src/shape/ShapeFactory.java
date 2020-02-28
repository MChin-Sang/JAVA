package shape;

public class ShapeFactory {
    public Shape getShape(String[] tokens) {
        try {
            if (tokens[0].equalsIgnoreCase("SQUARE")) {
                if (tokens.length > 2) {
                    throw new IllegalArgumentException("To may arguments for type 'Square'");
                } else {
                    return new Square(Double.parseDouble(tokens[1]));
                }
            } else if (tokens[0].equalsIgnoreCase("CIRCLE")) {
                if (tokens.length > 2) {
                    throw new IllegalArgumentException("To may arguments for type 'Circle'");
                } else {
                    return new Circle(Double.parseDouble(tokens[1]));
                }
            } else if (tokens[0].equalsIgnoreCase("TRIANGLE")) {
                if (tokens.length > 4) {
                    throw new IllegalArgumentException("To may arguments for type 'Triangle'");
                } else {
                    return new Triangle(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]));
                }
            } else if (tokens[0].equalsIgnoreCase("RECTANGLE")) {
                return new Rectangle(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]));
            } else if (tokens[0].equalsIgnoreCase("Parallelogram")) {
                if (tokens.length > 3) {
                    throw new IllegalArgumentException("To may arguments for type 'Parallelogram'");
                } else {
                    return new Parallelogram(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]));
                }
            } else {
                return null;
            }
        }
        catch (SquareException | RectangleException | CircleException | TriangleException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
