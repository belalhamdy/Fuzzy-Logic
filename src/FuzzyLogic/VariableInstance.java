package FuzzyLogic;

public class VariableInstance {
    private Set selected;

    // takes the value before substituting in the set
    public VariableInstance(Variable var, String value) throws Exception {
        this.selected = var.getSetByName(value);
    }
    public double getValue(double variableInput){
        return selected.find(variableInput);
    }
}
