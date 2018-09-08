package CodesprintSEPT;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class ProbB {
    BufferedReader obj;
    PrintWriter out;

    public static void main(String[] args) throws IOException {
        new ProbB().solve();
    }

    void solve() throws IOException {
        obj = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        run();
        out.flush();
        out.close();
    }

    void run() throws IOException {
        String inp[] = obj.readLine().split(" ");
        int n = Integer.parseInt(inp[0]);
        int b = Integer.parseInt(inp[1]);
        Point point[] = new Point[n];
        for (int i = 0; i < n; i++) {
            inp = obj.readLine().split(" ");
            int id = Integer.parseInt(inp[0]);
            double x = Double.parseDouble(inp[1]);
            double y = Double.parseDouble(inp[2]);
            double z = Double.parseDouble(inp[3]);
            point[i] = new Point(id, x, y, z);
        }
        Arrays.sort(point);
        HashMap<Integer, Point> hash = new HashMap<>();
    }

    class Point implements Comparable<Point> {
        double x, y, z;
        int id;

        Point(int id, double a, double b, double c) {
            x = a;
            y = b;
            z = c;
            this.id = id;
        }

        @Override
        public int compareTo(Point o) {
            return Double.compare(x, o.x);
        }
    }
}