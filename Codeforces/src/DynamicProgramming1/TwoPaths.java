package DynamicProgramming1;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class TwoPaths {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int n = ii();
        int from[] = new int[n - 1];
        int to[] = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            from[i] = ii() - 1;
            to[i] = ii() - 1;
        }
        int ans = 0;
        for (int i = 0; i < n - 1; i++) {
            int f[] = new int[n - 2];
            int t[] = new int[n - 2];
            int m = 0;
            for (int j = 0; j < n - 1; j++) {
                if (i == j) continue;
                f[m] = from[j];
                t[m++] = to[j];
            }
            int g[][] = packU(n, f, t, m);
//            out.println(Arrays.deepToString(g));
            ans = Math.max(ans, bfs(0, g));
        }
        out.println(ans);
    }

    int bfs(int v, int graph[][]) {
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
        int max = 0;
        for (int i = 0; i < graph.length; i++) {
            if (d[i] != Integer.MAX_VALUE / 2 && d[i] > max) {
                max = d[i];
                v = i;
            }
        }
        d = new int[graph.length];
        parent = new int[graph.length];
        Arrays.fill(d, Integer.MAX_VALUE / 2);
        d[v] = 0;
        q = new LinkedList<>();
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
        int d1 = 0;
        for (int i = 0; i < graph.length; i++) {
            if (d[i] != Integer.MAX_VALUE/2)
                d1 = Math.max(d1, d[i]);
            else
                v = i;
        }

        d = new int[graph.length];
        parent = new int[graph.length];
        Arrays.fill(d, Integer.MAX_VALUE / 2);
        d[v] = 0;
        q = new LinkedList<>();
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
        max = 0;
        for (int i = 0; i < graph.length; i++) {
            if (d[i] != Integer.MAX_VALUE / 2 && d[i] > max) {
                max = d[i];
                v = i;
            }
        }
        d = new int[graph.length];
        parent = new int[graph.length];
        Arrays.fill(d, Integer.MAX_VALUE / 2);
        d[v] = 0;
        q = new LinkedList<>();
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
        int d2 = 0;
        for (int i = 0; i < graph.length; i++) {
            if (d[i] != Integer.MAX_VALUE/2)
                d2 = Math.max(d2, d[i]);
        }
//        out.println(d1+" "+d2);
        return d1 * d2;
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
                    new TwoPaths().main1();
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
