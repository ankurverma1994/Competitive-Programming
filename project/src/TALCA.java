/**
 * Created by ankurverma1994.
 * My code is awesome!
 */

import java.util.*;
import java.io.*;

class TALCA {

    final int inf = Integer.MAX_VALUE / 2;

    //------------> Solution starts here!!
    // finding LCA using square root decomposition :)
    @SuppressWarnings("Main Logic")
    void solve() {
        int n = ii();
        int from[] = new int[n - 1];
        int to[] = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            from[i] = ii() - 1;
            to[i] = ii() - 1;
        }
        int g[][] = packU(n, from, to, n - 1);
        // assume root to be 1
        P = new int[n];
        L = new int[n];
        T = new int[n];
        visit = new boolean[n];
        int sqrtN = (int) Math.floor(Math.sqrt(n));
        dfs1(g, 0, 0);
        Arrays.fill(visit, false);
        dfs2(0, sqrtN, g);
//        out.println(Arrays.toString(L));
//        out.println(Arrays.toString(T));
//        out.println(sqrtN);
//        out.println(Arrays.toString(P));
        for (int Q = ii(); Q > 0; Q--) {
            int root = ii() - 1, u = ii() - 1, v = ii() - 1;
            /**
             * possible answer are
             * u v root lca(u,v) lca(u,root) lca(v,root)
             */
            int minDist = inf, possibleLCA = root;
            int node[] = {root, u, v, lca(u, v), lca(u, root), lca(v, root)};
            for (int x : node) {
                int dist = distance(x, u) + distance(x, v) + distance(x, root);
//                System.out.println((x + 1) + " " + dist);
                if (dist < minDist) {
                    minDist = dist;
                    possibleLCA = x;
                }
            }
            out.println(possibleLCA + 1);
        }
    }


    // O(N) precomputation
    // O(sqrt(H)) query.. H--> max height of tree
    // nr = sqrt(N), N--> no. of nodes in tree
    // P--> uss subtree ka root find karega
    // T--> father/parent of node precompute
    //root --> 1
    // L --> level of tree precompute
    int P[], L[], T[];
    boolean visit[];

    void dfs1(int g[][], int node, int level) {
        L[node] = level;
        visit[node] = true;
        for (int i = 0; i < g[node].length; i++) {
            int v = g[node][i];
            if (!visit[v]) {
                T[v] = node;
                dfs1(g, v, level + 1);
            }
        }
    }

    void dfs2(int node, int nr, int g[][]) {
        visit[node] = true;

        if (L[node] < nr)
            P[node] = 1;
        else if (L[node] % nr == 0)
            P[node] = T[node];
        else
            P[node] = P[T[node]];
        for (int i = 0; i < g[node].length; i++) {
            int v = g[node][i];
            if (!visit[v])
                dfs2(v, nr, g);
        }
    }

    int lca(int x, int y) {
        while (P[x] != P[y]) {
            if (L[x] > L[y])
                x = P[x];
            else
                y = P[y];
        }
        while (x != y) {
            if (L[x] > L[y])
                x = T[x];
            else
                y = T[y];
        }
        return x;
    }

    int distance(int x, int y) {
        //in rooted tree distance=level
        int lca = lca(x, y);
        return L[x] + L[y] - 2 * L[lca];
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

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new TALCA().main1();
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
