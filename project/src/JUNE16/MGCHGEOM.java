package JUNE16;
/**
 * Created by ankurverma1994.
 * My code is awesome!
 */

import java.util.*;
import java.io.*;
import java.math.*;

class MGCHGEOM {
    final double eps = 1e-6;
    //    TreeMap<String, Integer> map;
    int len;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
            int n = ii();
            co = new long[n + 10][];
            len = 0;
//            map = new TreeMap<>();
            for (int i = 0; i < n; i++) {
                char c = ic();
                if (c == '+') {
                    addPoints();
                }
                if (c == '-') {
                    removePoints();
                }
//                for (int j = 0; j < len; j++)
//                    System.out.println(Arrays.toString(co[j]));
//                System.out.println(len);
                long hull[][] = convexHull(co, len);
                out.printf("%.1f\n", area(hull));
            }
        }
    }

    double area(long hull[][]) {
        int len = hull.length;
        double ans = 0;
        for (int i = 0; i < len; i++) {
            long x1, y1, x2, y2;
            if (i > 0) {
                x1 = hull[i - 1][0];
                y1 = hull[i - 1][1];
            } else {
                x1 = hull[len - 1][0];
                y1 = hull[len - 1][1];
            }
            x2 = hull[i][0];
            y2 = hull[i][1];
            ans = ans + (x1 - x2) * (y1 + y2);
        }
        return Math.abs(ans) / 2;
    }

//    long[][] prepare() {
//        long[][] co = new long[len][];
//        int i = 0;
////        for (Map.Entry<String, Integer> a : map.entrySet()) {
////            String k = a.getKey();
////            String s[] = k.split(" ");
////            int x = Integer.parseInt(s[0]), y = Integer.parseInt(s[1]);
////            int val = a.getValue();
////            co[i] = new long[]{x, y, i};
////            i++;
////        }
//        return convexHull(co);
//    }

    long co[][];

    void addPoints() {
        int x = ii(), y = ii();
        co[len] = new long[]{x, y, len};
        len++;
    }

    void removePoints() {
        int x = ii(), y = ii();
        int i = 0;
        for (; i < len; i++)
            if (co[i][0] == x && co[i][1] == y)
                break;
        for (int j = i + 1; j < len; j++) {
            co[j - 1][0] = co[j][0];
            co[j - 1][1] = co[j][1];
            co[j - 1][2] = co[j][2];
        }
        len--;
    }

//    void addPoint() {
//        int x = ii(), y = ii();
//        String p = String.valueOf(x) + " " + String.valueOf(y);
//        if (map.containsKey(p)) map.put(p, map.get(p) + 1);
//        else map.put(p, 1);
//    }
//
//    void removePoint() {
//        int x = ii(), y = ii();
//        String p = String.valueOf(x) + " " + String.valueOf(y);
//        int val = map.get(p) - 1;
//        if (val == 0) map.remove(p);
//        else map.put(p, val);
//    }

    public long[][] convexHull(long[][] co, int n) {
        /* This part of code is picked up from "uwi" previous submission */
//        int n = co.length;
//        System.out.println(n);
//        if (n <= 1) return co;
        if (n <= 1) {
            long ret[][] = new long[1][];
            ret[0] = co[0];
            return ret;
        }
        Arrays.sort(co, 0, n, new Comparator<long[]>() {
            public int compare(long[] a, long[] b) {
                if (a[0] != b[0]) return Long.compare(a[0], b[0]);
                return Long.compare(a[1], b[1]);
            }
        });

        int[] inds = new int[n + 1];
        int p = 0;
        for (int i = 0; i < n; i++) {
            if (p >= 1 && co[inds[p - 1]][0] == co[i][0] && co[inds[p - 1]][1] == co[i][1]) continue;
            while (p >= 2 && ccw(co[inds[p - 2]], co[inds[p - 1]], co[i]) >= 0) p--; // if you need point on line
            inds[p++] = i;
        }

        int inf = p + 1;
        for (int i = n - 2; i >= 0; i--) {
            if (co[inds[p - 1]][0] == co[i][0] && co[inds[p - 1]][1] == co[i][1]) continue;
            while (p >= inf && ccw(co[inds[p - 2]], co[inds[p - 1]], co[i]) >= 0) p--; // if you need point on line
            inds[p++] = i;
        }

        long[][] ret = new long[p - 1][];
        for (int i = 0; i < p - 1; i++) ret[i] = co[inds[i]];
        return ret;
    }

    public static int ccw(long ax, long ay, long bx, long by, long tx, long ty) {
        return Long.signum((tx - ax) * (by - ay) - (bx - ax) * (ty - ay));
    }

    public static int ccw(long[] a, long[] b, long[] t) {
        return Long.signum((t[0] - a[0]) * (b[1] - a[1]) - (b[0] - a[0]) * (t[1] - a[1]));
    }

    public static int ccw(int[] a, int[] b, int[] t) {
        return Long.signum((long) (t[0] - a[0]) * (b[1] - a[1]) - (long) (b[0] - a[0]) * (t[1] - a[1]));
    }


    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new MGCHGEOM().main1();
    }

    void main1() throws IOException {
        out = new PrintWriter(System.out);
        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
        // obj=check.isEmpty() ? new FileInputStream("/home/ankurverma1994/input.txt") : new ByteArrayInputStream(check.getBytes());
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

    String is() {
        int b = skip();
        StringBuilder sb = new StringBuilder();
        while (!(isSpaceChar(b))) // when nextLine, (isSpaceChar(b) && b!=' ')
        {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
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

    boolean isSpaceChar(int c) {
        return (!(c >= 33 && c <= 126));
    }

    int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b)) ;
        return b;
    }

    double id() {
        return Double.parseDouble(is());
    }

    char ic() {
        return (char) skip();
    }
}
