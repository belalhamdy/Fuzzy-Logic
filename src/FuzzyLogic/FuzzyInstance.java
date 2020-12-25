package FuzzyLogic;

import java.util.List;
import java.util.Map;

public class FuzzyInstance {
    // Takes the rules and defuzzifies them
    private List<Rule> ruleBase;
    private Map<String,Double> variableInputs;

    public FuzzyInstance(List<Rule> ruleBase, Map<String,Double> inputs) {
        this.ruleBase = ruleBase;
        this.variableInputs = inputs;
    }

    public double solve() throws Exception {
        double weightedInferenceSum = 0, inferenceSum = 0;
        for (Rule currRule : ruleBase) {
            double currInference = currRule.applyRule(variableInputs);
            inferenceSum += currInference;
            weightedInferenceSum += currInference * currRule.getCentroid();
        }
        return weightedInferenceSum / inferenceSum;
    }
}
