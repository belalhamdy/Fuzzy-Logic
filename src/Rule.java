import java.util.List;

public class Rule {
    public enum Operator {
        AND, OR
    }

    List<SetInstance> setInstances;
    List<Operator> operators;
    Set result;
    double inferenceValue;

    public Rule(List<SetInstance> setInstances, List<Operator> operators, Set result) throws Exception {
        if (setInstances.size() - 1 != operators.size()) // for every rule we should have n sets and n-1 operators
            throw new Exception("Cannot define this rule as number of values = " + setInstances.size() +
                    " and number of operators = " + operators.size());

        this.setInstances = setInstances;
        this.operators = operators;
        this.result = result;
        setInferenceValue();
    }

    void setInferenceValue() throws Exception {
        inferenceValue = setInstances.get(0).getValue();
        for (int i = 0; i < operators.size(); ++i) {
            inferenceValue = infer(inferenceValue, setInstances.get(i + 1).getValue(), operators.get(i));
        }
    }

    double infer(double firstValue, double secondValue, Operator operator) throws Exception {
        if (operator == Operator.AND) return Math.min(firstValue, secondValue);
        else if (operator == Operator.OR) return Math.max(firstValue, secondValue);
        else throw new Exception("Unsupported operator or maybe its null");
    }

    public double getInferenceValue() {
        return inferenceValue;
    }

    public double getCentroidMultiplyInference() {
        return result.getCentroid() * inferenceValue;
    }

}
