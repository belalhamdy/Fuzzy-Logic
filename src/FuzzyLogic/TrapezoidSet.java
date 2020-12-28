package FuzzyLogic;

public class TrapezoidSet extends Set {
    protected int low, mid1, mid2, high;

    public TrapezoidSet(String name, int a, int b, int c, int d) {
        super(name);
        low = a;
        mid1 = b;
        mid2 = c;
        high = d;
    }

    //Assuming a line goes through points (low, 0), (mid1, 1)
    //Slope = 1/(mid1-low)
    //Y-y1=Slope*(X-x1)
    //Y=(X-low)/(mid1-low)
    //Similar equation can be deduced for the opposite line.
    @Override
    public double find(double value) {
        if (value <= low) return low == mid1 ? 1.0 : 0.0;
        if (value >= high) return mid2 == high ? 1.0 : 0.0;
        if (value >= mid1 && value <= mid2) return 1.0;
        if (value <= mid1)
            return (value - low) / (mid1 - low);
        else
            return (high - value) / (high - mid2);
    }

    @Override
    public double getCentroid() {
        return (low + mid1 + mid2 + high) / 4.0;
    }
}
