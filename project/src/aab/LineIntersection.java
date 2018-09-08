package aab;

public class LineIntersection {
    class Point {
        double x, y;

        Point(double a, double b) {
            x = a;
            y = b;
        }
    }

    class Line {
        double a, b, c;

        // ax + by + c = 0;
        Line(double x, double y, double z) {
            a = x;
            b = y;
            c = z;
        }
    }

    double EPS = 1e-9;

    double det(double a, double b, double c, double d) {
        return a * d - b * c;
    }

    Point res; //intersection point

    boolean intersect(Line m, Line n) {
        double zn = det(m.a, m.b, n.a, n.b);
        if (Math.abs(zn) < EPS)
            return false;
        double x = -det(m.c, m.b, n.c, n.b) / zn;
        double y = -det(m.a, m.c, n.a, n.c) / zn;
        res = new Point(x, y);
        return true;
    }

    boolean parallel(Line m, Line n) {
        return Math.abs(det(m.a, m.b, n.a, n.b)) < EPS;
    }

    boolean equivalent(Line m, Line n) {
        return Math.abs(det(m.a, m.b, n.a, n.b)) < EPS
                && Math.abs(det(m.a, m.c, n.a, n.c)) < EPS
                && Math.abs(det(m.b, m.c, n.b, n.c)) < EPS;
    }
}