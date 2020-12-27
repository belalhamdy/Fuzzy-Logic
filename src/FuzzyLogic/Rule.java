package FuzzyLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rule {
    public enum Operator {
        AND, OR
    }

    private final List<VariableInstance> variableInstances;
    private final List<Operator> operators;
    private final Set result;

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
    public double applyRule(Map<String, Double> inputs) throws Exception {
        double inferenceValue = 0;
        for (int i = 0; i <= operators.size(); i++) {
            VariableInstance curVar = variableInstances.get(i);
            double current = curVar.getValue(inputs.get(curVar.getName()));
            System.out.printf("%s=%s : %.4f%n", curVar.getName(), curVar.getSetName(), current);
            inferenceValue = infer(inferenceValue, current, i == 0 ? Operator.OR : operators.get(i - 1));
        }
        System.out.printf("mu value for %s: %f%n%n", result.getName(), inferenceValue);
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
