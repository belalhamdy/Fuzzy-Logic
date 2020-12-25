import java.util.List;

public class Defuzzifier {
    // Takes the rules and defuzzifies them
    List<RuleBases> ruleBases;
    double result;

    public Defuzzifier(List<RuleBases> ruleBases) {
        this.ruleBases = ruleBases;
        setResultWithWeightedAverageMethod();
    }

    private void setResultWithWeightedAverageMethod() {
        double centroidsByInference = ruleBases.stream().mapToDouble(RuleBases::getCentroidMultiplyInference).sum();
        double inferenceSum = ruleBases.stream().mapToDouble(RuleBases::getInferenceValue).sum();
        result = centroidsByInference / inferenceSum;
    }

    public double getResult() {
        return result;
    }
}
