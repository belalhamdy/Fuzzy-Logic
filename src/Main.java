import FuzzyLogic.Examples;
import FuzzyLogic.Rule;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String,Double> mp1 = new HashMap<>();
        mp1.put("project_funding",50.0);
        mp1.put("team_experience_level",40.0);
        Examples.runAssignmentExample(mp1);
        System.out.println("------------------------------");
        Map<String,Double> mp2 = new HashMap<>();
        mp2.put("size",2.0);
        mp2.put("weight",2.5);
        Examples.runMidTermExample2019(mp2);
        System.out.println("------------------------------");


    }
}
