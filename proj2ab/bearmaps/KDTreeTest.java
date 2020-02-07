package bearmaps;

import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    @Test
    public void basicTest() {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        KDTree tree = new KDTree(List.of(p1, p2, p3));
        Point ret = tree.nearest(3.0, 4.0); // returns p2
        assertEquals(p2, ret);
        ret.getX(); // evaluates to 3.3
        assertEquals(3.3, ret.getX(), 0.001);
        ret.getY(); // evaluates to 4.4
        assertEquals(4.4, ret.getY(), 0.001);
    }

    @Test
    public void randomTest() {
        HashSet<Point> points = new HashSet<>();
        int n = 10000;
        Random random = new Random();
        for (int i = 0; i < n; ++i) {
            double x = random.nextDouble() * 10000;
            double y = random.nextDouble() * 10000;
            points.add(new Point(x, y));
        }
        var list = List.of(points.toArray(new Point[0]));
        NaivePointSet nps = new NaivePointSet(list);
        KDTree kdTree = new KDTree(list);

        for (int i = 0; i < n; ++i) {
            double x = random.nextDouble() * 10000;
            double y = random.nextDouble() * 10000;
            assertEquals(nps.nearest(x, y), kdTree.nearest(x, y));
        }
    }
}
