package aaa;
/**
 * Created by ankurverma1994
 */

import java.io.*;
import java.util.*;
import java.math.*;

class LCA {
    int g[][];
    int H[]; //where H[i] records the index of the first occurrence of node i in E
    int E[]; //which records the sequence of visited nodes
    int L[]; //which records the depth of each visited node
    int idx;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        System.out.println("Enter a rooted tree(0 based indexing)\nEnter number of nodes");
        int n = ii();
        H = new int[n + 1];
        E = new int[2 * n + 2];
        L = new int[2 * n + 2];
        Arrays.fill(H, -1);
        System.out.println("Enter root of a tree");
        int root = ii();
        System.out.println("Enter " + (n - 1) + " lines space seprated two integers denoting edge between them");
        int from[] = new int[n - 1];
        int to[] = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            from[i] = ii();
            to[i] = ii();
        }
        g = packU(n, from, to, n - 1);
        idx = 0;
        dfs(root, 0);
        RmqSparseTable sparseTable = new RmqSparseTable(L);
        System.out.println("Enter number of queries for LCA");
        for (int q = ii(); q > 0; q--) {
            int u = ii(), v = ii();
            int index = sparseTable.minPos(H[u], H[v]);
            System.out.println(E[index]);
        }
    }

    void dfs(int cur, int depth) {
        H[cur] = idx;
        E[idx] = cur;
        L[idx++] = depth;
        for (int i = 0; i < g[cur].length; i++) {
            dfs(g[cur][i], depth + 1);
            E[idx] = cur;                         // backtrack to current node
            L[idx++] = depth;
        }
    }

    // for undirected graph, max is length of from or to array
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

    class RmqSparseTable {

        int[] logTable;
        int[][] rmq;
        int[] a;

        public RmqSparseTable(int[] a) {
            this.a = a;
            int n = a.length;

            logTable = new int[n + 1];
            for (int i = 2; i <= n; i++)
                logTable[i] = logTable[i >> 1] + 1;

            rmq = new int[logTable[n] + 1][n];

            for (int i = 0; i < n; ++i)
                rmq[0][i] = i;

            for (int k = 1; (1 << k) < n; ++k) {
                for (int i = 0; i + (1 << k) <= n; i++) {
                    int x = rmq[k - 1][i];
                    int y = rmq[k - 1][i + (1 << k - 1)];
                    rmq[k][i] = a[x] <= a[y] ? x : y;
                }
            }
        }

        public int minPos(int i, int j) {
            int k = logTable[j - i];
            int x = rmq[k][i];
            int y = rmq[k][j - (1 << k) + 1];
            return a[x] <= a[y] ? x : y;
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
                    new LCA().main1();
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
