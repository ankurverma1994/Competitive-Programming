package Codeforces;
/**
 * Created by ankurverma1994.
 * My code is awesome!
 * Segment Tree gives TLE at test 7
 */

import java.util.*;
import java.io.*;
import java.math.*;

public class MinMaxEqual {

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int n = ii(), a[] = iia(n), b[] = iia(n);
        /*sta = new SegmentTree(a, n, true);
        stb = new SegmentTree(b, n, false) {
            int operation(int a, int b) {
                return Math.min(a, b);
            }
        };
        */
        ds = new RmqSparseTable(a, b);
        long ans = 0;
        for (int l = 0; l < n; l++) {
            int rmin = BinarySearchLowerBound(l, n);
            int rmax = BinarySearchUpperBound(l, n);
//            out.println(l + " " + rmin + " " + l + " " + rmax + (rmin == n || rmax < l));
            if (rmin == n || rmax < l) continue;
            ans = ans + (rmax - rmin + 1);
        }
        out.println(ans);
    }

    //    SegmentTree sta, stb;
    RmqSparseTable ds;

    public int BinarySearchLowerBound(int v, int n) {
        int low = v - 1, high = n;
        while (high - low > 1) {
            int h = high + low >>> 1;
            if (valid(v, h) >= 0) {
                high = h;
            } else {
                low = h;
            }
        }
        return high;
    }

    int valid(int l, int r) {
        return ds.max(l, r) - ds.min(l, r);
//        return sta.query(l, r) - stb.query(l, r);
    }

    public int BinarySearchUpperBound(int v, int n) {
        int low = v - 1, high = n;
        while (high - low > 1) {
            int h = high + low >>> 1;
            if (valid(v, h) <= 0) {
                low = h;
            } else {
                high = h;
            }
        }
        return low;
    }

    class RmqSparseTable {

        int[] logTable1, logTable2;
        int[][] rmq1, rmq2;
        int[] a, b;

        public RmqSparseTable(int[] a, int b[]) {
            this.a = a;
            this.b = b;
            int n = a.length;

            logTable1 = new int[n + 1];
            logTable2 = new int[n + 1];
            for (int i = 2; i <= n; i++) {
                logTable1[i] = logTable1[i >> 1] + 1;
                logTable2[i] = logTable2[i >> 1] + 1;
            }

            rmq1 = new int[logTable1[n] + 1][n];
            rmq2 = new int[logTable2[n] + 1][n];

            for (int i = 0; i < n; ++i) {
                rmq1[0][i] = i;
                rmq2[0][i] = i;
            }

            for (int k = 1; (1 << k) < n; ++k) {
                for (int i = 0; i + (1 << k) <= n; i++) {
                    int x = rmq1[k - 1][i];
                    int y = rmq1[k - 1][i + (1 << k - 1)];
                    rmq1[k][i] = a[x] >= a[y] ? x : y;

                    x = rmq2[k - 1][i];
                    y = rmq2[k - 1][i + (1 << k - 1)];
                    rmq2[k][i] = b[x] <= b[y] ? x : y;
                }
            }
        }

        public int max(int i, int j) {
            int k = logTable1[j - i];
            int x = rmq1[k][i];
            int y = rmq1[k][j - (1 << k) + 1];
            return Math.max(a[x], a[y]);
        }

        public int min(int i, int j) {
            int k = logTable2[j - i];
            int x = rmq2[k][i];
            int y = rmq2[k][j - (1 << k) + 1];
            return Math.min(b[x], b[y]);
        }
    }

    class SegmentTree {
        int tree[];
        int a[];
        int n;

        int operation(int a, int b) {
            return Math.max(a, b);
        }

        SegmentTree(int a[], int n, boolean flag) {
            tree = new int[4 * n];
            this.a = new int[n];
            this.n = n;
            for (int i = 0; i < n; i++)
                this.a[i] = a[i];
            if (flag) Arrays.fill(tree, Integer.MIN_VALUE); //max
            else Arrays.fill(tree, Integer.MAX_VALUE); //min
            build(0, n - 1, 0);
        }

        void build(int s, int e, int c) {
            if (s == e) {
                tree[c] = a[s];
                return;
            }
            int m = (s + e) >> 1;
            build(s, m, 2 * c + 1);
            build(m + 1, e, 2 * c + 2);
            tree[c] = operation(tree[2 * c + 1], tree[2 * c + 2]);
        }

        int query(int l, int r) {
            return get(0, n - 1, 0, l, r);
        }

        int get(int s, int e, int c, int l, int r) {
            if (s == l && e == r) return tree[c];
            int m = (s + e) >> 1;
            if (r <= m) return get(s, m, 2 * c + 1, l, r);
            if (l > m) return get(m + 1, e, 2 * c + 2, l, r);
            return operation(get(s, m, 2 * c + 1, l, m), get(m + 1, e, 2 * c + 2, m + 1, r));
        }
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new MinMaxEqual().main1();
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

    int[] iia(int n) {
        int a[] = new int[n];
        for (int i = 0; i < n; i++) a[i] = ii();
        return a;
    }
}
