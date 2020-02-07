package bearmaps;

import java.util.List;

public class KDTree {
    private static class Node {
        private Point point;
        Node left;
        Node right;
        boolean isXNode;

        private Node(Point p, boolean isXNode) {
            point = p;
            this.left = null;
            this.right = null;
            this.isXNode = isXNode;
        }

        private double distance(Point target) {
            return Point.distance(point, target);
        }
    }

    private Node root;

    public KDTree(List<Point> points) {
        for (Point p: points) {
            insert(root, p, true);
        }
    }

    public Point nearest(double x, double y) {
        return nearest(root, new Point(x, y), root).point;
    }

    private Node nearest(Node node, Point target, Node best) {
        if (node == null) {
            return best;
        }
        if (node.distance(target) < best.distance(target)) {
            best = node;
        }
        Node goodSide = null;
        Node badSide = null;
        if ((node.isXNode && target.getX() < node.point.getX())
                || (!node.isXNode && target.getY() < node.point.getY())) {
            goodSide = node.left;
            badSide = node.right;
        } else {
            goodSide = node.right;
            badSide = node.left;
        }

        best = nearest(goodSide, target, best);
        double bestDistance = best.distance(target);
        if ((node.isXNode && Double.compare(Math.abs(node.point.getX() - target.getX()), bestDistance) < 0)
                || (!node.isXNode && Double.compare(Math.abs(node.point.getY() - target.getY()), bestDistance) < 0)) {
            best = nearest(badSide, target, best);
        }

        return best;
    }

    private Node insert(Node node, Point p, boolean isXNode) {
        if (root == null) {
            root = new Node(p, true);
            return root;
        }

        if (node == null) {
            return new Node(p, isXNode);
        } else if (isXNode) {
            if (p.getX() < node.point.getX()) {
                node.left = insert(node.left, p, false);
            } else {
                node.right = insert(node.right, p, false);
            }
        } else {
            if (p.getY() < node.point.getY()) {
                node.left = insert(node.left, p, true);
            } else {
                node.right = insert(node.right, p, true);
            }
        }
        return node;
    }
}
