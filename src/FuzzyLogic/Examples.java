package FuzzyLogic;

import java.util.Map;

public class Examples {
    public static void runAssignmentExample(Map<String, Double> mp) {
        System.out.println("Assignment Example:\n");
        try {
            Variable projectFunding = new Variable("project_funding");
            Set verylow = new TrapezoidSet("very_low", 0, 0, 10, 30);
            Set low = new TrapezoidSet("low", 10, 30, 40, 60);
            Set medium = new TrapezoidSet("medium", 40, 60, 70, 90);
            Set high = new TrapezoidSet("high", 70, 90, 100, 100);

            projectFunding.addSets(verylow, low, medium, high);

            Variable teamExperienceLevel = new Variable("team_experience_level");
            Set beginner = new TriangleSet("beginner", 0, 15, 30);
            Set intermediate = new TriangleSet("intermediate", 15, 30, 45);
            Set expert = new TriangleSet("expert", 30, 60, 60);

            teamExperienceLevel.addSets(beginner, intermediate, expert);

            Variable risk = new Variable("risk");
            Set risk_high = new TriangleSet("risk_high", 0, 25, 50);
            Set risk_normal = new TriangleSet("risk_normal", 25, 50, 75);
            Set risk_low = new TriangleSet("risk_low", 50, 100, 100);
            risk.addSets(risk_high, risk_normal, risk_low);

//            Rule r0 = new Rule(new VariableInstance(projectFunding, high), risk_low);
//            r0.addStatement(Rule.Operator.OR, new VariableInstance(teamExperienceLevel, expert));
//
//            Rule r1 = new Rule(new VariableInstance(projectFunding, medium), risk_normal);
//            r1.addStatement(Rule.Operator.AND, new VariableInstance(teamExperienceLevel, intermediate));
//            r1.addStatement(Rule.Operator.OR, new VariableInstance(teamExperienceLevel, beginner));
//
//            Rule r2 = new Rule(new VariableInstance(projectFunding, verylow), risk_high);
//
//            Rule r3 = new Rule(new VariableInstance(projectFunding, low), risk_high);
//            r3.addStatement(Rule.Operator.AND, new VariableInstance(teamExperienceLevel, beginner));

            RuleBuilder ruleBuilder = new RuleBuilder();
            ruleBuilder.addVariables(projectFunding, teamExperienceLevel, risk);
            ruleBuilder.addSets(verylow, low, medium, high, beginner, intermediate, expert, risk_high, risk_normal, risk_low);

            FuzzyInstance fuzzyInstance = new FuzzyInstance();
            fuzzyInstance.addRules(
                    ruleBuilder.getRule("project_funding=high or team_experience_level=expert then risk=risk_low"),
                    ruleBuilder.getRule("project_funding=medium and team_experience_level=intermediate or team_experience_level=beginner then risk=risk_normal"),
                    ruleBuilder.getRule("project_funding=very_low then risk=risk_high"),
                    ruleBuilder.getRule("project_funding=low and team_experience_level=beginner then risk=risk_high")
            );

            fuzzyInstance.setVariableInputs(mp);

            double ans = fuzzyInstance.solve();
            System.out.println(ans);
            System.out.println(risk.defuzzify(ans).getName());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void runMidTermExample2019(Map<String, Double> mp) {
        System.out.println("Midterm 2019 Example:\n");
        try {
            Set small = new TriangleSet("small", 0, 0, 10);
            Set large = new TriangleSet("large", 0, 10, 10);

            Variable size = new Variable("size");
            Variable weight = new Variable("weight");
            size.addSets(small, large);
            weight.addSets(small, large);

            Variable quality = new Variable("quality");
            Set bad = new TriangleSet("bad", 0, 0, 50);
            Set medium = new TriangleSet("medium", 0, 50, 100);
            Set good = new TriangleSet("good", 50, 100, 100);
            quality.addSets(bad, medium, good);

//            Rule r0 = new Rule(new VariableInstance(size, small), bad);
//            r0.addStatement(Rule.Operator.AND, new VariableInstance(weight, small));
//
//            Rule r1 = new Rule(new VariableInstance(size, small), medium);
//            r1.addStatement(Rule.Operator.AND, new VariableInstance(weight, large));
//
//            Rule r2 = new Rule(new VariableInstance(size, large), medium);
//            r2.addStatement(Rule.Operator.AND, new VariableInstance(weight, small));
//
//            Rule r3 = new Rule(new VariableInstance(size, large), good);
//            r3.addStatement(Rule.Operator.AND, new VariableInstance(weight, large));

            RuleBuilder ruleBuilder = new RuleBuilder();
            ruleBuilder.addSets(small, large, bad, medium, good);
            ruleBuilder.addVariables(size, weight, quality);

            FuzzyInstance fuzzyInstance = new FuzzyInstance();
            fuzzyInstance.addRules(
                    ruleBuilder.getRule("size=small and weight=small then quality=bad"),
                    ruleBuilder.getRule("size=small and weight=large then quality=medium"),
                    ruleBuilder.getRule("size=large and weight=small then quality=medium"),
                    ruleBuilder.getRule("size=large and weight=large then quality=good")
            );

            fuzzyInstance.setVariableInputs(mp);

            double ans = fuzzyInstance.solve();
            System.out.println(ans);
            System.out.println(quality.defuzzify(ans).getName());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
