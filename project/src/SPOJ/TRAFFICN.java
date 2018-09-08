package SPOJ;
/**
 * Created by ankurverma1994.
 * My code is awesome!
 */

import java.util.*;
import java.io.*;
import java.math.*;

class TRAFFICN {
    final int inf = 715827882;

    //------------> Solution starts here!!
    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
            int n = ii(), m = ii(), k = ii(), s = ii() - 1, t = ii() - 1;
            int from[] = new int[m];
            int to[] = new int[m];
            int w[] = new int[m];
            for (int i = 0; i < m; i++) {
                from[i] = ii() - 1;
                to[i] = ii() - 1;
                w[i] = ii();
            }

            int g[][][] = packWD(n, from, to, w);
            int gr[][][] = packWD(n, to, from, w);
            int ds[] = dijk(g, s);
            int dt[] = dijk(gr, t);
            long ans = ds[t];

            for (int i = 0; i < k; i++) {
                int u = ii() - 1, v = ii() - 1, q = ii();
                ans = Math.min(ans, ds[u] + q + dt[v]);
                ans = Math.min(ans, ds[v] + q + dt[u]);
            }

            out.println(ans >= inf ? "-1" : ans);
        }
    }

    public int[] dijk(int[][][] g, int from) {
        /* This part of code is picked up from "uwi" previous submission */
        int n = g.length;
        int[] td = new int[n];

        Arrays.fill(td, inf); // max
        MinHeapL q = new MinHeapL(n);
        q.add(from, 0);
        td[from] = 0;

        while (q.size() > 0) {
            int cur = q.argmin();
            q.remove(cur);

            for (int[] e : g[cur]) {
                int next = e[0];
                int nd = td[cur] + e[1];
                if (nd < td[next]) {
                    td[next] = nd;
                    q.update(next, nd);
                }
            }
        }
        return td;
    }

    public static class MinHeapL {
        /* This part of code is picked up from "uwi" previous submission */
        public int[] a;
        public int[] map;
        public int[] imap;
        public int n;
        public int pos;
        public static int INF = Integer.MAX_VALUE;

        public MinHeapL(int m) {
            n = Integer.highestOneBit((m + 1) << 1);
            a = new int[n];
            map = new int[n];
            imap = new int[n];
            Arrays.fill(a, INF);
            Arrays.fill(map, -1);
            Arrays.fill(imap, -1);
            pos = 1;
        }

        public long add(int ind, int x) {
            int ret = imap[ind];
            if (imap[ind] < 0) {
                a[pos] = x;
                map[pos] = ind;
                imap[ind] = pos;
                pos++;
                up(pos - 1);
            }
            return ret != -1 ? a[ret] : x;
        }

        public long update(int ind, int x) {
            int ret = imap[ind];
            if (imap[ind] < 0) {
                a[pos] = x;
                map[pos] = ind;
                imap[ind] = pos;
                pos++;
                up(pos - 1);
            } else {
                a[ret] = x;
                up(ret);
                down(ret);
            }
            return x;
        }

        public long remove(int ind) {
            if (pos == 1) return INF;
            if (imap[ind] == -1) return INF;

            pos--;
            int rem = imap[ind];
            long ret = a[rem];
            map[rem] = map[pos];
            imap[map[pos]] = rem;
            imap[ind] = -1;
            a[rem] = a[pos];
            a[pos] = INF;
            map[pos] = -1;

            up(rem);
            down(rem);
            return ret;
        }

        public long min() {
            return a[1];
        }

        public int argmin() {
            return map[1];
        }

        public int size() {
            return pos - 1;
        }

        private void up(int cur) {
            for (int c = cur, p = c >>> 1; p >= 1 && a[p] > a[c]; c >>>= 1, p >>>= 1) {
                int d = a[p];
                a[p] = a[c];
                a[c] = d;
                int e = imap[map[p]];
                imap[map[p]] = imap[map[c]];
                imap[map[c]] = e;
                e = map[p];
                map[p] = map[c];
                map[c] = e;
            }
        }

        private void down(int cur) {
            for (int c = cur; 2 * c < pos; ) {
                int b = a[2 * c] < a[2 * c + 1] ? 2 * c : 2 * c + 1;
                if (a[b] < a[c]) {
                    int d = a[c];
                    a[c] = a[b];
                    a[b] = d;
                    int e = imap[map[c]];
                    imap[map[c]] = imap[map[b]];
                    imap[map[b]] = e;
                    e = map[c];
                    map[c] = map[b];
                    map[b] = e;
                    c = b;
                } else {
                    break;
                }
            }
        }
    }

    // for directed weighted graph
    public static int[][][] packWD(int n, int[] from, int[] to, int[] w) {
        /* This part of code is picked up from "uwi" previous submission */
        int[][][] g = new int[n][][];
        int[] p = new int[n];
        for (int f : from)
            p[f]++;
        for (int i = 0; i < n; i++)
            g[i] = new int[p[i]][2];
        for (int i = 0; i < from.length; i++) {
            --p[from[i]];
            g[from[i]][p[from[i]]][0] = to[i];
            g[from[i]][p[from[i]]][1] = w[i];
        }
        return g;
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new TRAFFICN().main1();
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