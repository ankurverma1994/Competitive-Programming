package aaa;

import java.util.Arrays;

class Geometry {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;

    public static void main(String[] args) {
        int a= 0x30;
    }

    // compute square of distance between two points
    double dist2(int x1, int y1, int x2, int y2) {
        return (1L * (x2 - x1) * (x2 - x1) + 1L * (y2 - y1) * (y2 - y1));
    }

    // tell triangle is isosceles, equilateral or scalene triangle.
    // @param square of length of 3 sides
    String triangleType1(double d1, double d2, double d3) {
        if (Math.abs(d1 - d2) < eps && Math.abs(d1 - d3) < eps)
            return "equilateral";
        if (Math.abs(d1 - d3) < eps)
            return "isosceles";
        if (Math.abs(d1 - d2) < eps)
            return "isosceles";
        if (Math.abs(d2 - d3) < eps)
            return "isosceles";
        return "scalene";
    }

    // tell triangle is acute, obtuse or right angled
    // @param squares of length of side of triangle
    String triangleType2(double d1, double d2, double d3) {
        double max = Math.max(Math.max(d1, d2), d3);
        if (Math.abs(max - (d1 + d2 + d3 - max)) < eps)
            return "right";
        if (max > (d1 + d2 + d3 - max))
            return "obtuse";
        return "acute";
    }
}

class Polygon {

    static final double EPS = 1e-15;
    public int n;
    public Point p[];

    Polygon(int n, Point x[]) {
        this.n = n;
        p = Arrays.copyOf(x, n);
    }

    long area() { //returns 2 * area
        long ans = 0;
        for (int i = 1; i + 1 < n; i++)
            ans += p[i].minus(p[0]).cross(p[i + 1].minus(p[0]));
        return ans;
    }

    boolean PointInPolygon(Point q) {
        boolean c = false;
        for (int i = 0; i < n; i++) {
            int j = (i + 1) % n;
            if ((p[i].y <= q.y && q.y < p[j].y ||
                    p[j].y <= q.y && q.y < p[i].y) &&
                    q.x < p[i].x + (p[j].x - p[i].x) * (q.y - p[i].y) / (p[j].y - p[i].y))
                c = !c;
        }
        return c;
    }

    // determine if point is on the boundary of a polygon
    boolean PointOnPolygon(Point q) {
        for (int i = 0; i < n; i++)
            if (ProjectPointSegment(p[i], p[(i + 1) % n], q).dist(q) < EPS)
                return true;
        return false;
    }

    // project point c onto line segment through a and b
    Point ProjectPointSegment(Point a, Point b, Point c) {
        double r = b.minus(a).dot(b.minus(a));
        if (Math.abs(r) < EPS) return a;
        r = c.minus(a).dot(b.minus(a)) / r;
        if (r < 0) return a;
        if (r > 1) return b;
        return a.plus(b.minus(a).mul(r));
    }
}

class Point {
    public double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point minus(Point b) {
        return new Point(x - b.x, y - b.y);
    }

    public Point plus(Point a) {
        return new Point(x + a.x, y + a.y);
    }

    public double cross(Point b) {
        return (double) x * b.y - (double) y * b.x;
    }

    public double dot(Point b) {
        return (double) x * b.x + (double) y * b.y;
    }

    public Point mul(double r) {
        return new Point(x * r, y * r);
    }

    public double dist(Point p) {
        return Math.sqrt(fastHypt(x - p.x, y - p.y));
    }

    public double fastHypt(double x, double y) {
        return x * x + y * y;
    }
}