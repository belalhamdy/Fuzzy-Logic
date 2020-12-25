import FuzzyLogic.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            Variable projectFunding = new Variable("project_funding");
            projectFunding.addSet(new TrapezoidSet("very low", 0, 0, 10, 30));
            projectFunding.addSet(new TrapezoidSet("low", 10, 30, 40, 60));
            projectFunding.addSet(new TrapezoidSet("medium", 40, 60, 70, 90));
            projectFunding.addSet(new TrapezoidSet("high", 70, 90, 100, 100));

            Variable teamExperienceLevel = new Variable("team_experience_level");
            teamExperienceLevel.addSet(new TriangleSet("beginner", 0, 15, 30));
            teamExperienceLevel.addSet(new TriangleSet("intermediate", 15, 30, 45));
            teamExperienceLevel.addSet(new TriangleSet("expert", 30, 60, 60));

            Variable risk = new Variable("risk");
            risk.addSet(new TriangleSet("high", 0, 25, 50));
            risk.addSet(new TriangleSet("normal", 25, 50, 75));
            risk.addSet(new TriangleSet("low", 50, 100, 100));

            Rule r0 = new Rule(new VariableInstance(projectFunding,"high"),risk.getSetByName("low"));
            r0.addStatement(Rule.Operator.OR,new VariableInstance(teamExperienceLevel,"expert"));


            Rule r1 = new Rule(new VariableInstance(projectFunding,"medium"),risk.getSetByName("normal"));
            r1.addStatement(Rule.Operator.AND,new VariableInstance(teamExperienceLevel,"intermediate"));
            r1.addStatement(Rule.Operator.OR,new VariableInstance(teamExperienceLevel,"beginner"));

            Rule r2 = new Rule(new VariableInstance(projectFunding,"very low"),risk.getSetByName("high"));

            Rule r3 = new Rule(new VariableInstance(projectFunding,"low"),risk.getSetByName("high"));
            r3.addStatement(Rule.Operator.AND,new VariableInstance(teamExperienceLevel,"beginner"));

            List<Rule> rules = new ArrayList<>();
            rules.add(r0);
            rules.add(r1);
            rules.add(r2);
            rules.add(r3);

            Map<String,Double> mp = new HashMap<>();
            mp.put("project_funding",50.0);
            mp.put("team_experience_level",40.0);
            FuzzyInstance fuzzyInstance = new FuzzyInstance(rules,mp);

            double ans = fuzzyInstance.solve();
            System.out.println(ans);
            System.out.println(risk.defuzzify(ans).getName());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
}
