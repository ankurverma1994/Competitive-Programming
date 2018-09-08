package SPOJ;
/**
 * Created by ankurverma1994.
 * My code is awesome!
 */

import java.util.*;
import java.io.*;
import java.math.*;

class SAMER08A {

    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 4;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int n = ii(), m = ii();
        while (n != 0 && m != 0) {

            int s = ii(), d = ii();
            int from[] = new int[m];
            int to[] = new int[m];
            int w[] = new int[m];
            boolean takeEdges[] = new boolean[m];
            Arrays.fill(takeEdges, true);
            for (int i = 0; i < m; i++) {
                from[i] = ii();
                to[i] = ii();
                w[i] = ii();
            }

            int g[][][] = packWD(n, from, to, w);
            int gr[][][] = packWD(n, to, from, w);
            long ds[] = Dij(s, g);
            long dd[] = Dij(d, gr);
            int count = 0;

//            out.println("DIs " + ds[2]);

            for (int i = 0; i < m; i++) {
                if (ds[from[i]] + w[i] + dd[to[i]] == ds[d]) {
                    takeEdges[i] = false;
//                    out.println(i);
                } else
                    count++;
            }
//            out.println(count);

            int nfrom[] = new int[count];
            int nto[] = new int[count];
            int nw[] = new int[count];
            for (int i = 0, j = 0; i < m; i++) {
                if (takeEdges[i]) {
                    nfrom[j] = from[i];
                    nto[j] = to[i];
                    nw[j++] = w[i];
                }
            }

            ds = Dij(s, packWD(n, nfrom, nto, nw));

            out.println(ds[d] == inf ? "-1" : ds[d]);

            n = ii();
            m = ii();
        }
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
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new SAMER08A().main1();
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