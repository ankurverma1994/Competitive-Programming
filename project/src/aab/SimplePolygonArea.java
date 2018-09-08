package aab;

public class SimplePolygonArea {
    class Point {
        double x, y;

        Point(double a, double b) {
            x = a;
            y = b;
        }
    }

    /*
    Given a simple polygon (that is, without self-intersections, but not necessarily convex)
     given the coordinates of its vertices in order for bypass or anti-clockwise.
      It is required to find its area.
     */
    double area(Point fig[]) {
        double res = 0;
        for (int i = 0; i < fig.length; i++) {
            Point p1 = i > 0 ? fig[i - 1] : fig[fig.length - 1], p2 = fig[i];
            res += ((p1.x - p2.x) * (p1.y + p2.y));
        }
        return Math.abs(res) / 2;
    }
}