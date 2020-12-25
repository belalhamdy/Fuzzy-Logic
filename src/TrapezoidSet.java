public class TrapezoidSet extends Set {
    protected int low, mid1, mid2, high;

    public TrapezoidSet(String name, int a, int b, int c, int d) {
        super(name);
        low = a;
        mid1 = b;
        mid2 = c;
        high = d;
    }

    @Override
    public double find(double value) {
        if (value <= low) return low == mid1 ? 1.0 : 0.0;
        if (value >= high) return mid2 == high ? 1.0 : 0.0;
        if (value >= mid1 && value <= mid2) return 1.0;
        if (value <= mid1)
            return (value - low) / (mid1 - low);
        else
            return (value - mid2) / (high - mid2);
    }

    @Override
    public double getCentroid() {
        return (low + mid1 + mid2 + high) / 4.0;
    }
}
