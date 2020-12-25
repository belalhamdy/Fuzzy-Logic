package FuzzyLogic;

public class TriangleSet extends TrapezoidSet {
    public TriangleSet(String name, int a, int b, int c) {
        super(name, a, b, b, c);
    }

    @Override
    public double getCentroid() {
        return (low + mid1 + high) / 3.0;
    }
}
