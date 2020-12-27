package FuzzyLogic;

public class VariableInstance {
    private final Set selected;
    private final Variable variable;

    // takes the value before substituting in the set
    public VariableInstance(Variable var, Set selected) throws Exception {
        this.variable = var;
        if(!variable.hasSet(selected)) throw new Exception("Set " + selected.getName() + " is not part of the variable " + var.getName());
        this.selected = selected;
    }
    public double getValue(double variableInput){
        return selected.find(variableInput);
    }
    public String getName(){
        return variable.getName();
    }

    public String getSetName() {
        return selected.getName();
    }
}
