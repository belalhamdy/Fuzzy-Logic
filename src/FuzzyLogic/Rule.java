package FuzzyLogic;

import java.util.List;

public class Rule {
    private enum Operator {
        AND, OR
    }

    private List<VariableInstance> variableInstances;
    private List<Operator> operators;
    private Set result;

    public Rule(List<VariableInstance> variableInstances, List<Operator> operators, Set result) throws Exception {
        // for every rule we should have n sets and n-1 operators
        if (variableInstances.size() - 1 != operators.size())
            throw new Exception("Cannot define this rule as number of values = " + variableInstances.size() +
                    " and number of operators = " + operators.size());

        this.variableInstances = variableInstances;
        this.operators = operators;
        this.result = result;
    }

    //returns inference
    public double applyRule(List<Double> variableInputs) throws Exception {
        if (variableInputs.size() != variableInstances.size())
            throw new Exception("Number of values given = " + variableInputs.size() +
                    "must match number of variables = " + variableInstances.size());

        double inferenceValue = variableInstances.get(0).getValue(variableInputs.get(0));
        for (int i = 1; i <= operators.size(); i++) {
            double current = variableInstances.get(i).getValue(variableInputs.get(i));
            inferenceValue = infer(inferenceValue, current, operators.get(i - 1));
        }
        return inferenceValue;
    }

    private double infer(double firstValue, double secondValue, Operator operator) throws Exception {
        if (operator == Operator.AND) return Math.min(firstValue, secondValue);
        else if (operator == Operator.OR) return Math.max(firstValue, secondValue);
        else throw new Exception("Unsupported operator or maybe its null");
    }

    public double getCentroid(){
        return result.getCentroid();
    }
}
