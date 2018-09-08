package HackerRank;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {
    static InputStream is;
    static PrintWriter out;
    static String INPUT = "";

    static void solve() {
        int n = ni(), K = ni();
        int[] from = new int[n - 1];
        int[] to = new int[n - 1];
        int[] w = new int[n - 1];
        long all = 0;
        for (int i = 0; i < n - 1; i++) {
            from[i] = ni();
            to[i] = ni();
            w[i] = ni();
            all += w[i];
        }
        int[][][] g = packWU(n, from, to, w);

        boolean[] en = new boolean[n];
        for (int i = 0; i < K; i++) {
            en[ni()] = true;
        }

        Queue<int[]> q = new PriorityQueue<int[]>(200000, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return -(a[2] - b[2]);
            }
        });

        DJSet ds = new DJSet(n);
        for (int i = 0; i < n; i++) {
            if (en[i]) {
                for (int[] e : g[i]) {
                    q.add(new int[]{i, e[0], e[1]});
                }
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (!ds.equiv(cur[0], cur[1])) {
                boolean d0 = en[ds.root(cur[0])];
                boolean d1 = en[ds.root(cur[1])];
                if (d0 && d1) {
                } else {
                    ds.union(cur[0], cur[1]);
                    en[ds.root(cur[0])] = true;
                    for (int[] e : g[cur[1]]) {
                        q.add(new int[]{cur[1], e[0], e[1]});
                    }
                    all -= cur[2];
                }
            }
        }
        out.println(all);
    }

    public static int[][][] packWU(int n, int[] from, int[] to, int[] w) {
        int[][][] g = new int[n][][];
        int[] p = new int[n];
        for (int f : from) p[f]++;
        for (int t : to) p[t]++;
        for (int i = 0; i < n; i++) g[i] = new int[p[i]][2];
        for (int i = 0; i < from.length; i++) {
            --p[from[i]];
            g[from[i]][p[from[i]]][0] = to[i];
            g[from[i]][p[from[i]]][1] = w[i];
            --p[to[i]];
            g[to[i]][p[to[i]]][0] = from[i];
            g[to[i]][p[to[i]]][1] = w[i];
        }
        return g;
    }

    public static class DJSet {
        public int[] upper;

        public DJSet(int n) {
            upper = new int[n];
            Arrays.fill(upper, -1);
        }

        public int root(int x) {
            return upper[x] < 0 ? x : (upper[x] = root(upper[x]));
        }

        public boolean equiv(int x, int y) {
            return root(x) == root(y);
        }

        public void union(int x, int y) {
            x = root(x);
            y = root(y);
            if (x != y) {
                if (upper[y] < upper[x]) {
                    int d = x;
                    x = y;
                    y = d;
                }
                upper[x] += upper[y];
                upper[y] = x;
            }
        }

        public int count() {
            int ct = 0;
            for (int i = 0; i < upper.length; i++) {
                if (upper[i] < 0) ct++;
            }
            return ct;
        }
    }

    public static void main(String[] args) throws Exception {
        long S = System.currentTimeMillis();
        is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
        out = new PrintWriter(System.out);

        solve();
        out.flush();
        long G = System.currentTimeMillis();
        tr(G - S + "ms");
    }

    static boolean eof() {
        try {
            is.mark(1000);
            int b;
            while ((b = is.read()) != -1 && !(b >= 33 && b <= 126)) ;
            is.reset();
            return b == -1;
        } catch (IOException e) {
            return true;
        }
    }

    static int ni() {
        try {
            int num = 0;
            boolean minus = false;
            while ((num = is.read()) != -1 && !((num >= '0' && num <= '9') || num == '-')) ;
            if (num == '-') {
                num = 0;
                minus = true;
            } else {
                num -= '0';
            }

            while (true) {
                int b = is.read();
                if (b >= '0' && b <= '9') {
                    num = num * 10 + (b - '0');
                } else {
                    return minus ? -num : num;
                }
            }
        } catch (IOException e) {
        }
        return -1;
    }

    static void tr(Object... o) {
        if (INPUT.length() != 0) System.out.println(Arrays.deepToString(o));
    }
}
