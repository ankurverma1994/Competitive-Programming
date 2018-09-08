package Codeforces;
/**
 * Created by ankurverma1994.
 * My code is awesome!
 */

import java.util.*;
import java.io.*;
import java.math.*;

public class Missiles {

    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int n = ii(), m = ii(), s = ii() - 1;
        int from[] = new int[m];
        int to[] = new int[m];
        int w[] = new int[m];
        for (int i = 0; i < m; i++) {
            from[i] = ii() - 1;
            to[i] = ii() - 1;
            w[i] = ii();
        }
        long l = il();
        int g[][][] = packWU(n, from, to, w);
        long d[] = Dij(s, g);

        int ans = 0;
        for (int i = 0; i < m; i++) {
            int u = from[i], v = to[i];
            boolean a = false, b = false;
            if (d[u] < l && l - d[u] < w[i]) {
                long p = l - d[u], q = w[i] - p;
                if (d[u] + p <= d[v] + q) {
                    a = true;
                    ans++;
                }
            }
            if (d[v] < l && l - d[v] < w[i]) {
                long p = l - d[v], q = w[i] - p;
                if (d[v] + p <= d[u] + q) {
                    b = true;
                    ans++;
                }
            }
            if (a && b && d[u] < l && d[v] < l && l - d[v] + l - d[u] == w[i]) ans--;
        }
        for (long i : d) if (i == l) ans++;
        out.println(ans);
    }

    // Dijikstra Algo
    // @param s-source
    public long[] Dij(int s, int g[][][]) {
        int n = g.length;
        long dis[] = new long[n];
        boolean visit[] = new boolean[n];
        int parent[] = new int[n];
        Arrays.fill(dis, inf);
        Arrays.fill(parent, -1);
        dis[s] = 0;
        Comparator<Node> comp = new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.dist > o2.dist) return 1;
                if (o1.dist < o2.dist) return -1;
                return 0;
            }
        };
        PriorityQueue<Node> q = new PriorityQueue<>(2 * n, comp);
        q.add(new Node(s, 0));
        while (!q.isEmpty()) {
            Node now = q.poll();
            int u = now.x;
            if (!visit[u]) {
                visit[u] = true;
                for (int i = 0; i < g[u].length; i++) {
                    int curr = g[u][i][0];
                    long w = g[u][i][1];
                    if (dis[u] + w < dis[curr]) {
                        dis[curr] = dis[u] + w;
                        parent[curr] = u;
                        q.add(new Node(curr, dis[curr]));
                    }
                }
            }
        }
        return dis;
    }

    static public class Node {
        int x;
        long dist;

        public Node(int i, long j) {
            x = i;
            dist = j;
        }
    }

    // for undirected weighted graph
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

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new Missiles().main1();
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
