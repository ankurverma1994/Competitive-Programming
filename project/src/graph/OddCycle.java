package graph;
/**
 * Created by ankurverma1994.
 * My code is awesome!
 */

import java.util.*;
import java.io.*;
import java.math.*;

public class OddCycle {

    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int n = ii(), m = ii();
        int from[] = new int[m];
        int to[] = new int[m];
        for (int i = 0; i < m; i++) {
            from[i] = ii() - 1;
            to[i] = ii() - 1;
        }


        if (m == 0) {
            // nC3
            long ans = 1L * n * (n - 1) * (n - 2) / 6;
            out.print("3 " + ans);
            return;
        }


        int g[][] = packU(n, from, to, m);
        boolean zero = false;
        visit = new boolean[n];
        color = new int[n];
        // 0 for count
        // 1 for color 1
        // 2 for color 2
        comp = new int[n][3];
        num = 0;
        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                boolean bipart = !bfsTwoColoring(g, i);
                num++;
                if (bipart) {
                    zero = true;
                    break;
                }
            }
        }


        if (zero) {
            out.println("0 1");
            return;
        } else {
            int max = 0;
            for (int i = 0; i < num; i++)
                max = Math.max(max, comp[i][0]);
            if (max < 3) {
                int size1 = 0, size2 = 0;
                for (int i = 0; i < num; i++) {
                    if (comp[i][0] == 1) size1++;
                    // fuck silly mistake
                    if (comp[i][0] == 2) size2++;
                }
                // 1-2 or 2-2
                long ans = 1L * size2 * size1 + 2L * size2 * (size2 - 1);
                out.println("2 " + ans);
                return;
            }

//            nC2 for each vertex set
            long ans = 0;
            for (int i = 0; i < num; i++) {
                if (comp[i][0] >= 3) {
                    ans = ans + (1L * comp[i][1] * (comp[i][1] - 1)) / 2 + (1L * comp[i][2] * (comp[i][2] - 1)) / 2;
                }
            }
            out.println("1 " + ans);
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

    // To check graph is Bipartite or not and also to confirm that there doesn't
    // exist odd length cycle.
    boolean visit[];
    int color[];
    int comp[][];
    int num;

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
        comp[num][0]++;
        comp[num][color[source]]++;
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int w = 0; w < g[u].length; w++) {
                int v = g[u][w];
                if (color[u] == color[v]) return false;
                else if (!visit[v]) {
                    visit[v] = true;
                    color[v] = 3 - color[u];
                    q.add(v);
                    comp[num][0]++;
                    comp[num][color[v]]++;
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
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new OddCycle().main1();
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
