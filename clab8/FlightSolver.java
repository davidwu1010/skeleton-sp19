import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {
    private PriorityQueue<Flight> waiting;
    private PriorityQueue<Flight> flying;

    public FlightSolver(ArrayList<Flight> flights) {
        waiting = new PriorityQueue<>(flights.size(), Comparator.comparingInt(Flight::startTime));
        waiting.addAll(flights);
        flying = new PriorityQueue<>(Comparator.comparingInt(Flight::endTime));
    }

    public int solve() {
        int max = 0;

        while (!waiting.isEmpty()) {
            if (flying.isEmpty() || waiting.peek().startTime <= flying.peek().endTime) {
                flying.add(waiting.poll());
            } else {
                flying.poll();
            }

            int sum = flying.stream().mapToInt(Flight::passengers).sum();
            if (sum > max) {
                max = sum;
            }
        }

        return max;
    }

}
