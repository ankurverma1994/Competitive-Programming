package NPL;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class ProbD {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int n = ii(), m = n - 1;
        int from[] = new int[m];
        int to[] = new int[m];
        int w[] = new int[m];
        for (int i = 0; i < m; i++) {
            from[i] = ii() - 1;
            to[i] = ii() - 1;
            w[i] = ii();
        }


        int g[][][] = packWU(n, from, to, w);
        int pars[][] = parents(g, 0);
        int dep[] = pars[2];
        ans = new int[n];
        start = new int[n];
        end = new int[n];
        index = 0;
        dfs(g, 0, -1, 0);


        LazySegmentTree st = new LazySegmentTree(ans, n);


        for (int q = ii(); q > 0; q--) {

            int type = ii();

            if (type == 1) {

                int x = ii() - 1, v = ii();
                int value = v ^ w[x];
                w[x] = v;
                int node = dep[from[x]] < dep[to[x]] ? to[x] : from[x];
                st.update(start[node], end[node], value);

            }

            if (type == 2) {

                int u = ii() - 1, v = ii() - 1;
                out.println(st.query(start[u], start[u]) ^ st.query(start[v], start[v]));

            }
        }
    }

    int ans[], start[], end[], index;

    void dfs(int g[][][], int curr, int par, int dis) {

        start[curr] = index;
        ans[index] = dis;

        for (int i = 0; i < g[curr].length; i++) {

            int next = g[curr][i][0];
            int w = g[curr][i][1];
            if (next != par) {
                index++;
                dfs(g, next, curr, dis ^ w);
            }

        }
        end[curr] = index;
    }

    public static int[][] parents(int[][][] g, int root) {
        int n = g.length;
        int[] par = new int[n];
        Arrays.fill(par, -1);
        int[] dw = new int[n];
        int[] pw = new int[n];
        int[] dep = new int[n];

        int[] q = new int[n];
        q[0] = root;
        for (int p = 0, r = 1; p < r; p++) {
            int cur = q[p];
            for (int[] nex : g[cur]) {
                if (par[cur] != nex[0]) {
                    q[r++] = nex[0];
                    par[nex[0]] = cur;
                    dep[nex[0]] = dep[cur] + 1;
                    dw[nex[0]] = dw[cur] + nex[1];
                    pw[nex[0]] = nex[1];
                }
            }
        }
        return new int[][]{par, q, dep, dw, pw};
    }

    public static int[][][] packWU(int n, int[] from, int[] to, int[] w) {
        /* This part of code is picked up from "uwi" previous submission */
        int[][][] g = new int[n][][];
        int[] p = new int[n];
        for (int f : from)
            p[f]++;
        for (int t : to)
            p[t]++;
        for (int i = 0; i < n; i++)
            g[i] = new int[p[i]][2];
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

    class LazySegmentTree {
        int tree[], lazy[];
        int a[];
        int n;

        int operation(int a, int b) {
            return a ^ b;
        }

        public LazySegmentTree(int a[], int n) {
            tree = new int[4 * n];
            lazy = new int[4 * n];
            this.a = new int[n];
            this.n = n;
            for (int i = 0; i < n; i++)
                this.a[i] = a[i];
            build(0, n - 1, 0);
        }

        public void build(int s, int e, int c) {
            if (s == e) {
                tree[c] = a[s];
                return;
            }
            int m = (s + e) >> 1;
            build(s, m, 2 * c + 1);
            build(m + 1, e, 2 * c + 2);
            tree[c] = operation(tree[2 * c + 1], tree[2 * c + 2]);
        }

        void push(int node, int start, int end) {
            if (lazy[node] != 0) {
                // This node needs to be updated
                // TODO for sum, change according to operation
//                tree[node] += (end - start + 1) * lazy[node];    // Update it
                tree[node] ^= lazy[node];
                if (start != end) {
                    lazy[node * 2 + 1] ^= lazy[node];                  // Mark child as lazy
                    lazy[node * 2 + 2] ^= lazy[node];                // Mark child as lazy
                }
                lazy[node] = 0;                                  // Reset it
            }
        }

        // a[x] ^= v
        void update(int l, int r, int val) {
            updateRange(0, 0, n - 1, l, r, val);
        }

        int query(int l, int r) {
            return queryRange(0, 0, n - 1, l, r);
        }

        void updateRange(int node, int start, int end, int l, int r, int val) {
            push(node, start, end);
            if (start > end || start > r || end < l)              // Current segment is not within range [l, r]
                return;
            if (start >= l && end <= r) {
                // Segment is fully within range
                // TODO for sum, change according to operation
//                tree[node] += (end - start + 1) * val;
                tree[node] ^= val;
                if (start != end) {
                    // Not leaf node
                    lazy[node * 2 + 1] ^= val;
                    lazy[node * 2 + 2] ^= val;
                }
                return;
            }
            int mid = (start + end) / 2;
            updateRange(node * 2 + 1, start, mid, l, r, val);        // Updating left child
            updateRange(node * 2 + 2, mid + 1, end, l, r, val);   // Updating right child
            tree[node] = operation(tree[node * 2 + 1], tree[node * 2 + 2]);        // Updating root with max value
        }

        int queryRange(int node, int start, int end, int l, int r) {
            if (start > end || start > r || end < l)
                return 0;         // Out of range
            push(node, start, end);
            if (start >= l && end <= r)             // Current segment is totally within range [l, r]
                return tree[node];
            int mid = (start + end) / 2;
            int p1 = queryRange(node * 2 + 1, start, mid, l, r);         // Query left child
            int p2 = queryRange(node * 2 + 2, mid + 1, end, l, r); // Query right child
            return operation(p1, p2);
        }
    }


    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new ProbD().main1();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (StackOverflowError e) {
                    System.out.println("RTE");
                }
            }
        }, "1", 1 << 26).start();
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
