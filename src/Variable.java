import java.util.ArrayList;
import java.util.List;

public class Variable {
    String name;
    List<Set> sets;

    public Variable(String name, List<Set> sets){
        this.name = name;
        this.sets = new ArrayList<>(sets);
    }
    public Variable(String name){
        this(name,new ArrayList<>());
    }
    public void addSet(Set set) throws Exception {
        try{
            getSetByName(set.getName());
        }catch (Exception e){
            throw new Exception("Error another set has the same " + set.name + " in the same variable " + this.name);
        }
        sets.add(set);
    }
    public Set getSetByName(String name) throws Exception {
        Set found = sets.stream().filter(set -> name.equals(set.getName())).findAny().orElse(null);
        if(found == null)
            throw new Exception("Cannot find the set " + name + " in the variable" + this.name);
        return found;
    }
}
