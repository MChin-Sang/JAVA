/**
 * extends rectangle and overides getName and toString
 */

package shape;

public class Parallelogram extends Rectangle {
    public Parallelogram(double width, double length) throws RectangleException {
        super(width, length);
    }
    @Override
    public String getName() {
        return "Parallelogram";
        // getClass().getSimpleName();
    }
    @Override
    public String toString() {
        return String.format("Parallelogram {w=%.1f, h=%.1f} perimeter = %.4f", getWidth(), getLength(), perimeter());
    }


}