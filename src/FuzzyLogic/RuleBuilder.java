package FuzzyLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RuleBuilder {
    private final Map<String, Variable> stringToVar;
    private final Map<String, Set> stringToSet;
    RuleBuilder(){
        stringToSet = new HashMap<>();
        stringToVar = new HashMap<>();
    }
    void addSets(Set...sets){
        for (Set s: sets) {
            stringToSet.put(s.getName(), s);
        }
    }
    void addVariables(Variable...sets){
        for (Variable s: sets) {
            stringToVar.put(s.getName(), s);
        }
    }
    Rule getRule(String text) throws Exception {
        //String pat = "^\\s*([a-zA-Z_]+)\\s*=\\s*([a-zA-Z_]+)(?:\\s+(or|and)\\s+([a-zA-Z_]+)\\s*=\\s*([a-zA-Z_]+))*\\s+(then)\\s+([a-zA-Z_]+)\\s*=\\s*([a-zA-Z_]+)";
        String pat = "^\\s*(\\w+)\\s*=\\s*(\\w+)(?:\\s+(or|and)\\s+(\\w+)\\s*=\\s*(\\w+))*\\s+(then)\\s+(\\w+)\\s*=\\s*(\\w+)";
        Pattern p = Pattern.compile(pat);
        Matcher m = p.matcher(text);
        if (!m.matches()) return null;
        List<String> matches = new ArrayList<>();
        for (int i = 1; i <= m.groupCount(); i++) {
            if (m.group(i) == null) continue;
            matches.add(m.group(i));
        }
        VariableInstance vi = new VariableInstance(stringToVar.get(matches.get(0)), stringToSet.get(matches.get(1)));
        Rule r = new Rule(vi, stringToSet.get(m.group(m.groupCount())));
        for (int i = 2; ; i+=3) {
            if(matches.get(i).equals("then")) break;
            vi = new VariableInstance(stringToVar.get(matches.get(i + 1)), stringToSet.get(matches.get(i + 2)));
            r.addStatement(matches.get(i).equals("and") ? Rule.Operator.AND : Rule.Operator.OR, vi);
        }
        return r;
    }
}
