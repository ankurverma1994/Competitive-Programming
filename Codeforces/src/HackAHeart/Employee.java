package HackAHeart;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;

class Employee {
    int n, justice[], g[][], max[],spar[][],par[],pars[][],dep[];

    //------------> Solution starts here!!
    void solve() {
        n = ii();
        justice = iia(n);
        int from[] = new int[n - 1];
        int to[] = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            from[i] = i + 1;
            to[i] = ii() - 1;
        }
        g = packU(n, from, to, n - 1);
        pre();
        for (int q = ii(); q > 0; q--) {
            int u = ii() - 1, v = ii() - 1;
            int lca = LCA(u, v, spar, dep);
            out.println(max[lca]);
        }
    }
    void pre(){
        max = new int[n];
        dfs(0, -1, justice[0], 0);
        pars = PARENTS(g, 0);
        par = pars[0];
        dep = pars[2];
        spar = LSP(par);
    }

    void dfs(int curr, int par, int JF, int id) {
        max[curr] = id + 1;
        for (int next : g[curr]) {
            if (next == par) continue;
            dfs(next, curr, Math.max(JF, justice[next]), justice[next] > JF ? next : id);
        }
    }

    /* LCA code picked from competitive programming user "UWI" */
    public static int[][] PARENTS(int[][] g, int root) {
        /* This part of code is picked up from "uwi" previous submission */
        int n = g.length;
        int[] par = new int[n];
        Arrays.fill(par, -1);

        int[] depth = new int[n];
        depth[0] = 0;

        int[] q = new int[n];
        q[0] = root;
        for (int p = 0, r = 1; p < r; p++) {
            int cur = q[p];
            for (int nex : g[cur]) {
                if (par[cur] != nex) {
                    q[r++] = nex;
                    par[nex] = cur;
                    depth[nex] = depth[cur] + 1;
                }
            }
        }
        return new int[][]{par, q, depth};
    }

    public static int LCA(int a, int b, int[][] sparse, int[] depth) {
        /* This part of code is picked up from "uwi" previous submission */
        if (depth[a] < depth[b]) {
            b = ancestor(b, depth[b] - depth[a], sparse);
        } else if (depth[a] > depth[b]) {
            a = ancestor(a, depth[a] - depth[b], sparse);
        }

        if (a == b)
            return a;
        int sa = a, sb = b;
        for (int low = 0, high = depth[a], t = Integer.highestOneBit(high), k = Integer
                .numberOfTrailingZeros(t); t > 0; t >>>= 1, k--) {
            if ((low ^ high) >= t) {
                if (sparse[k][sa] != sparse[k][sb]) {
                    low |= t;
                    sa = sparse[k][sa];
                    sb = sparse[k][sb];
                } else {
                    high = low | t - 1;
                }
            }
        }
        return sparse[0][sa];
    }

    protected static int ancestor(int a, int m, int[][] spar) {
        /* This part of code is picked up from "uwi" previous submission */
        for (int i = 0; m > 0 && a != -1; m >>>= 1, i++) {
            if ((m & 1) == 1)
                a = spar[i][a];
        }
        return a;
    }

    public static int[][] LSP(int[] par) {
        /* This part of code is picked up from "uwi" previous submission */
        int n = par.length;
        int m = Integer.numberOfTrailingZeros(Integer.highestOneBit(n - 1)) + 1;
        int[][] pars = new int[m][n];
        pars[0] = par;
        for (int j = 1; j < m; j++) {
            for (int i = 0; i < n; i++) {
                pars[j][i] = pars[j - 1][i] == -1 ? -1 : pars[j - 1][pars[j - 1][i]];
            }
        }
        return pars;
    }

    public static int[][] packU(int n, int[] from, int[] to, int max) {
        /* This part of code is picked up from "uwi" previous submission */
        int[][] g = new int[n][];
        int[] p = new int[n];
        for (int i = 0; i < max; i++) p[from[i]]++;
        for (int i = 0; i < max; i++) p[to[i]]++;
        for (int i = 0; i < n; i++) g[i] = new int[p[i]];
        for (int i = 0; i < max; i++) {
            g[from[i]][--p[from[i]]] = to[i];
            g[to[i]][--p[to[i]]] = from[i];
        }
        return g;
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new Employee().main1();
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

    int[] iia(int n) {
        int a[] = new int[n];
        for (int i = 0; i < n; i++) a[i] = ii();
        return a;
    }
}
