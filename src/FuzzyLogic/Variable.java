package FuzzyLogic;

import java.util.ArrayList;
import java.util.List;

public class Variable {
    String name;
    List<Set> sets;

    public Variable(String name) {
        this.name = name;
        this.sets = new ArrayList<>();
    }

    public void addSet(Set set) throws Exception {
        if (sets.stream().anyMatch(s -> s.getName().equals(set.getName())))
            throw new Exception("Error another set has the same " + set.getName() + " in the same variable " + this.name);
        sets.add(set);
    }

    public Set getSetByName(String name) throws Exception {
        Set found = sets.stream().filter(set -> name.equals(set.getName())).findAny().orElse(null);
        if (found == null)
            throw new Exception("Cannot find the set " + name + " in the variable" + this.name);
        return found;
    }

    public String getName() {
        return name;
    }

    public Set defuzzify(double value) {
        Set ret = null;
        double max = -1;
        for (Set currSet : sets) {
            double currVal = currSet.find(value);
            if (currVal - max > 1e-5) {
//                System.out.println(max + " -> " + currVal);
                max = currVal;
                ret = currSet;
            }
        }
        return ret;
    }
}
