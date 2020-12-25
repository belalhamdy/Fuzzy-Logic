import FuzzyLogic.*;

import java.util.ArrayList;
import java.util.List;

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

            //Rule r1 = new Rule()

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
