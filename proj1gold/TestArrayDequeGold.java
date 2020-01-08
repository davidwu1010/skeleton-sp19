import org.junit.Test;

import java.util.StringJoiner;

import static org.junit.Assert.*;

public class TestArrayDequeGold {
    @Test
    public void test() {
        StringJoiner sj = new StringJoiner("\n", "\n", "");
        ArrayDequeSolution<Integer> solutionDeque = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> studentDeque = new StudentArrayDeque<>();

        while (true) {
            int operation = StdRandom.uniform(4);
            if (operation == 0) {
                int num = StdRandom.uniform(100);
                sj.add(String.format("addFirst(%d)", num));
                solutionDeque.addFirst(num);
                studentDeque.addFirst(num);
            } else if (operation == 1) {
                int num = StdRandom.uniform(100);
                sj.add(String.format("addLast(%d)", num));
                solutionDeque.addLast(num);
                studentDeque.addLast(num);
            } else if (operation == 2) {
                if (solutionDeque.isEmpty() || studentDeque.isEmpty()) {
                    continue;
                }
                sj.add("removeFirst()");
                Integer expected = solutionDeque.removeFirst();
                Integer actual = studentDeque.removeFirst();
                assertEquals(sj.toString(), expected, actual);
            } else if (operation == 3){
                if (solutionDeque.isEmpty() || studentDeque.isEmpty()) {
                    continue;
                }
                sj.add("removeLast()");
                Integer expected = solutionDeque.removeLast();
                Integer actual = studentDeque.removeLast();
                assertEquals(sj.toString(), expected, actual);
            }
        }
    }
}
