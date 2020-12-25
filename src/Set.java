import java.util.ArrayList;
import java.util.List;

public class Set {
    String name;
    List<Integer> values;

    public Set(String name, List<Integer> values) {
        this.name = name;
        this.values = new ArrayList<>(values);
    }

    public String getName() {
        return name;
    }
    public double find(double value){
        double answer = 0.0;
        // TODO: calculate the equation of the set here and substitute by the value and save it in answer
        return answer;
    }
    public double getCentroid(){
        return values.stream().mapToInt(val -> val).average().orElse(0.0);
    }
}
