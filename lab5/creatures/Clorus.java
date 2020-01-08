package creatures;

import edu.princeton.cs.algs4.StdRandom;
import huglife.*;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Clorus extends Creature {
    private int r;
    private int g;
    private int b;

    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    @Override
    public void move() {
        energy -= 0.03;
    }

    @Override
    public void attack(Creature c) {
        energy += c.energy();
    }

    @Override
    public Clorus replicate() {
        energy /= 2;
        return new Clorus(energy);
    }

    @Override
    public void stay() {
        energy -= 0.01;
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipsNeighbors = new ArrayDeque<>();

        for (Direction d: neighbors.keySet()) {
            if (neighbors.get(d).name().equals("empty")) {
                emptyNeighbors.add(d);
            }
            if (neighbors.get(d).name().equals("plip")) {
                plipsNeighbors.add(d);
            }
        }

        if (emptyNeighbors.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        } else if (!plipsNeighbors.isEmpty()) {
            Direction d = randomDirection(plipsNeighbors);
            return new Action(Action.ActionType.ATTACK, d);
        } else if (energy() >= 1) {
            Direction d = randomDirection(emptyNeighbors);
            return new Action(Action.ActionType.REPLICATE, d);
        } else {
            Direction d = randomDirection(emptyNeighbors);
            return new Action(Action.ActionType.MOVE, d);
        }
    }

    @Override
    public Color color() {
        return new Color(r, g, b);
    }

    private Direction randomDirection(Deque<Direction> deque) {
        int n = StdRandom.uniform(deque.size());
        return deque.toArray(new Direction[0])[n];
    }
}
