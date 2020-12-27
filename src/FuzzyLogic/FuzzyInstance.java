package FuzzyLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FuzzyInstance {
    // Takes the rules and defuzzifies them
    private final List<Rule> ruleBase;
    private Map<String,Double> variableInputs;

    public FuzzyInstance() {
        this.ruleBase = new ArrayList<>();
        this.variableInputs = new HashMap<>();
    }

    public void assignVariable(String name, double value){
        variableInputs.put(name, value);
    }

    public void setVariableInputs(Map<String, Double> varin){
        variableInputs = varin;
    }

    public void addRule(Rule r){
        ruleBase.add(r);
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

    public void addRules(Rule... rules) {
        for(Rule r : rules){
            addRule(r);
        }
    }
}
