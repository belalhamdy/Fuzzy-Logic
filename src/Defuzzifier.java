import java.util.List;

public class Defuzzifier {
    // Takes the rules and defuzzifies them
    List<Rule> rules;
    double result;

    public Defuzzifier(List<Rule> rules) {
        this.rules = rules;
        setResultWithWeightedAverageMethod();
    }

    private void setResultWithWeightedAverageMethod() {
        double centroidsByInference = rules.stream().mapToDouble(Rule::getCentroidMultiplyInference).sum();
        double inferenceSum = rules.stream().mapToDouble(Rule::getInferenceValue).sum();
        result = centroidsByInference /inferenceSum;
    }

    public double getResult() {
        return result;
    }
}
