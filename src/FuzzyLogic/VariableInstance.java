package FuzzyLogic;

public class VariableInstance {
    private Set selected;
    private Variable variable;

    // takes the value before substituting in the set
    public VariableInstance(Variable var, String value) throws Exception {
        this.variable = var;
        this.selected = var.getSetByName(value);
    }
    public double getValue(double variableInput){
        return selected.find(variableInput);
    }
    public String getName(){
        return variable.getName();
    }
}
