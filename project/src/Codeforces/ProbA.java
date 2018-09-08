package Codeforces;
/**
 * @copyright ankurverma1994
 */

import java.io.*;
import java.util.*;

public class ProbA {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double inf = 1e18;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int ax = ii(), ay = ii(), bx = ii(), by = ii(), rx = ii(), ry = ii();
        Point a = new Point(ax, ay);
        Point b = new Point(bx, by);
        Point r = new Point(rx, ry);
        int n = ii();
        Point bott[] = new Point[n];
        for (int i = 0; i < n; i++) {
            bott[i] = new Point(ii(), ii());
        }
        double sum = 0;
        double RB[] = new double[n];
        for (int i = 0; i < n; i++) {
            RB[i] = dist(r, bott[i]);
            sum += RB[i];
        }
        double ans = inf;
        double sum1 = inf;
        int id = -1;
        // pehle a ko le l
        for (int i = 0; i < n; i++) {
            double temp = 2 * sum + dist(a, bott[i]) + dist(bott[i], r) - 2 * RB[i];
            if (temp < sum1) {
                sum1 = temp;
                id = i;
            }
        }
        double ss = inf;
        ans = Math.min(ans, sum1);
        // baccha hua sum1 hai not 2*sum
        for (int i = 0; i < n; i++) {
            double temp = sum1 + dist(b, bott[i]) + dist(bott[i], r) - 2 * RB[i];
            if (temp < ss && id != i) {
                ss = temp;
            }
        }
        ans = Math.min(ans, ss);
        //pehle b ko le l
        sum1 = inf;
        id = -1;
        for (int i = 0; i < n; i++) {
            double temp = 2 * sum + dist(b, bott[i]) + dist(bott[i], r) - 2 * RB[i];
            if (temp < sum1) {
                sum1 = temp;
                id = i;
            }
        }
        ans = Math.min(ans, sum1);
        ss = inf;
        for (int i = 0; i < n; i++) {
            double temp = sum1 + dist(a, bott[i]) + dist(bott[i], r) - 2 * RB[i];
            if (temp < ss && id != i) {
                ss = temp;
            }
        }
        ans = Math.min(ans, ss);
        out.printf("%.12f\n", ans);
    }

    class Point {
        public double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public double dist(Point p, Point q) {
        double x = (p.x - q.x) * (p.x - q.x), y = (p.y - q.y) * (p.y - q.y);
        return Math.sqrt(x + y);
    }


    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new ProbA().main1();
    }

    void main1() throws IOException {
        out = new PrintWriter(System.out);
        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
        solve();
        out.flush();
        out.close();
    }

    byte inbuffer[] = new byte[1024];
    int lenbuffer = 0, ptrbuffer = 0;

    int readByte() {
        if (lenbuffer == -1) throw new InputMismatchException();
        if (ptrbuffer >= lenbuffer) {
            ptrbuffer = 0;
            try {
                lenbuffer = obj.read(inbuffer);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
        }
        if (lenbuffer <= 0) return -1;
        return inbuffer[ptrbuffer++];
    }

    int ii() {
        int num = 0, b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }
}