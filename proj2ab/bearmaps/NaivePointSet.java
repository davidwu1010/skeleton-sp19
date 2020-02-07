package bearmaps;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NaivePointSet implements PointSet{
    private List<Point> points;

    public NaivePointSet(List<Point> points) {
        this.points = new ArrayList<>(points);
    }

    @Override
    public Point nearest(double x, double y) {
        Point target = new Point(x, y);
        Comparator<Point> comp = Comparator.comparing(point -> Point.distance(point, target));
        return points.stream().min(comp).get();
    }
}
