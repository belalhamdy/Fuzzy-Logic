public class SetInstance {
    Set set;
    double value; // value after substituting in the set

    // takes the value before substituting in the set
    public SetInstance(Set set, double value)
    {
        this.set = set;
        this.value = set.find(value);
    }
    public double getValue(){
        return value;
    }
}
