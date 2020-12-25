package FuzzyLogic;

public abstract class Set {
    private String name;

    public Set(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public abstract double find(double value);
    public abstract double getCentroid();
}
