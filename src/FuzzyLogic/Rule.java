package FuzzyLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Rule {
    public enum Operator {
        AND, OR
    }

    private List<VariableInstance> variableInstances;
    private List<Operator> operators;
    private Set result;

    public Rule(VariableInstance firstVariableInstance, Set result) {
        this.variableInstances = new ArrayList<>();
        variableInstances.add(firstVariableInstance);
        this.operators = new ArrayList<>();
        this.result = result;
    }

    public void addStatement(Operator op, VariableInstance instance) {
        operators.add(op);
        variableInstances.add(instance);
    }
    //returns inference
    public double applyRule(Map<String,Double> inputs) throws Exception {
        VariableInstance currVariable = variableInstances.get(0);
        double inferenceValue = currVariable.getValue(inputs.get(currVariable.getName()));
        for (int i = 1; i <= operators.size(); i++) {
            currVariable = variableInstances.get(i);
            double current = currVariable.getValue(inputs.get(currVariable.getName()));
            inferenceValue = infer(inferenceValue, current, operators.get(i - 1));
        }
        return inferenceValue;
    }

    private double infer(double firstValue, double secondValue, Operator operator) throws Exception {
        if (operator == Operator.AND) return Math.min(firstValue, secondValue);
        else if (operator == Operator.OR) return Math.max(firstValue, secondValue);
        else throw new Exception("Unsupported operator or maybe its null");
    }

    public double getCentroid() {
        return result.getCentroid();
    }
}
