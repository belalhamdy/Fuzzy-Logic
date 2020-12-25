package FuzzyLogic;

import java.util.List;

public class FuzzyInstance {
    // Takes the rules and defuzzifies them
    private List<Rule> ruleBase;
    private List<Double> variableInputs;

    public FuzzyInstance(List<Rule> ruleBase, List<Double> variableInputs) {
        this.ruleBase = ruleBase;
        this.variableInputs = variableInputs;
    }

    public double defuzzify() throws Exception {
        double weightedInferenceSum = 0, inferenceSum = 0;
        for (Rule curRule : ruleBase) {
            double curInference = curRule.applyRule(variableInputs);
            inferenceSum += curInference;
            weightedInferenceSum += curInference * curRule.getCentroid();
        }
        return weightedInferenceSum / inferenceSum;
    }
}
