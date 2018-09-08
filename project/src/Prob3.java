/**
 * Created by ankurverma1994
 */

import java.io.*;
import java.util.*;
import java.math.*;

class Prob3 {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int n = ii();
        a = iia(n);
        int from[] = new int[n - 1];
        int to[] = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            from[i] = ii() - 1;
            to[i] = ii() - 1;
        }
        int g[][] = packU(n, from, to, n - 1);
        int temp[][] = parents3(g, 0);
        int parent[] = temp[0], depth[] = temp[2];
        int pars[][] = logStepParents(parent, n);

        for (int Q = ii(); Q > 0; Q--) {
            char c = ic();
            int x = ii() - 1, y = ii() - 1;
            if (c == 'C') {
                out.println(bfs(x, g, y, false));
            } else if (c == 'F') {
                int L = lca(x, y, pars, parent, depth);
                int max = Math.max(mx(x, L, pars, parent, depth), mx(L, y, pars, parent, depth));
                int min = Math.min(mn(x, L, pars, parent, depth), mn(L, y, pars, parent, depth));
                max = Math.max(max, a[L]);
                min = Math.min(min, a[L]);
////                out.println(max + " " + min);
                out.println(max - min);
            }
        }
    }

    int max[][], min[][];

    // pars[j][i]--> 2^j parent of node i
    int[][] logStepParents(int parent[], int n) {
        int m = (int) Math.ceil(Math.log(n)) + 1;
        int pars[][] = new int[m][n];
        max = new int[m][n];
        min = new int[m][n];

        for (int i = 0; i < m; i++)
            Arrays.fill(pars[i], -1);

        for (int i = 0; i < n; i++) {
            pars[0][i] = parent[i];
            min[0][i] = max[0][i] = a[i];
        }

        for (int j = 1; (1 << j) < n; j++)
            for (int i = 0; i < n; i++) {
                if (pars[j - 1][i] != -1) {
                    pars[j][i] = pars[j - 1][pars[j - 1][i]];
                    max[j][i] = Math.max(max[j - 1][i], max[j - 1][pars[j - 1][i]]);
                    min[j][i] = Math.min(min[j - 1][i], min[j - 1][pars[j - 1][i]]);
                }
            }
        return pars;
    }

    int lca(int p, int q, int pars[][], int parent[], int depth[]) {
        //if p is situated on a higher level than q then we swap them
        if (depth[p] < depth[q]) {
            int temp = p;
            p = q;
            q = temp;
        }

        //we compute the value of [log(L[p)]
        int log = 1;
        for (; (1 << log) <= depth[p]; log++) ;
        log--;

        //we find the ancestor of node p situated on the same level
        //with q using the values in P
        for (int i = log; i >= 0; i--)
            if (depth[p] - (1 << i) >= depth[q])
                p = pars[i][p];

        if (p == q) return p;

        //we compute LCA(p, q) using the values in P
        for (int i = log; i >= 0; i--)
            if (pars[i][p] != -1 && pars[i][p] != pars[i][q]) {
                p = pars[i][p];
                q = pars[i][q];
            }
        return parent[p];
    }

    int mx(int p, int q, int pars[][], int parent[], int depth[]) {
        //if p is situated on a higher level than q then we swap them
        int ans = 0;
        if (depth[p] < depth[q]) {
            int temp = p;
            p = q;
            q = temp;
        }

        //we compute the value of [log(L[p)]
        int log = 1;
        for (; (1 << log) <= depth[p]; log++) ;
        log--;

        //we find the ancestor of node p situated on the same level
        //with q using the values in P
        for (int i = log; i >= 0; i--)
            if (depth[p] - (1 << i) >= depth[q]) {
                ans = Math.max(ans, max[i][p]);
                p = pars[i][p];
            }

        if (p == q) return ans;

        //we compute LCA(p, q) using the values in P
        for (int i = log; i >= 0; i--)
            if (pars[i][p] != -1 && pars[i][p] != pars[i][q]) {
                ans = Math.max(ans, max[i][p]);
                ans = Math.max(ans, max[i][q]);
                p = pars[i][p];
                q = pars[i][q];
            }
        if (parent[p] != -1)
            ans = Math.max(ans, Math.max(max[0][p], max[0][q]));
        return ans;
    }

    int mn(int p, int q, int pars[][], int parent[], int depth[]) {
        //if p is situated on a higher level than q then we swap them
        int ans = Integer.MAX_VALUE;
        if (depth[p] < depth[q]) {
            int temp = p;
            p = q;
            q = temp;
        }

        //we compute the value of [log(L[p)]
        int log = 1;
        for (; (1 << log) <= depth[p]; log++) ;
        log--;

        //we find the ancestor of node p situated on the same level
        //with q using the values in P
        for (int i = log; i >= 0; i--)
            if (depth[p] - (1 << i) >= depth[q]) {
                ans = Math.min(ans, min[i][p]);
                p = pars[i][p];
            }

        if (p == q) return ans;

        //we compute LCA(p, q) using the values in P
        for (int i = log; i >= 0; i--)
            if (pars[i][p] != -1 && pars[i][p] != pars[i][q]) {
                ans = Math.min(ans, min[i][p]);
                ans = Math.min(ans, min[i][q]);
                p = pars[i][p];
                q = pars[i][q];
            }
        if (parent[p] != -1)
            ans = Math.min(ans, Math.min(min[0][p], min[0][q]));
        return ans;
    }

    public int[][] parents3(int[][] g, int root) {
        /* This part of code is picked up from "uwi" previous submission */
        int n = g.length;
        int[] par = new int[n];
        Arrays.fill(par, -1);

        int[] depth = new int[n];
        depth[0] = 0;

        int[] levelTraversal = new int[n];
        levelTraversal[0] = root;
        for (int p = 0, r = 1; p < r; p++) {
            int cur = levelTraversal[p];
            for (int nex : g[cur]) {
                if (par[cur] != nex) {
                    levelTraversal[r++] = nex;
                    par[nex] = cur;
                    depth[nex] = depth[cur] + 1;
                }
            }
        }
        return new int[][]{par, levelTraversal, depth};
    }

    int a[];

    int bfs(int v, int graph[][], int des, boolean max) {
        int d[] = new int[graph.length];
        int parent[] = new int[graph.length];
        Arrays.fill(d, Integer.MAX_VALUE / 2);
        d[v] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(v);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int w = 0; w < graph[u].length; w++) {
                if (d[graph[u][w]] == Integer.MAX_VALUE / 2) {
                    d[graph[u][w]] = d[u] + 1;
                    parent[graph[u][w]] = u;
                    q.add(graph[u][w]);
                }
            }
        }
        int number[] = new int[graph.length];
        int length = 0;
        while (des != v) {
            number[length++] = a[des];
            des = parent[des];
        }
        number[length++] = a[des];
        Arrays.sort(number, 0, length);
//        out.println(Arrays.toString(number) + " " + length);
        if (max)
            return number[length - 1] - number[0];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i + 1 < length; i++)
            min = Math.min(min, number[i + 1] - number[i]);
        return min;
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
                    new Prob3().main1();
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

    long il() {
        long num = 0;
        int b;
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

    float nf() {
        return Float.parseFloat(is());
    }

    double id() {
        return Double.parseDouble(is());
    }

    char ic() {
        return (char) skip();
    }

    int[] iia(int n) {
        int a[] = new int[n];
        for (int i = 0; i < n; i++) a[i] = ii();
        return a;
    }

    long[] ila(int n) {
        long a[] = new long[n];
        for (int i = 0; i < n; i++) a[i] = il();
        return a;
    }

    String[] isa(int n) {
        String a[] = new String[n];
        for (int i = 0; i < n; i++) a[i] = is();
        return a;
    }

    double[][] idm(int n, int m) {
        double a[][] = new double[n][m];
        for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) a[i][j] = id();
        return a;
    }

    int[][] iim(int n, int m) {
        int a[][] = new int[n][m];
        for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) a[i][j] = ii();
        return a;
    }
}
