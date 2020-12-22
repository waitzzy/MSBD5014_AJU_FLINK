package Q4;

import java.util.ArrayList;

public class Q4Result {
    public ArrayList<Q4SelectResultTuple> result;

    public Q4Result(ArrayList<Q4SelectResultTuple> result) {
        this.result = result;
    }

    public Q4Result() {
        this.result = new ArrayList<Q4SelectResultTuple>();
    }

    public boolean addTuple(Q4SelectResultTuple t) {
        return result.add(t);
    }

    @Override
    public String toString() {
        return "Q4SelectResult{" +
                "result=" + result +
                '}';
    }
}
