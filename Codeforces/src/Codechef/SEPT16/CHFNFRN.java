package Codechef.SEPT16;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class CHFNFRN {

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
            int n = ii(), m = ii();
            boolean graph[][] = new boolean[n][n];
            for (int i = 0; i < m; i++) {
                int a = ii() - 1, b = ii() - 1;
                graph[a][b] = graph[b][a] = true;
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    graph[i][j] = !graph[i][j];
                }
                graph[i][i] = false;
            }
            int from[] = new int[n * n];
            int to[] = new int[n * n];
            int edges = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (graph[i][j]) {
                        from[edges] = i;
                        to[edges++] = j;
                    }
                }
            }
            int g[][] = packD(n, from, to, edges);
            out.println(isBipartitie(g, n) ? "YES" : "NO");
        }
    }

    // for directed graph, max is length of from or to array
    static int[][] packD(int n, int[] from, int[] to, int max) {
        /* This part of code is picked up from "uwi" previous submission */
        int[][] g = new int[n][];
        int[] p = new int[n];
        for (int i = 0; i < max; i++) p[from[i]]++;
        for (int i = 0; i < n; i++)
            g[i] = new int[p[i]];
        for (int i = 0; i < max; i++) {
            g[from[i]][--p[from[i]]] = to[i];
        }
        return g;
    }

    // To check graph is Bipartite or not and also to confirm that there doesn't
    // exist odd length cycle.
    boolean visit[];
    int color[];

    boolean isBipartitie(int g[][], int n) {
        visit = new boolean[n];
        color = new int[n];
        for (int i = 0; i < n; i++)
            if (!visit[i] && !bfsTwoColoring(g, i))
                return false;
        return true;
    }

    boolean bfsTwoColoring(int g[][], int source) {
        /* does not contain odd length cycle */
        color[source] = 1;
        Queue<Integer> q = new LinkedList<>();
        q.add(source);
        visit[source] = true;
        color[source] = 1;
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int w = 0; w < g[u].length; w++) {
                int v = g[u][w];
                if (color[u] == color[v]) return false;
                else if (!visit[v]) {
                    visit[v] = true;
                    color[v] = 3 - color[u];
                    q.add(v);
                }
            }
        }
        return true;
    }


    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new CHFNFRN().main1();
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
